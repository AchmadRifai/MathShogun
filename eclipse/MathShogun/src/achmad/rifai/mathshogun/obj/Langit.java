package achmad.rifai.mathshogun.obj;

import java.io.IOException;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Langit implements achmad.rifai.mathshogun.beans.Obj {

	private Rect screen;
	private Context ctx;
	private String path;

	public Langit(Rect screen, Context ctx) {
		super();
		this.screen = screen;
		this.ctx = ctx;
		path = "bg.png";
	}

	@Override
	public void update() {}

	@Override
	public Rect kotak() {
		return screen;
	}

	@Override
	public void draw(Canvas c) throws IOException {
		java.io.InputStream i = ctx.getAssets().open(path);
		android.graphics.Bitmap b = BitmapFactory.decodeStream(i);
		c.drawBitmap(b, null, kotak(), null);
		i.close();
	}

}
