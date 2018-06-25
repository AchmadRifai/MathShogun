package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import rifai.achmad.japanese.R;

public class Tochi {
    private Context ctx;
    private Rect screen,hitbox;
    private int width,height;
    private Bitmap img;

    public Tochi(Context ctx, Rect screen) {
        this.ctx = ctx;
        this.screen = screen;
        sokutei();
    }

    private void sokutei() {
        width=screen.width();
        height=screen.height()/3;
        hitbox=new Rect(0,height,width,screen.height());
    }

    public int right() {
        return hitbox.right;
    }

    public void setX(int x){
        hitbox.set(x,height,x+width,screen.height());
    }

    public void draw(Canvas c) {
        gambar();
        c.drawBitmap(img,null,hitbox,null);
    }

    private void gambar() {
        img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bbumi);
    }

    public int getX(){
        return hitbox.left;
    }
}
