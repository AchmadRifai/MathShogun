package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ashura on 09/04/18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder holder;
    private Context c;
    private Game g;
    private GameThread gt;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        c=context;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i("GAME","Creating View");
        g=new Game(getContext(),holder,new Rect(0, 0, getWidth(), getHeight()),getResources());
        gt=new GameThread(g);
        gt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("GAME", "changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d("GAMEVIEW", "destroyed");
        if(gt!=null){
            gt.shutdown();
            while (gt!=null){
                try {
                    gt.join();
                    gt=null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pause() {
        g.gs= Game.GameStat.PAUSE;
    }

    public void resume() {
        g.gs= Game.GameStat.RUN;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        g.onTouchEvent(event);
        return true;
    }
}
