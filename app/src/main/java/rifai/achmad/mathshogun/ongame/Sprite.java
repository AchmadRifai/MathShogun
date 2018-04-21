package rifai.achmad.mathshogun.ongame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * Created by ashura on 21/04/18.
 */

public class Sprite {
    public enum SpriteStates{
        FLY,IDLE,JUMP,LAND
    }public Bitmap image;
    public Context ctx;
    private Rect hitbox;
    public Rect screen;
    private SpriteStates st;
    private int width,height;
    private double x,y;
    public double vx,vy,ay,ax;
    public final double GRAV=4,FRIC=3;
    public boolean affectedByGrav=false;
    Paint noALiasPaint=new Paint(),borderPaint=new Paint(),vectorPaint=new Paint();

    public Sprite(Bitmap image, Context ctx, Rect hitbox, Rect screen) {
        this.image = image;
        this.ctx = ctx;
        this.hitbox = hitbox;
        this.screen = screen;
        st=SpriteStates.IDLE;
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

    public Rect getHitbox() {
        return hitbox;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRight(){
        return x+width;
    }

    public double getBottom(){
        return y+height;
    }

    public void setX(double x){
        this.x=x;
        hitbox.set((int)x,(int)y,(int)x+width,(int)y+height);
    }

    public void setY(double y) {
        this.y = y;
        hitbox.set((int)x,(int)y,(int)x+width,(int)y+height);
    }

    public void update(long elapsed){
        vx+=ax;
        vy+=ay;
        if(affectedByGrav)vy+=GRAV;
        setX(x+vx);
        setY(y+vy);
    }

    public void draw(Canvas canvas,long elevation){
        if(image!=null){
            setY(y);
            canvas.drawBitmap(image,null,hitbox,null);
        }else drawHitbox(canvas,elevation,Color.MAGENTA);
    }

    public void drawHitbox(Canvas canvas, long elevation, int magenta) {
        borderPaint.setColor(magenta);
        setY(y);
        canvas.drawRect(hitbox,borderPaint);
    }

    public void drawVect(Canvas canvas,long elevation,int scalar){
        Path path=new Path();
        path.moveTo(hitbox.centerX(),hitbox.centerY());
        path.lineTo(hitbox.centerX()+(int)vx*scalar,hitbox.centerY()+(int)vy*scalar);
        path.close();
        canvas.drawPath(path,vectorPaint);
    }
}
