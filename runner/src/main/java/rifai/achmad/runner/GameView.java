package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private String nama;
    private SurfaceHolder holder;
    private GameModel gm;
    private GameController gc;
    private GameController.GameNext gn;

    public GameView(Context context, String nama) {
        super(context);
        gn= (GameController.GameNext) context;
        this.nama=nama;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("GameView","Game Start");
        gm=new GameModel(this.getContext(),new Rect(0,0,getWidth(),getHeight()),holder);
        gm.setNama(nama);
        gc=new GameController(gm);
        gc.setGn(gn);
        gc.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("GameView","Game Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("GameView","Game Stop");
    }

    public void destrooy(){
        gc.setRunning(false);
    }
}
