package achmad.rifai.mathshogun.obj;

import java.io.IOException;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Petak implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private int x, y, width, height;
	private String path;

	public Petak(Context ctx, int x, int y, int width, int height, String path) {
		super();
		this.ctx = ctx;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.path = path;
	}

	@Override
	public void update() {}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public Rect kotak() {
		return new Rect(x, y, x + width, y + height);
	}

	@Override
	public void draw(Canvas c) throws IOException {
		java.io.InputStream i = ctx.getAssets().open(path);
		android.graphics.Bitmap b = BitmapFactory.decodeStream(i);
		c.drawBitmap(b, null, kotak(), null);
		i.close();
	}

}
