package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import rifai.achmad.mathshogun.R;

/**
 * Created by ashura on 09/04/18.
 */

public class Game {
    public void update(long elapsed) {
        if(gs==GameStat.RUN){
            player.update(elapsed);
            highway.update(elapsed);
            skyline_close.update(elapsed);
            skyline_mid.update(elapsed);
            skyline_far.update(elapsed);
            if(musuh.isOffScreen())
                musuh=new Enemy(BitmapFactory.decodeResource(resources, R.drawable.van,opt),
                c,Enemy.generate(screen),screen,screen.height()-screen.width()/10);
            if(Rect.intersects(musuh.getHitbox(),player.getHitbox()))
                tersentuh();
        }
    }

    private void tersentuh() {
        gs=GameStat.RONIN;
    }

    public void draw() {
        Canvas canvas=holder.lockCanvas();
        if(canvas!=null){
            canvas.drawColor(Color.WHITE);
            switch (gs){
                //
            }
        }holder.unlockCanvasAndPost(canvas);
    }

    public void onTouchEvent(MotionEvent event) {
        //
    }

    public enum GameStat{
        RUN, PAUSE, ONQUIS, RONIN
    }

    private Context c;
    private SurfaceHolder holder;
    private Rect screen;
    private Resources resources;
    public GameStat gs=GameStat.RUN;

    private Pemain player;
    private ScrollableBackground highway,skyline_close,skyline_far,skyline_mid;
    private Enemy musuh;
    private Sprite text;
    Paint borderPaint=new Paint();
    BitmapFactory.Options opt;

    public Game(Context c, SurfaceHolder holder, Rect screen, Resources resources) {
        this.c = c;
        this.holder = holder;
        this.screen = screen;
        this.resources = resources;
        opt=new BitmapFactory.Options();
        opt.inScaled=false;
        restartGame();
    }

    private void restartGame() {
        player=new Pemain(null,c,new Rect(400,screen.height()/2,520,
                screen.height()/2+220),screen);
        highway=new ScrollableBackground(BitmapFactory.decodeResource(resources,R.drawable.highway,opt),
        c,new Rect(),screen,12);
    }
}
