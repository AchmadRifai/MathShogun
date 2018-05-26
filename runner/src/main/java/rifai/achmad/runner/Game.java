package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.io.IOException;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Work;
import rifai.achmad.dbne.dao.DAONilai;
import rifai.achmad.dbne.entity.Nilai;

public class Game {
    public void onTuc(MotionEvent event) {
        if(state==State.RUN)player.jump();
        else if(state==State.LOST) {
            try {
                restartGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(state==State.ONQUIS){}
        else state=State.RUN;
    }

    public void saveData(String nama) {
        ConnHelper c=new ConnHelper(ctx);
        DAONilai dao=new DAONilai(c);
        Nilai n=new Nilai();
        n.setTgl(org.joda.time.DateTime.now());
        n.setExp(player.getExp());
        n.setGold(player.getGold());
        n.setLevel(player.getLevel());
        n.setPemain(nama);
        n.setMode(Work.getMyMode(ctx));
        n.setNyawa(player.getNyawa());
        dao.insert(n);
        c.close();
    }

    public enum State{
        PAUSE,RUN,ONQUIS,LOST
    }

    public Context ctx;
    private SurfaceHolder holder;
    private Rect screen;
    private State state=State.RUN;
    private Player player;
    private Background background;
    private Enemy enemy;
    BitmapFactory.Options options;

    public Game(Context ctx, SurfaceHolder holder, Rect screen) {
        this.ctx = ctx;
        this.holder = holder;
        this.screen = screen;
        options=new BitmapFactory.Options();
        options.inScaled=false;
        try {
            restartGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void restartGame() throws IOException {
        player=new Player(null,ctx,new Rect(100,screen.height()/2
                ,320,screen.height()/2+220),screen);
        background=new Background(BitmapFactory.decodeStream(ctx.getAssets().open("bbumi.png")),
                ctx,new Rect(0,screen.height()-screen.width()/3,screen.width(),screen.height()),screen
                ,12);
        enemy=new Enemy(null,ctx,new Rect(100,screen.height()/2,
                320,screen.height()/2+220),screen,
                screen.height()-screen.width()/10);
    }

    public void draw() throws IOException {
        Canvas c=holder.lockCanvas();
        if(c!=null){
            c.drawColor(Color.WHITE);
            c.drawBitmap(BitmapFactory.decodeStream(ctx.getAssets().open("bg.png")),null,
                    screen,null);
            if(state==State.RUN)drawGame(c);
            else if(state==State.PAUSE)drawGame(c);
            else if(state==State.LOST)drawGame(c);
            else if(state==State.ONQUIS) {
                drawGame(c);
                startQuis();
            }
            holder.unlockCanvasAndPost(c);
        }
    }

    private void startQuis() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Quise quise=new Quise(ctx,Work.getMyMode(ctx),player,Game.this);
                quise.show();
            }
        });
    }

    private void drawTemple(Canvas c) {
        try {
            Bitmap img=BitmapFactory.decodeStream(ctx.getAssets().open("castle.png"));
            Rect hit=player.getHitbox();
            hit.left-=30;
            hit.right=-30;
            c.drawBitmap(img,null,hit,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawGame(Canvas c) {
        background.draw(c);
        enemy.draw(c);
        player.draw(c);
        drawTemple(c);
        nyawaDraw(c);
        levelDraw(c);
        goldDraw(c);
        expDraw(c);
    }

    private void expDraw(Canvas c) {
        Paint p=new Paint();
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(30);
        c.drawText(""+player.getExp()+" EXP",10,130,p);
    }

    private void goldDraw(Canvas c) {
        Paint p=new Paint();
        p.setColor(Color.rgb(255,215,0));
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(30);
        c.drawText(""+player.getGold()+" Point",10,95,p);
    }

    private void levelDraw(Canvas c) {
        Paint p=new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(30);
        c.drawText("Level "+player.getLevel(),10,60,p);
    }

    private void nyawaDraw(Canvas c) {
        Paint p=new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(30);
        c.drawText(""+player.getNyawa()+" Nyawa",10,25,p);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void update(long elapsed){
        if(state==State.RUN){
            player.update(elapsed);
            //background.update(elapsed);
            enemy.update(elapsed);
            if(enemy.isOffScreen())enemy=new Enemy(null,ctx,Enemy.generate(screen),screen
            ,screen.height()-screen.width()/10);
            if(enemy.getHitbox().left==player.getHitbox().right)
                state=State.ONQUIS;
            aturStatus();
        }
    }

    private void aturStatus() {
        if(state==State.ONQUIS){
            enemy.setState(Enemy.State.LOSE);
            player.setState(Player.State.QUIZ);
        }else if(state==State.PAUSE){
            player.setState(Player.State.IDLE);
        }else if(state==State.RUN){
            enemy.setState(Enemy.State.RUN);
            player.setState(Player.State.IDLE);
        }
    }
}
