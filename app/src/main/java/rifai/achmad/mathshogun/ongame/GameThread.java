package rifai.achmad.mathshogun.ongame;

/**
 * Created by ai on 10/03/2018.
 */

public class GameThread extends Thread{
    private Game g;

    public GameThread(Game g) {
        this.g = g;
    }
}
