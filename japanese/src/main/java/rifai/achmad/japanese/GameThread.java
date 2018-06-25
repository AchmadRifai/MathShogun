package rifai.achmad.japanese;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

public class GameThread extends Thread {
    private Context ctx;
    private GameModel gm;
    private boolean running,quis_on;
    private GameProcess gp;
    private MediaPlayer mp;

    public GameThread(Context ctx, GameModel gm) {
        this.ctx = ctx;
        this.gm = gm;
        gp= (GameProcess) ctx;
        running=true;
        quis_on=false;
    }

    @Override
    public void run() {
        while (running){
            if(gm.getKondisi()== GameModel.Kondisi.RUN)lari();
            if(!running)break;
            if(gm.getKondisi()== GameModel.Kondisi.LOST)kalah();
            if(!running)break;
            if(gm.getKondisi()== GameModel.Kondisi.FIGHTING&&!quis_on)onquis();
            if(!running)break;
        } gm.saveGame();
        gp.endGame();
    }

    private void onquis() {
        playAudio(R.raw.burning);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                gp.onquis(gm,GameThread.this);
            }
        }); quis_on=true;
        clearAudio();
    }

    private void kalah() {
        running=false;
        gm.repaint();
    }

    private void lari() {
        playAudio(R.raw.walk);
        gm.jalan();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void clearAudio() {
        if(mp!=null)mp.release();
    }

    private void playAudio(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mp=MediaPlayer.create(ctx,i);
                mp.start();
            }
        });
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setQuis_on(boolean quis_on) {
        this.quis_on = quis_on;
    }

    public interface GameProcess{
        void onquis(GameModel gm, GameThread gt);
        void endGame();
    }
}
