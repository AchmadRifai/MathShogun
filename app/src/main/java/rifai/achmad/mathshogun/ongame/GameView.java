package rifai.achmad.mathshogun.ongame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ai on 10/03/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private Activity a;
    private Game g;
    private Context c;
    private GameThread gt;
    private SurfaceHolder holder;

    public GameView(Context context, Activity a) {
        super(context);
        c=context;
        this.a=a;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        g=new Game(c,new Rect(0,0,getWidth(),getHeight()),holder,getResources(),a);
        gt=new GameThread(g);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
