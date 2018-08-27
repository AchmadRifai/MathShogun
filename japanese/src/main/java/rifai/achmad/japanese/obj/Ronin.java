package rifai.achmad.japanese.obj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import rifai.achmad.dbne.Work;
import rifai.achmad.japanese.R;

public class Ronin {
    private Context ctx;
    private Rect screen,hitbox;
    private int mode,width,height,tipe;
    private Bitmap img;
    private boolean kanan;
    private Status status;
    private Dojo power;

    public Ronin(Context ctx, Rect screen) {
        this.ctx = ctx;
        this.screen = screen;
        mode=1;
        status=Status.RUN;
        kanan=false;
        sokutei();
    }

    public Dojo getPower() {
        return power;
    }

    public void setPower(Dojo power) {
        this.power = power;
    }

    private void sokutei() {
        width=screen.width()/7;
        height=screen.height()/4;
        hitbox=new Rect(screen.width()-(screen.width()/100),screen.height()/2+(screen.height()/20),
                screen.width()-(screen.width()/100)+width,
                screen.height()/2+(screen.height()/20)+height);
        tipe= Work.genRandom(0, 2);
        loadPower();
    }

    private void loadPower() {
        power=new Dojo();
        JsonParser p=new JsonParser(); try {
            InputStreamReader i=new InputStreamReader(ctx.getAssets().open("ronin.json"));
            JsonArray a=p.parse(i).getAsJsonArray();
            JsonObject o=a.get(tipe).getAsJsonObject();
            power.setColor(o.get("color").getAsString());
            power.setPoint(o.get("point").getAsInt());
            power.setPower(o.get("power").getAsInt());
            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Canvas c) {
        gambar();
        c.drawBitmap(img,null,hitbox,null);
    }

    private void gambar() {
        if("red".equals(power.getColor()))aka();
        else if("blue".equals(power.getColor()))aoi();
        else if("green".equals(power.getColor()))midori();
    }

    private void midori() {
        if (status == Status.WIN) {
            if (mode == 1)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_80_green);
            else if (mode == 2)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_81_green);
            else if (mode == 3)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_82_green);
        } else if (status == Status.RUN) {
            if (kanan) img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_72_green);
            else img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_68_green);
        } else if (status == Status.DIED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_132_green);
        else if (status == Status.FIGHT)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_55_green);
        else if (status == Status.INJURED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_37_green);
    }

    private void aoi() {
        if (status == Status.WIN) {
            if (mode == 1)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_80_blue);
            else if (mode == 2)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_81_blue);
            else if (mode == 3)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_82_blue);
        } else if (status == Status.RUN) {
            if (kanan) img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_72_blue);
            else img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_68_blue);
        } else if (status == Status.DIED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_132_blue);
        else if (status == Status.FIGHT)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_55_blue);
        else if (status == Status.INJURED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_37_blue);
    }

    private void aka() {
        if (status == Status.WIN) {
            if (mode == 1)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_80_red);
            else if (mode == 2)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_81_red);
            else if (mode == 3)
                img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_82_red);
        } else if (status == Status.RUN) {
            if (kanan) img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_72_red);
            else img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_68_red);
        } else if (status == Status.DIED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_132_red);
        else if (status == Status.FIGHT)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_55_red);
        else if (status == Status.INJURED)
            img = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.rampok_37_red);
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
