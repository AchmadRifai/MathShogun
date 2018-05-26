package rifai.achmad.runner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private Game game;
    private SurfaceHolder holder;
    private Context context;
    private MyThreat myThreat;
    private Activity activity;
    private String nama;

    public GameView(Activity activity, String nama) {
        super(activity);
        this.nama=nama;
        this.activity=activity;
        this.context=activity;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("GameView","Starting Game");
        game=new Game(context,getHolder(),new Rect(0,0,getWidth(),getHeight()));
        myThreat=new MyThreat(game, (MyThreat.GameProcess) activity);
        myThreat.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("GameView","Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("GameView","Destroying");
        myThreat.running=false;
        game.saveData(nama);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        game.onTuc(event);
        return true;
    }
}
