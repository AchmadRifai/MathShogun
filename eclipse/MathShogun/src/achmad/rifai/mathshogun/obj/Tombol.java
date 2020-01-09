package achmad.rifai.mathshogun.obj;

import java.io.IOException;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Tombol implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private int x, y, width, height, color;
	private String path;

	public Tombol(Context ctx, int x, int y, int width, int height, int color, String path) {
		super();
		this.ctx = ctx;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.path = path;
	}

	@Override
	public void update() {}

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
		border(c);
	}

	private void border(Canvas c) {
		Paint p = new Paint();
		p.setStyle(Paint.Style.STROKE);
		p.setColor(color);
		c.drawRect(kotak(), p);
	}

}
