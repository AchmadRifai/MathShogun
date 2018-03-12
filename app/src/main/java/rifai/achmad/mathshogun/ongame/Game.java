package rifai.achmad.mathshogun.ongame;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by ai on 10/03/2018.
 */

public class Game {
    public GameStatus gs;
    private Context c;
    private Rect r;
    private SurfaceHolder sh;
    private Resources res;
    private Activity a;
    private Paint paint;
    private BitmapFactory.Options opt;

    public Game(Context c, Rect r, SurfaceHolder sh, Resources res, Activity a) {
        this.c = c;
        this.r = r;
        this.sh = sh;
        this.res = res;
        this.a = a;
        gs=GameStatus.RUN;
        paint=new Paint();
        opt=new BitmapFactory.Options();
    }
}
