package rifai.achmad.mathshogun.ongame;

/**
 * Created by ashura on 09/04/18.
 */

public class GameThread extends Thread{
    private Game g;
    private final int fps=100;
    private volatile boolean running=true;

    public GameThread(Game g) {
        this.g = g;
    }

    @Override
    public void run() {
        long lastTime=System.currentTimeMillis();
        while (running){
            long now=System.currentTimeMillis();
            long elapsed=now-lastTime;
            if(elapsed>fps){
                g.update(elapsed);
                g.draw();
            }lastTime=now;
        }
    }

    public void shutdown() {
        running=false;
    }
}
