package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by ashura on 09/04/18.
 */

public class Game {
    public void update(long elapsed) {
        //
    }

    public void draw() {
        //
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
        //
    }
}
