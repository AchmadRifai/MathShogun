package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Work;
import rifai.achmad.dbne.entity.Nilai;
import rifai.achmad.runner.obj.Bumi;
import rifai.achmad.runner.obj.Samurai;

public class GameModel {
    public enum State{
        RUN,IDLE,LOSE,ONQUIS
    }

    private Context ctx;
    private Rect screen;
    private SurfaceHolder holder;
    private State state;
    private String nama;
    private List<Bumi>tanah;
    private Samurai player;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public GameModel(Context ctx, Rect screen, SurfaceHolder holder) {
        this.ctx = ctx;
        this.screen = screen;
        this.holder = holder;
        state=State.RUN;
        initBumi();
        initPlayer();
    }

    private void initPlayer() {
        player=new Samurai(ctx,new Rect(100,screen.height()/2,screen.width()/5
        ,screen.height()/2),screen);
    }

    private void initBumi() {
        Bumi b1=new Bumi(screen,new Rect(0,(0+screen.height())/4,screen.width(),screen.height()),ctx)
                ,b2=new Bumi(screen,new Rect(b1.getWidth()+b1.getX(),
                (0+screen.height())/4,screen.width(),screen.height()),ctx);
        tanah=new LinkedList<>();
        tanah.add(b1);
        tanah.add(b2);
    }

    public void repaint(){
        Canvas c=holder.lockCanvas();
        if(c!=null)try{
            c.drawBitmap(BitmapFactory.decodeStream(ctx.getAssets().open("bg.png")),
                    null,screen,null);
            drawGame(c);
            holder.unlockCanvasAndPost(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawGame(Canvas c) {
        for(Bumi b:tanah)b.draw(c);
        player.draw(c);
    }

    public void jalan(){
        //for (Bumi b:tanah)b.setX(b.getX()-8);
        //if(0>=tanah.get(0).getX()+tanah.get(0).getWidth())tanah.get(0).setX(0);
        //tanah.get(1).setX(tanah.get(0).getX()+tanah.get(0).getWidth());
        player.jalan();
        aturState();
    }

    private void aturState() {
        //
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        aturState();
    }

    public void saveGame(){
        ConnHelper ch=new ConnHelper(ctx);
        Nilai n=new Nilai();
        n.setMode(Work.getMyMode(ctx));
        n.setTgl(org.joda.time.DateTime.now());
        n.setPemain(nama);
        ch.close();
    }
}
