package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import rifai.achmad.japanese.R;

public class Dan {
    private Context ctx;
    private Rect hitbox;
    private boolean show;
    private Bitmap img;

    public Dan(Context ctx, Rect hitbox) {
        this.ctx = ctx;
        this.hitbox = hitbox;
        show=false;
    }

    public void draw(Canvas c) {
        img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.up);
        if(show)c.drawBitmap(img,null,hitbox,null);
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
