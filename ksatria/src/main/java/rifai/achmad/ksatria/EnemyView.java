package rifai.achmad.ksatria;

import android.graphics.Bitmap;

public class EnemyView {
    private GameView gameView;
    private Bitmap currentSprite,flipSprite;
    public enum AnimationType{
        DEAD,
        RUN,
        ATTACK
    }private Bitmap[]spritesAnimation;
    private AnimationType currentAction;
    private int speedFrame[];
}
