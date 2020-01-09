package achmad.rifai.mathshogun.obj;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Lakon implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private int x, y, width, height, atk;
	private boolean kiri, last;
	private State sedang;

	public Lakon(Context ctx, int x, int y, int width, int height) {
		super();
		this.ctx = ctx;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		sedang = State.RUN;
		kiri = last = false;
		atk = 0;
	}

	public State getSedang() {
		return sedang;
	}

	public void setSedang(State sedang) {
		this.sedang = sedang;
	}

	public enum State {
		RUN, FIGHT, LOSE, DEAD, WIN, UP
	}

	@Override
	public void update() {
		if (sedang == State.RUN) kiri = !kiri;
		else if (sedang == State.LOSE) last = !last;
		else if (sedang == State.WIN) {
			if (atk < 2) atk++;
			else atk = 0;
		}
	}

	@Override
	public Rect kotak() {
		return new Rect(x, y, x + width, y + height);
	}

	@Override
	public void draw(Canvas c) throws IOException {
		String path = genPath();
		InputStream i = ctx.getAssets().open(path);
		c.drawBitmap(BitmapFactory.decodeStream(i), null, kotak(), null);
		i.close();
		if (sedang == State.LOSE) drawSlash(c);
		else if (sedang == State.UP) drawLvl(c);
	}

	private void drawLvl(Canvas c) throws IOException {
		String path = "up.png";
		InputStream i = ctx.getAssets().open(path);
		c.drawBitmap(BitmapFactory.decodeStream(i), null, kotak(), null);
		i.close();
	}

	private void drawSlash(Canvas c) throws IOException {
		String path = last ? "kiri-1.png" : "kiri-0.png";
		InputStream i = ctx.getAssets().open(path);
		c.drawBitmap(BitmapFactory.decodeStream(i), null, kotak(), null);
		i.close();
	}

	private String genPath() {
		String s = "play/samurai-";
		if (sedang == State.FIGHT || sedang == State.LOSE || sedang == State.UP) s += "12.png";
		else if (sedang == State.DEAD) s += "35.png";
		else if (sedang == State.RUN) s += kiri ? "14.png" : "13.png";
		else if (sedang == State.WIN) s += (15 + atk) + ".png";
		return s;
	}

}
