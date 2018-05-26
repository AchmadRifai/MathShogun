package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.IOException;

public class Background {
    private Bitmap image;
    private Context ctx;
    private Rect hitbox,screen;
    private int speed;
    private Sprite[] sprites;

    public Background(Bitmap image, Context ctx, Rect hitbox, Rect screen, int speed) {
        this.image = image;
        this.ctx = ctx;
        this.hitbox = hitbox;
        this.screen = screen;
        this.speed = speed;
        buatBg();
    }

    private void buatBg() {
        sprites=new Sprite[3];
        sprites[0]=new Sprite(image,ctx,hitbox,screen);
        sprites[1]=new Sprite(image,ctx,hitbox,screen);
        sprites[2]=new Sprite(image,ctx,hitbox,screen);
        sprites[0].setX(0);
        sprites[1].setX(sprites[0].getRight());
        sprites[2].setX(sprites[1].getRight());
    }

    public void update(long elapsed){
        for(Sprite s:sprites){
            s.setX(s.getX()-speed);
            if(s.getRight()<0)
                s.setX(s==sprites[0]?sprites[1].getRight()-speed:s==sprites[1]?sprites[0].getRight()-speed:
                sprites[1].getRight()-speed);
        }
    }

    public void draw(Canvas c) {
        for(Sprite s:sprites)s.draw(c);
    }

    private void gambar() throws IOException {
        for (Sprite s:sprites)
            s.image= BitmapFactory.decodeStream(ctx.getAssets().open("bbumi.png"));
    }
}
