package rifai.achmad.runner.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.IOException;

public class Samurai {
    public enum State{
        RUN,WIN,LOSE,ONQUIS,IDLE
    }

    private Bitmap img;
    private Context ctx;
    private Rect hitbox,screen;
    private int x,y,width,height,mode;
    private State state;
    private boolean kanan;
    private int level=1,nyawa=5,batas_exp=100,exp=0,gold=0;

    public Samurai(Context ctx,Rect hitbox, Rect screen) {
        this.ctx = ctx;
        this.hitbox = hitbox;
        this.screen = screen;
        kanan=true;
        state=State.IDLE;
        x=hitbox.left;
        y=hitbox.top;
        width=hitbox.width();
        height=hitbox.height();
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void draw(Canvas c){
        loadImg();
        if(img!=null){
            c.drawBitmap(img,null,hitbox,null);
        }else Log.i("Samurai","Image not found");
    }

    private void loadImg() {
        try{
            if(state==State.RUN){
                if(kanan)
                    img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-13.png"));
                else
                    img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-14.png"));
            }else if(state==State.IDLE)
                img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-12.png"));
            else if(state==State.ONQUIS)
                img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-18.png"));
            else if(state==State.WIN){
                if(mode==1)
                    img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-15.png"));
                else if(mode==2)
                    img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-16.png"));
                else if(mode==3)
                    img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-17.png"));
            }else if(state==State.LOSE)
                img= BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-35.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jalan(){
        kanan=!kanan;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
        if(nyawa<=0)state=State.LOSE;
    }

    public int getBatas_exp() {
        return batas_exp;
    }

    public void setBatas_exp(int batas_exp) {
        this.batas_exp = batas_exp;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
