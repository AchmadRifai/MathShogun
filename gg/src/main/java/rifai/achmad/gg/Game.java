package rifai.achmad.gg;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import org.joda.time.DateTime;

import java.util.List;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.dao.DAONilai;
import rifai.achmad.dbne.dao.DAOPengaturan;
import rifai.achmad.dbne.entity.Nilai;
import rifai.achmad.dbne.entity.Pengaturan;

public class Game {
    public boolean inQuis() {
        return quis;
    }

    public boolean kalahWes() {
        return state==GameState.LOST;
    }

    public void simpanScore() {
        ConnHelper ch=new ConnHelper(context);
        DAONilai dao=new DAONilai(ch);
        Nilai n=new Nilai();
        n.setTgl(DateTime.now());
        n.setPemain(nama);
        n.setNyawa(player.nyawa);
        n.setMode(getMode(ch));
        n.setLevel(player.level);
        n.setGold(player.gold);
        n.setExp(player.exp);
        dao.insert(n);
        ch.close();
    }

    private Soal.TipeSoal getMode(ConnHelper ch) {
        Soal.TipeSoal t=null;
        DAOPengaturan dao=new DAOPengaturan(ch);
        List<Pengaturan>l=dao.all();
        if(!l.isEmpty()){
            Pengaturan p=l.get(0);
            t=p.getMode();
        }return t;
    }

    public void kalah() {
        player.nyawa-=1;
        if(player.nyawa==0)state=GameState.LOST;
        else state=GameState.RUNNING;
    }

    public void menang() {
        player.gold+=20*player.level*kesulitan();
        player.exp+=20*player.level*kesulitan();
        checkEXP();
        if(player.level==5)state=GameState.LOST;
        else state=GameState.RUNNING;
    }

    private void checkEXP() {
        if(player.exp>=player.batas_exp){
            int b=player.batas_exp;
            player.level+=1;
            player.batas_exp*=2;
            player.exp-=b;
            checkEXP();
        }
    }

    private int kesulitan() {
        int i=0;
        ConnHelper ch=new ConnHelper(context);
        Soal.TipeSoal t=getMode(ch);
        switch (t){
            case SULIT:
                i=3;
                break;
            case SEDANG:
                i=2;
                break;
            case MUDAH:
                i=1;
                break;
        }ch.close();
        return 1;
    }

    private enum GameState {
        START, PAUSED, RUNNING, LOST,INQUIS
    }

    private Context context;
    private SurfaceHolder holder;
    private Rect screen;
    public boolean quis=false;
    private Resources resources;
    private GameState state = GameState.START;

    private Player player;
    private ScrollableBackground highway;
    private ScrollableBackground skyline_close;
    private ScrollableBackground skyline_mid;
    private ScrollableBackground skyline_far;
    private Vehicle testCar;
    private Sprite loseText;
    public String nama;

    Paint borderPaint = new Paint();
    BitmapFactory.Options options;

    public Game(Context context, Rect screen, SurfaceHolder holder, Resources resources) {
        this.context = context;
        this.screen = screen;
        this.holder = holder;
        this.resources = resources;
        options = new BitmapFactory.Options();
        options.inScaled = false;
        restartGame();
    }

    public void onTouchEvent(MotionEvent event) {
        if (state == GameState.RUNNING) {
            player.jump();
        } else if(state == GameState.LOST){
            restartGame();
        } else if(state == GameState.START) {
            state = GameState.RUNNING;
        } else if(state == GameState.PAUSED) {
            state = GameState.RUNNING;
        }
    }


    /**
     * Game logic is checked here! Hitboxes, movement, etc.
     * @param elapsed Time since game started
     */
    public void update(Long elapsed) {
        if(state == GameState.RUNNING){
            // Do stuff
            player.update(elapsed);
            highway.update(elapsed);
            skyline_close.update(elapsed);
            skyline_mid.update(elapsed);
            skyline_far.update(elapsed);
            testCar.update(elapsed);

            if(testCar.isOffScreen()) testCar = new Vehicle(BitmapFactory.decodeResource(resources, R.drawable.van, options),
                    context, Vehicle.generate(screen), screen, screen.height() - screen.width() / 10);

            if(Rect.intersects(testCar.getHitbox(), player.getHitbox())) loseGame();
        }
    }


    /**
     * Decides whether to draw
     */
    public void draw() {
        //Log.d("GAME_DRAW", "Locking canvas");
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            switch (state) {
                case RUNNING:
                    drawGame(canvas);
                    break;
                case LOST:
                    drawGame(canvas);
                    loseText.draw(canvas, 0);
                    break;
                case START:
                    drawGame(canvas);
                    break;
                case INQUIS:
                    drawGame(canvas);
                    QuisFragment qf=new QuisFragment(player.level);
                    Activity a= (Activity) context;
                    qf.show(a.getFragmentManager(),"Level"+player.level+"Gold"
                            +player.gold+"EXP"+player.exp);
                    break;
            }
            //Log.d("GAME_DRAW", "Unlocking canvas");
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Draws game resources
     * @param canvas Canvas to be drawn on
     */
    private void drawGame(Canvas canvas) {
        //Log.d("GAME_DRAWGAME", "Trying to draw everything in the game!");
        //canvas.drawRect(screen, borderPaint);
        skyline_far.draw(canvas);
        skyline_mid.draw(canvas);
        skyline_close.draw(canvas);
        highway.draw(canvas);
        testCar.draw(canvas, 0);
        player.draw(canvas, 0);
        nyawaDraw(canvas);
        levelDraw(canvas);
        expDraw(canvas);
        goldDraw(canvas);
    }

    private void nyawaDraw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(Color.RED);
        int i=resources.getDimensionPixelSize(R.dimen.status_text_size);
        p.setTextSize(i);
        canvas.drawText(""+player.nyawa+" Nyawa",10,12,p);
    }

    private void goldDraw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(Color.argb(255,212,175,55));
        int i=resources.getDimensionPixelSize(R.dimen.status_text_size);
        p.setTextSize(i);
        canvas.drawText(""+player.gold+" Gold",10,48,p);
    }

    private void expDraw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(Color.GREEN);
        int i=resources.getDimensionPixelSize(R.dimen.status_text_size);
        p.setTextSize(i);
        canvas.drawText(""+player.exp+" EXP",10,36,p);
    }

    private void levelDraw(Canvas canvas) {
        Paint p=new Paint();
        p.setColor(Color.BLUE);
        int i=resources.getDimensionPixelSize(R.dimen.status_text_size);
        p.setTextSize(i);
        canvas.drawText("Level "+player.level,10,24,p);
    }

    private void restartGame() {
        player = new Player(null, context, new Rect(
                400,
                screen.height()/2,
                520,
                screen.height()/2 + 220),
                screen);

        highway = new ScrollableBackground( BitmapFactory.decodeResource(resources, R.drawable.highway, options),
                context, new Rect( 0, screen.height() - screen.width() / 10, screen.width(), screen.height()), screen, 12);

        skyline_close = new ScrollableBackground(BitmapFactory.decodeResource(resources, R.drawable.skyline_close, options),
                context, new Rect( 0, screen.height() / 2, screen.height() * 3, screen.height()), screen, 8);

        skyline_mid = new ScrollableBackground(BitmapFactory.decodeResource(resources, R.drawable.skyline_mid, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 4);

        skyline_far = new ScrollableBackground(BitmapFactory.decodeResource(resources, R.drawable.skyline_far, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 2);

        testCar = new Vehicle(BitmapFactory.decodeResource(resources, R.drawable.van, options),
                context, Vehicle.generate(screen), screen, screen.height() - screen.width() / 10);

        loseText = new Sprite(BitmapFactory.decodeResource(resources, R.drawable.lose_text, options),
                context, new Rect(screen.width() / 2 - 600, screen.height() / 2 - 180, screen.width() / 2 + 600, screen.height() / 2 + 180), screen);

        borderPaint.setStrokeWidth(24);
        borderPaint.setColor(Color.GREEN);
        borderPaint.setStyle(Paint.Style.STROKE);
        state = GameState.RUNNING;
    }

    private void loseGame() {
        state = GameState.LOST;
    }
}
