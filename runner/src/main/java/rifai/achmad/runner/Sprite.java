package rifai.achmad.runner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {
    public Rect getHitbox() {
        return hitbox;
    }

    public enum State{
        IDLE,JUMP,FLY,LAND
    }

    public Bitmap image;
    public Context ctx;
    private Rect hitbox;
    public Rect screen;
    private State state;

    private int height,width;
    private double x,y;

    public double ax,ay,vx,vy;
    public final double FRIC=3,GRAV=4;
    public boolean affectedByGrav=false;
    Paint p1=new Paint(),p2=new Paint(),p3=new Paint();

    public Sprite(Bitmap image, Context ctx, Rect hitbox, Rect screen) {
        this.image = image;
        this.ctx = ctx;
        this.hitbox = hitbox;
        this.screen = screen;
        state=State.IDLE;
        width=hitbox.width();
        height=hitbox.height();
        x=hitbox.left;
        y=hitbox.top;
        ax=ay=vx=vy=0;
        p1.setAntiAlias(false);
        p1.setFilterBitmap(false);
        p1.setDither(false);
        p1.setColor(Color.GREEN);
        p2.setStrokeWidth(10);
        p2.setStyle(Paint.Style.STROKE);
        p3.setStyle(Paint.Style.STROKE);
        p3.setStrokeWidth(8);
        p3.setColor(Color.GREEN);
    }

    public void update(long elapsed){
        vx+=ax;
        vy+=ay;
        if(affectedByGrav)vy+=GRAV;
        setX(x+vx);
        setY(y+vy);
    }

    public void setY(double v) {
        y=v;
        hitbox.set((int)x,(int)y,(int)(x+width),(int)(y+height));
    }

    public void setX(double v) {
        x=v;
        hitbox.set((int)x,(int)y,(int)(x+width),(int)(y+height));
    }

    public void draw(Canvas c) {
        if (image != null) {
            setY(y);
            c.drawBitmap(image, null, hitbox, new Paint());
        } else Log.e("SpriteError", "image not found");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getRight(){
        return x-getWidth();
    }

    public double getBottom(){
        return y-height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
