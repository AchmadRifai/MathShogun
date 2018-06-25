package rifai.achmad.japanese;

import android.content.Context;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private String nama;
    private SurfaceHolder holder;
    private GameModel gm;
    private Context ctx;
    private GameThread gt;

    public GameView(Context ctx, String nama) {
        super(ctx);
        this.ctx=ctx;
        this.nama=nama;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gm=new GameModel(ctx,nama,new Rect(0,0,getWidth(),getHeight()),
                holder);
        gt=new GameThread(ctx,gm);
        gt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void stopGame(){
        gt.setRunning(false);
    }
}
