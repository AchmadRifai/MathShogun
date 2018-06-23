package rifai.achmad.runner;

public class GameController extends Thread{
    private GameModel gm;
    private boolean running;
    private GameNext gn;

    public void setGn(GameNext gn) {
        this.gn = gn;
    }

    public GameController(GameModel gm) {
        this.gm = gm;
        running=true;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running){
            if(gm.getState()== GameModel.State.RUN)jalan();
            if(!running)break;
            if(gm.getState()== GameModel.State.LOSE)kalah();
            if(!running)break;
        } gm.saveGame();
        gn.endGame();
    }

    private void kalah() {
        gm.repaint();
        running=false;
    }

    private void jalan() {
        gm.jalan();
        gm.repaint();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface GameNext{
        void endGame();
    }
}
