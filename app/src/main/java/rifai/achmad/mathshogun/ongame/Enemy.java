package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by ashura on 21/04/18.
 */

public class Enemy extends Sprite{
    public int roadHeight;
    public Rect hitbox;

    public Enemy(Bitmap image, Context ctx, Rect hitbox, Rect screen,int roadHeight) {
        super(image, ctx, hitbox, screen);
        this.roadHeight=roadHeight;
        vx=-30;
        setX(screen.right);
        setY(roadHeight-getHeight());
    }

    public boolean isOffScreen(){
        return getRight()<screen.left;
    }

    public static Rect generate(Rect screen){
        return new Rect(0, 0, 300, 140);
    }
}
