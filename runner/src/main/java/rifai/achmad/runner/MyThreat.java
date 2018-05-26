package rifai.achmad.runner;

import java.io.IOException;

public class MyThreat extends Thread{
    private Game game;
    private GameProcess gameProcess;
    public boolean running=true;

    public MyThreat(Game game, GameProcess gameProcess) {
        this.game = game;
        this.gameProcess = gameProcess;
    }

    @Override
    public void run() {
        while (running){
            long lastTime=System.currentTimeMillis();
            while (game.getState()== Game.State.RUN){
                long now=System.currentTimeMillis(),elapsed=now-lastTime;
                game.update(elapsed);
                try {
                    game.draw();
                } catch (IOException e) {
                    e.printStackTrace();
                }lastTime=elapsed;
            }if(game.getState()==Game.State.LOST){
                game.update(0);
                try {
                    game.draw();
                } catch (IOException e) {
                    e.printStackTrace();
                }gameProcess.endGame();
                running=false;
            }
        }
    }

    public interface GameProcess{
        void endGame();
    }
}
