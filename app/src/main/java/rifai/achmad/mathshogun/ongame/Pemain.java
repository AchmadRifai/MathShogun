package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by ashura on 09/04/18.
 */

public class Pemain extends Sprite{
    public Pemain(Bitmap image, Context ctx, Rect hitbox, Rect screen) {
        super(image, ctx, hitbox, screen);
        affectedByGrav=true;
    }

    public void dash(){
        if(Math.abs(this.getBottom() - screen.height() + screen.width() / 10) < 5) applyForce(0, -60);
    }

    public void applyForce(double i, double i1) {
        ax=i;
        ay=i1;
    }

    @Override
    public void update(long elapsed) {
        if(getHitbox().bottom>=screen.height()-screen.width()/10){
            setY(screen.height()-screen.width()/10-getHeight());
            vy=0;
        }super.update(elapsed);
        ax=ay=0;
    }
}