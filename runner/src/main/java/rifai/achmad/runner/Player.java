package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.IOException;

public class Player extends Sprite{
    public enum State{
        RUN,QUIZ,DEAD,IDLE
    }

    private boolean kanan=false;
    private State state=State.IDLE;
    private int level=1,nyawa=5,exp=0,gold=0,batas_exp=100;

    public Player(Bitmap image, Context ctx, Rect hitbox, Rect screen) {
        super(image, ctx, hitbox, screen);
        affectedByGrav=true;
    }

    @Override
    public void update(long elapsed) {
        state=State.RUN;
        if(getHitbox().bottom>=screen.height()-screen.width()/10){
            setY(screen.height()-screen.width()/10-getHeight());
            vy=0;
        } super.update(elapsed);
        ax=ay=0;
    }

    @Override
    public void draw(Canvas c) {
        try {
            gambar();
        } catch (IOException e) {
            e.printStackTrace();
        }super.draw(c);
        state=State.RUN;
    }

    private void gambar() throws IOException {
        if(state==State.IDLE)
            image=BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-12.png"));
        else if(state==State.DEAD)
            image=BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-35.png"));
        else if(state==State.QUIZ)
            image=BitmapFactory.decodeStream(ctx.getAssets().open("samurai/samurai-15.png"));
        else image=null;
    }

    public void jump(){
        if(5>Math.abs(getBottom()-screen.height()+screen.width()/10))
            applyForce(0,-60);
    }

    public void applyForce(int i, int i1) {
        ax=i;
        ay=i1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if(level==5)state=State.IDLE;
    }

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
        if(nyawa==0)state=State.DEAD;
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

    public int getBatas_exp() {
        return batas_exp;
    }

    public void setBatas_exp(int batas_exp) {
        this.batas_exp = batas_exp;
    }

    public void setState(State state) {
        this.state = state;
    }
}
