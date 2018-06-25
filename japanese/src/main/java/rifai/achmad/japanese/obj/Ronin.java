package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import rifai.achmad.japanese.R;

public class Ronin {
    private Context ctx;
    private Rect screen,hitbox;
    private int mode,width,height;
    private Bitmap img;
    private boolean kanan;
    private Status status;

    public Ronin(Context ctx, Rect screen) {
        this.ctx = ctx;
        this.screen = screen;
        mode=1;
        status=Status.RUN;
        kanan=false;
        sokutei();
    }

    private void sokutei() {
        width=screen.width()/7;
        height=screen.height()/4;
        hitbox=new Rect(screen.width()-(screen.width()/100),screen.height()/2+(screen.height()/20),
                screen.width()-(screen.width()/100)+width,
                screen.height()/2+(screen.height()/20)+height);
    }

    public void draw(Canvas c) {
        gambar();
        c.drawBitmap(img,null,hitbox,null);
    }

    private void gambar() {
        if (status == Status.WIN) {
            if (mode == 1)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_80);
            else if (mode == 2)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_81);
            else if (mode == 3)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_82);
        } else if (status == Status.RUN) {
            if (kanan) img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_72);
            else img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_68);
        } else if (status == Status.DIED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_132);
        else if (status == Status.FIGHT)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_55);
        else if (status == Status.INJURED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_37);
    }

    public boolean isKanan() {
        return kanan;
    }

    public void setKanan(boolean kanan) {
        this.kanan = kanan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setX(int x) {
        hitbox.set(x,screen.height()/2+(screen.height()/20),x+width,
                screen.height()/2+(screen.height()/20)+height);
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public int getX() {
        return hitbox.left;
    }

    public int getRight() {
        return hitbox.right;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public enum Status {
        RUN,DIED,WIN,INJURED,FIGHT
    }
}
