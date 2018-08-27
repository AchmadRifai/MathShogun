package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import rifai.achmad.japanese.R;

public class Shogun {
    private Context ctx;
    private Rect screen,hitbox;
    private int mode,width,height;
    private boolean kanan;
    private Bitmap img;
    private Status status;
    private int level,gold,nyawa,exp,batas_exp;

    public Shogun(Context ctx, Rect screen) {
        this.ctx = ctx;
        this.screen = screen;
        status=Status.RUN;
        kanan=false;
        mode=1;
        sokutei();
        level=1;
        gold=0;
        nyawa=100;
        exp=0;
        batas_exp=100;
    }

    private void sokutei() {
        width=screen.width()/7;
        height=screen.height()/4;
        hitbox=new Rect(screen.width()/10,screen.height()/2+(screen.height()/20),
                screen.width()/10+width,screen.height()/2+(screen.height()/20)+height);
    }

    public void jalan() {
        kanan=!kanan;
    }

    public void draw(Canvas c) {
        gambar();
        c.drawBitmap(img,null,hitbox,null);
    }

    private void gambar() {
        if(status==Status.WIN){
            if(mode==1)img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.samurai_15);
            else if(mode==2)img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.samurai_16);
            else if(mode==3)img=BitmapFactory.decodeResource(ctx.getResources(), R.drawable.samurai_17);
        }else if(status==Status.RUN){
            if(kanan)img=BitmapFactory.decodeResource(ctx.getResources(),R.drawable.samurai_13);
            else img=BitmapFactory.decodeResource(ctx.getResources(),R.drawable.samurai_14);
        }else if(status==Status.STAY||status==Status.FIGHT)
            img=BitmapFactory.decodeResource(ctx.getResources(),R.drawable.samurai_12);
        else if(status==Status.DIED)img=BitmapFactory.decodeResource(ctx.getResources(),R.drawable.samurai_35);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        if(exp>=batas_exp){
            int b=batas_exp;
            setLevel(level+1);
            setBatas_exp(batas_exp*2);
            setExp(exp-b);
        }
    }

    public int getBatas_exp() {
        return batas_exp;
    }

    public void setBatas_exp(int batas_exp) {
        this.batas_exp = batas_exp;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public enum Status{
        RUN,FIGHT,STAY,DIED,WIN
    }
}
