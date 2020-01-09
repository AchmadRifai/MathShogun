package achmad.rifai.mathshogun.obj;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Ornament implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private int x, y, width, height, padding, speed;
	private String path;
	private java.util.List<Petak> l;

	public Ornament(Context ctx, int x, int y, int width, int height, int padding, int speed, String path) {
		super();
		this.ctx = ctx;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.padding = padding;
		this.speed = speed;
		this.path = path;
		awalan();
	}

	private void awalan() {
		l = new java.util.LinkedList<Petak>();
		for (int i = 0; i < 12; i++) {
			Petak p = new Petak(ctx, x, y, width, height, path);
			if (i > 0) {
				Petak p2 = l.get(i - 1);
				p.setX(p2.kotak().right + padding);
			} l.add(p);
		}
	}

	@Override
	public void update() {
		for (Petak p : l) p.setX(p.getX() - speed);
		Petak p1 = l.get(0), p2 = l.get(11), p3 = new Petak(ctx, p2.kotak().right + padding, y, width, height, path);
		if (0 >= p1.kotak().right) {
			l.remove(0);
			l.add(p3);
		}
	}

	@Override
	public Rect kotak() {
		Petak p1 = l.get(0), p2 = l.get(11);
		return new Rect(p1.getX(), y, p2.kotak().right, y + height);
	}

	@Override
	public void draw(Canvas c) throws IOException {
		for (Petak p:l)
			p.draw(c);
	}

}
