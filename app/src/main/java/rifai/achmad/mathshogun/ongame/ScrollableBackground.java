package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by ashura on 20/04/18.
 */

public class ScrollableBackground {
    public Sprite[]sprites;
    public int speed;

    public ScrollableBackground(Bitmap image, Context context, Rect hitbox, Rect screen, int speed) {
        this.speed = speed;
        sprites=new Sprite[2];
        sprites[0] = new Sprite(image, context, hitbox, screen);
        sprites[1] = new Sprite(image, context, hitbox, screen);
        sprites[0].setX(0);
        sprites[1].setX(sprites[0].getRight());
    }

    public void draw(Canvas canvas){
        for(Sprite s:sprites)s.draw(canvas,0);
    }

    public void update(long elapse){
        for(Sprite s:sprites){
            s.setX(s.getX() - speed);
            if(s.getRight() < 0) s.setX(
                    (s == sprites[0]) ? sprites[1].getRight() - speed: sprites[0].getRight() - speed);
        }
    }
}
