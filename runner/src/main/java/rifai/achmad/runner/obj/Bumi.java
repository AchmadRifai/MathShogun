package rifai.achmad.runner.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.IOException;

public class Bumi {
    private Rect screen,hitbox;
    private Bitmap gbr;
    private int x,y,width,height;
    private Context ctx;

    public Bumi(Rect screen, Rect hitbox, Context ctx) {
        this.screen = screen;
        this.hitbox = hitbox;
        this.ctx = ctx;
        x=hitbox.left;
        y=hitbox.bottom;
        width=hitbox.width();
        height=hitbox.height();
    }

    private void loadIMG() {
        try {
            gbr=BitmapFactory.decodeStream(ctx.getAssets().open("bbumi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
        x=hitbox.left;
        y=hitbox.top;
        width=hitbox.width();
        height=hitbox.height();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        hitbox.set(x,y,x+width,y+height);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        hitbox.set(x,y,x+width,y+height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        hitbox.set(x,y,x+width,y+height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        hitbox.set(x,y,x+width,y+height);
    }

    public void draw(Canvas c){
        loadIMG();
        if(gbr!=null){
            c.drawBitmap(gbr,null,hitbox,null);
        }else Log.e("Bumi","Image not found");
    }
}
