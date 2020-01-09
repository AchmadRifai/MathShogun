package achmad.rifai.mathshogun.obj;

import java.io.IOException;
import java.io.InputStream;

import achmad.rifai.mathshogun.utils.Tools;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Europe implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private int x, y, width, height, speed, atk;
	private Bentuk pas;
	private State sedang;
	private boolean kiri, last;

	public Europe(Context ctx, int x, int y, int width, int height, int speed) {
		super();
		this.ctx = ctx;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		genEnemy();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Bentuk getPas() {
		return pas;
	}

	public State getSedang() {
		return sedang;
	}

	public void setSedang(State sedang) {
		this.sedang = sedang;
	}

	private void genEnemy() {
		int i = Tools.acak(1, 4);
		pas = i == 1 ? Bentuk.WU : i == 2 ? Bentuk.WEI : Bentuk.SHU;
		atk = 0;
		kiri = last = false;
		sedang = State.RUN;
	}

	public enum State {
		RUN, FIGHT, LOSE, DEAD, WIN
	}

	public enum Bentuk {
		SHU, WEI, WU
	}

	@Override
	public void update() {
		if (sedang == State.RUN || sedang == State.DEAD) {
			kiri = !kiri;
			x -= speed;
		} else if (sedang == State.WIN) {
			if (atk < 2) atk++;
			else atk = 0;
		} else if (sedang == State.LOSE)
			last = !last;
	}

	@Override
	public Rect kotak() {
		return new Rect(x, y, x + width, y + height);
	}

	@Override
	public void draw(Canvas c) throws IOException {
		String path = pathGbr();
		InputStream i = ctx.getAssets().open(path);
		c.drawBitmap(BitmapFactory.decodeStream(i), null, kotak(), null);
		i.close();
		if (sedang == State.LOSE) drawSlash(c);
	}

	private void drawSlash(Canvas c) throws IOException {
		String path = last ? "kiri-1.png" : "kiri-0.png";
		InputStream i = ctx.getAssets().open(path);
		c.drawBitmap(BitmapFactory.decodeStream(i), null, kotak(), null);
		i.close();
	}

	private String pathGbr() {
		String path = "rampok/rampok_";
		if (sedang == State.DEAD) path += "132_";
		else if (sedang == State.FIGHT) path += "55_";
		else if (sedang == State.LOSE) path += "37_";
		else if (sedang == State.RUN) path += kiri ? "72_" : "68_";
		else if (sedang == State.WIN) path += "8" + atk + "_";
		path += pas == Bentuk.SHU ? "green" : pas == Bentuk.WEI ? "blue" : "red";
		path += ".png";
		return path;
	}

}
