package achmad.rifai.mathshogun.obj;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Inochi implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private Rect screen;
	private int gold, lvl, exp, hp, maxHp, maxExp;

	public Inochi(Context ctx, Rect screen) {
		super();
		this.ctx = ctx;
		this.screen = screen;
		gen();
	}

	private void gen() {
		lvl = 1;
		gold = exp = 0;
		hp = maxHp = maxExp = 100;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	@Override
	public void update() {}

	@Override
	public Rect kotak() {
		return screen;
	}

	@Override
	public void draw(Canvas c) throws IOException {
		drawHp(c);
		drawExp(c);
		drawLvl(c);
		drawGold(c);
	}

	private void drawGold(Canvas c) throws IOException {
		int top = 10 + (screen.bottom * 3 / 10), left = screen.right / 44, ke = 0;
		if (ke == 0) {
			Rect r = new Rect(10 + (ke++ * left), top, 10 + (ke * left), top + (screen.bottom / 42));
			String path = "tiles/gold.png";
			java.io.InputStream i = ctx.getAssets().open(path);
			c.drawBitmap(BitmapFactory.decodeStream(i), null, r, null);
			i.close();
			ke++;
		} String s = "" + gold;
		for (char ch : s.toCharArray()) {
			Rect r = new Rect(10 + (ke++ * left), top, 10 + (ke * left), top + (screen.bottom / 42));
			String path = "huds/";
			if (ch == '.') path += "dot.png";
            else if (ch == ' ') path += "space.png";
            else path += ch + ".png";
			InputStream i = ctx.getAssets().open(path);
			c.drawBitmap(BitmapFactory.decodeStream(i), null, r, null);
			i.close();
		}
	}

	private void drawLvl(Canvas c) throws IOException {
		String s = "level " + lvl;
		int top = 10 + (screen.bottom / 5), left = screen.right / 44, ke = 0;
		for (char ch : s.toCharArray()) {
			Rect r = new Rect(10 + (ke++ * left), top, 10 + (ke * left), top + (screen.bottom / 42));
			String path = "huds/";
			if (ch == '.') path += "dot.png";
            else if (ch == ' ') path += "space.png";
            else path += ch + ".png";
			InputStream i = ctx.getAssets().open(path);
			c.drawBitmap(BitmapFactory.decodeStream(i), null, r, null);
			i.close();
		}
	}

	private void drawExp(Canvas c) {
		Paint p1 = new Paint(), p2 = new Paint();
		Rect r = new Rect(10, 10, 10 + (screen.right / 10), 10 + (screen.bottom / 15)), r1, r2;
		r1 = new Rect(10, r.bottom + 10, 10 + (screen.right / 10), (r.bottom + 10) + (screen.bottom / 15));
		r2 = new Rect(20, 20 + r.bottom, 20 + (screen.right / 10) * exp / maxExp, r.bottom + (screen.bottom / 15));
		p1.setStyle(Paint.Style.STROKE);
		p1.setColor(Color.GREEN);
		p2.setStyle(Paint.Style.FILL);
		p2.setColor(Color.GREEN);
		c.drawRect(r1, p1);
		c.drawRect(r2, p2);
	}

	private void drawHp(Canvas c) {
		Paint p1 = new Paint(), p2 = new Paint();
		Rect r1 = new Rect(10, 10, 10 + (screen.right / 10), 10 + (screen.bottom / 15)), r2;
		r2 = new Rect(20, 20, (screen.right / 10) * hp / maxHp, screen.bottom / 15);
		p1.setStyle(Paint.Style.STROKE);
		p2.setStyle(Paint.Style.FILL);
		p1.setColor(Color.RED);
		p2.setColor(Color.RED);
		c.drawRect(r1, p1);
		c.drawRect(r2, p2);
	}

}
