package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.IOException;

public class Enemy extends Sprite{
    public enum State{
        WIN,LOSE,RUN,DEAD
    }

    public Rect hitbox;
    public int roadHeight;
    private boolean kanan=false;
    private State state=State.RUN;

    public Enemy(Bitmap image, Context ctx, Rect hitbox, Rect screen, int roadHeight) {
        super(image, ctx, hitbox, screen);
        this.roadHeight=roadHeight;
        vx=-30;
        setX(screen.right);
        setY(roadHeight-getHeight());
    }

    public static Rect generate(Rect screen){
        return new Rect(0,0,300,140);
    }

    public boolean isOffScreen(){
        return getRight()<screen.left;
    }

    @Override
    public void update(long elapsed) {
        super.update(elapsed);
        if(state==State.LOSE)
            getHitbox().top+=100;
        else getHitbox().top=screen.height()/2;
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
        if(state==State.RUN){
            if(kanan){
                image= BitmapFactory.decodeStream(ctx.getAssets().open("robber/rampok-72.png"));
                kanan=false;
            }else {
                image= BitmapFactory.decodeStream(ctx.getAssets().open("robber/rampok-68.png"));
                kanan=true;
            }
        }else if(state==State.LOSE)
            image= BitmapFactory.decodeStream(ctx.getAssets().open("robber/rampok-55.png"));
        else if(state==State.WIN)
            image= BitmapFactory.decodeStream(ctx.getAssets().open("robber/rampok-132.png"));
        else image=null;
    }

    public State getSta() {
        return state;
    }

    public void setState(State state){
        this.state=state;
    }
}
