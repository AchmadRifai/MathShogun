package rifai.achmad.japanese;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

import org.joda.time.DateTime;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Work;
import rifai.achmad.dbne.dao.DAONilai;
import rifai.achmad.dbne.entity.Nilai;
import rifai.achmad.japanese.obj.Dan;
import rifai.achmad.japanese.obj.Kenjutsu;
import rifai.achmad.japanese.obj.Ronin;
import rifai.achmad.japanese.obj.Shogun;
import rifai.achmad.japanese.obj.Tochi;

public class GameModel {
    private Context ctx;
    private String nama;
    private Rect screen;
    private SurfaceHolder holder;
    private Kondisi kondisi;
    private int walkSpeed,enemySpeed;
    private Tochi[]tanah;
    private Shogun player;
    private Ronin enemy;
    private Kenjutsu attack,damage;
    private Dan up;

    public GameModel(Context ctx, String nama, Rect screen, SurfaceHolder holder) {
        this.ctx = ctx;
        this.nama = nama;
        this.screen = screen;
        this.holder = holder;
        kondisi=Kondisi.RUN;
        ichiban();
    }

    private void ichiban() {
        walkSpeed=screen.width()/30;
        enemySpeed=screen.width()/20;
        int l=2;
        tanah=new Tochi[l];
        for(int x=0;x<l;x++){
            tanah[x]=new Tochi(ctx,screen);
            if(x!=0)tanah[x].setX(tanah[x-1].right());
        } player=new Shogun(ctx,screen);
        enemy=new Ronin(ctx,screen);
        attack=new Kenjutsu(ctx,enemy.getHitbox());
        damage=new Kenjutsu(ctx,player.getHitbox());
        up=new Dan(ctx,player.getHitbox());
    }

    public void saveGame(){
        ConnHelper ch=new ConnHelper(ctx);
        DAONilai dao=new DAONilai(ch);
        Nilai n=new Nilai();
        n.setPemain(nama);
        n.setTgl(DateTime.now());
        n.setMode(Work.getMyMode(ctx));
        n.setNyawa(player.getNyawa());
        n.setLevel(player.getLevel());
        n.setGold(player.getGold());
        n.setExp(player.getExp());
        dao.insert(n);
        ch.close();
    }

    public void jalan() {
        for(int x=0;x<tanah.length;x++){
            tanah[x].setX(tanah[x].getX()-walkSpeed);
            if(x==0){
                if(tanah[x].right()<=0)tanah[x].setX(0);
            }else tanah[x].setX(tanah[x-1].right());
        } player.jalan();
        enemy.setKanan(!enemy.isKanan());
        if(enemy.getStatus()!= Ronin.Status.DIED)enemy.setX(enemy.getX()-enemySpeed);
        else enemy.setX(enemy.getX()-walkSpeed);
        if(0>=enemy.getRight())enemy=new Ronin(ctx,screen);
        if(player.getHitbox().right>=enemy.getHitbox().left&&enemy.getStatus()!= Ronin.Status.DIED)
            kondisi=Kondisi.FIGHTING;
        aturKondisi();
    }

    private void aturKondisi() {
        if(kondisi==Kondisi.RUN){
            player.setStatus(Shogun.Status.RUN);
            if(enemy.getStatus()!= Ronin.Status.DIED)enemy.setStatus(Ronin.Status.RUN);
        }else if(kondisi==Kondisi.FIGHTING){
            player.setStatus(Shogun.Status.FIGHT);
            if(enemy.getStatus()!= Ronin.Status.DIED)enemy.setStatus(Ronin.Status.FIGHT);
        }else if(kondisi==Kondisi.LOST){
            player.setStatus(Shogun.Status.DIED);
            if(enemy.getStatus()!= Ronin.Status.DIED)enemy.setStatus(Ronin.Status.FIGHT);
        }
    }

    public void repaint() {
        Canvas c=holder.lockCanvas();
        if(c!=null) {
            Bitmap i= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.bg);
            c.drawBitmap(i,null,screen,null);
            paint(c);
            holder.unlockCanvasAndPost(c);
        }
    }

    private void paint(Canvas c) {
        for(Tochi t:tanah)t.draw(c);
        player.draw(c);
        enemy.draw(c);
        damage.draw(c);
        attack.draw(c);
        up.draw(c);
        fillNyawa(c);
        borderNyawa(c);
        fillExp(c);
        borderExp(c);
        levelDraw(c);
        goldDraw(c);
    }

    private void goldDraw(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(255,215,0));
        p.setTextSize(20);
        p.setTypeface(Typeface.createFromAsset(ctx.getAssets(),"Almost Japanese Comic.ttf"));
        int left=screen.height()/100+70;
        c.drawText(""+player.getGold()+" Points",5,left,p);
    }

    private void levelDraw(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        p.setTextSize(20);
        p.setTypeface(Typeface.createFromAsset(ctx.getAssets(),"Almost Japanese Comic.ttf"));
        int left=screen.height()/100+45;
        c.drawText("Level "+player.getLevel(),5,left,p);
    }

    private void borderExp(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GREEN);
        int top=screen.height()/100-5,left=screen.width()/100+145;
        Rect r=new Rect(left,top,left+110,top+30);
        c.drawRect(r,p);
    }

    private void fillExp(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GREEN);
        int top=screen.height()/100,left=screen.width()/100+150;
        Rect r=new Rect(left,top,left+(100*player.getExp()/player.getBatas_exp()),top+20);
        c.drawRect(r,p);
    }

    private void borderNyawa(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        int top=screen.height()/100-5,left=screen.width()/100-5;
        Rect r=new Rect(left,top,left+110,top+30);
        c.drawRect(r,p);
    }

    private void fillNyawa(Canvas c) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        int top=screen.height()/100,left=screen.width()/100;
        Rect r=new Rect(left,top,left+(player.getNyawa()),top+20);
        c.drawRect(r,p);
    }

    public Kondisi getKondisi() {
        return kondisi;
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void injured() {
        if(player.getNyawa()>=enemy.getPower().getPower())player.setNyawa(player.getNyawa()-
                enemy.getPower().getPower());
        else player.setNyawa(0);
    }

    public void shogunSLash1() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(1);
        player.setMode(1);
        attack.setShow(true);
    }

    public void shogunSlash2() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(2);
        player.setMode(1);
        attack.setShow(true);
    }

    public void shogunSlash3() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(1);
        player.setMode(2);
        attack.setShow(true);
    }

    public void shogunSlash4() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(2);
        player.setMode(2);
        attack.setShow(true);
    }

    public void shogunSlash5() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(1);
        player.setMode(3);
        attack.setShow(true);
    }

    public void shogunSlash6() {
        player.setStatus(Shogun.Status.WIN);
        enemy.setStatus(Ronin.Status.INJURED);
        attack.setHitbox(enemy.getHitbox());
        attack.setMode(2);
        player.setMode(3);
        attack.setShow(true);
    }

    public void reward() {
        player.setGold(player.getGold()+(enemy.getPower().getPoint()*player.getLevel()));
        player.setExp(player.getExp()+(enemy.getPower().getPoint()*player.getLevel()));
        enemy.setStatus(Ronin.Status.DIED);
    }

    public void leveling() {
        up.setShow(true);
        attack.setShow(false);
        player.setStatus(Shogun.Status.STAY);
    }

    public void normaling() {
        attack.setShow(false);
        damage.setShow(false);
        up.setShow(false);
        if(player.getLevel()==6||player.getNyawa()==0)kondisi=Kondisi.LOST;
        else kondisi=Kondisi.RUN;
    }

    public void roninSlash1() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(1);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(1);
        damage.setHitbox(player.getHitbox());
    }

    public void roninSlash2() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(2);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(1);
        damage.setHitbox(player.getHitbox());
    }

    public void roninSlash3() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(1);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(2);
        damage.setHitbox(player.getHitbox());
    }

    public void roninSLash4() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(2);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(2);
        damage.setHitbox(player.getHitbox());
    }

    public void roninSlash5() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(1);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(3);
        damage.setHitbox(player.getHitbox());
    }

    public void roninSlash6() {
        player.setStatus(Shogun.Status.STAY);
        damage.setMode(2);
        damage.setShow(true);
        enemy.setStatus(Ronin.Status.WIN);
        enemy.setMode(3);
        damage.setHitbox(player.getHitbox());
    }

    public enum Kondisi {
        RUN,FIGHTING,LOST
    }
}
