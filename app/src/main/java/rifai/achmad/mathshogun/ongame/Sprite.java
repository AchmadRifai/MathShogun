package rifai.achmad.mathshogun.ongame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by ai on 12/03/2018.
 */

public class Sprite {
    public enum SpriteState{
        IDLE, JUMP, FLY, LAND
    }

    public Bitmap image;
    public Context c;
    public Activity a;
    public Rect hitbox;
    public Rect screen;
    private SpriteState ss;
    private int width,height;
    private double x,y;
    public double vx,vy,ax,ay;
    public final double GRAV=3,FRIC=3;
    public boolean byGRAV=false;
    Paint noALiasPaint=new Paint(),borderPaint=new Paint(),vectorPaint=new Paint();

    public Sprite(Bitmap image, Context c, Activity a, Rect hitbox, Rect screen) {
        this.image = image;
        this.c = c;
        this.a = a;
        this.hitbox = hitbox;
        this.screen = screen;
        ss=SpriteState.IDLE;
        initSetting();
    }

    private void initSetting() {
        width=hitbox.width();
        height=hitbox.height();
        x=hitbox.left;
        y=hitbox.top;
        vx=vy=ax=ay=0;
        noALiasPaint.setAntiAlias(false);
        noALiasPaint.setFilterBitmap(false);
        noALiasPaint.setDither(false);
        noALiasPaint.setColor(Color.GREEN);
        borderPaint.setStrokeWidth(10);
        borderPaint.setStyle(Paint.Style.STROKE);
        vectorPaint.setStyle(Paint.Style.STROKE);
        vectorPaint.setStrokeWidth(8);
        vectorPaint.setColor(Color.GREEN);
    }

    public void update(long ellapse) {
        vx+=ax;
        vy+=ay;
        if(byGRAV)vy+=GRAV;
        setX(getX()+vx);
        setY(getY()+vy);
    }

    public void draw(Canvas canvas, long elevation){
        if(image!=null){
            setY(getY());
            canvas.drawBitmap(image,null,hitbox,null);
        }else drawHitbox(canvas,elevation,Color.MAGENTA);
    }

    public void drawVect(Canvas canvas, long elevation, int scalar){
        Path path=new Path();
        path.moveTo(hitbox.centerX(),hitbox.centerY());
        path.lineTo(hitbox.centerX()+(int) vx*scalar,hitbox.centerY()+(int) vx*scalar);
        path.close();
        canvas.drawPath(path,vectorPaint);
    }

    public void drawHitbox(Canvas canvas, long elevation, int color) {
        borderPaint.setColor(color);
        setY(getY());
        canvas.drawRect(hitbox,borderPaint);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        hitboxUpdate();
    }

    private void hitboxUpdate() {
        hitbox.set((int) x,(int) y,width+(int) x,height+(int) y);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        hitboxUpdate();
    }
}
