package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import rifai.achmad.japanese.R;

public class Kenjutsu {
    private Context ctx;
    private Rect hitbox;
    private int mode;
    private boolean show;
    private Bitmap img;

    public Kenjutsu(Context ctx, Rect hitbox) {
        this.ctx = ctx;
        this.hitbox = hitbox;
        mode=1;
        show=false;
    }

    public void draw(Canvas c) {
        gambar();
        if(show)c.drawBitmap(img,null,hitbox,null);
    }

    private void gambar() {
        if(mode==1)img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.kiri_0);
        else img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.kiri_1);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }
}
