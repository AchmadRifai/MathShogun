package achmad.rifai.mathshogun.view;

import java.io.IOException;

import achmad.rifai.mathshogun.InfoActivity;
import achmad.rifai.mathshogun.OptActivity;
import achmad.rifai.mathshogun.ScoreActivity;
import achmad.rifai.mathshogun.obj.Ornament;
import achmad.rifai.mathshogun.obj.Tochi;
import achmad.rifai.mathshogun.obj.Tombol;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class DashLayar implements achmad.rifai.mathshogun.beans.Layar {

	private Context ctx;
	private SurfaceHolder h;
	private Rect screen;
	private achmad.rifai.mathshogun.beans.Obj sora, top, mid, bot, gunung, kastil, wit, semak, play, score, opt, info;
	private boolean maju;

	public DashLayar(Context ctx, SurfaceHolder h, Rect screen) {
		super();
		this.ctx = ctx;
		this.h = h;
		this.screen = screen;
	}

	@Override
	public void awalan() {
		drawBlank();
		tanah();
		ornamen();
		tombol();
	}

	private void tombol() {
		int t = screen.bottom / 5 * 3;
		play = new Tombol(ctx, screen.right / 2, t - (screen.bottom / 10), screen.right / 15, screen.bottom / 10, Color.BLUE,
				"tiles/terus.png");
		score = new Tombol(ctx, screen.right / 10, t - (screen.bottom / 10), screen.right / 15, screen.bottom / 10, Color.GREEN,
				"tiles/gold.png");
		opt = new Tombol(ctx, screen.right / 3 * 2, t - (screen.bottom / 10), screen.right / 15, screen.bottom / 10, Color.RED,
				"tiles/warning.png");
		info = new Tombol(ctx, screen.right / 10 * 9, t - (screen.bottom / 10), screen.right / 15, screen.bottom / 10, Color.YELLOW,
				"tiles/info.png");
	}

	private void ornamen() {
		int t = screen.bottom / 5 * 3;
		gunung = new Ornament(ctx, -3, t - (screen.bottom / 2), screen.right / 2, screen.bottom / 2, screen.right / 2, 0, 
				"tiles/gunung-2.png");
		kastil = new Ornament(ctx, -3, t - (screen.bottom / 3), screen.right / 4, screen.bottom / 3, screen.right / 3, 0, 
				"tiles/castle.png");
		wit = new Ornament(ctx, -3, t - (screen.bottom / 4), screen.right / 5, screen.bottom / 4, 0, 0, "tiles/tree-2.png");
		semak = new Ornament(ctx, -3, t - (screen.bottom / 7), screen.right / 10, screen.bottom / 7, 0, 0, "tiles/brush.png");
	}

	private void tanah() {
		int t = screen.bottom / 5 * 3;
		top = new Tochi(ctx, 0, t, screen.right / 10, screen.bottom / 5, 0, "tiles/land-2.png");
		mid = new Tochi(ctx, -1, top.kotak().bottom, screen.right / 10, screen.bottom / 5, 0, "tiles/deep-5.png");
		bot = new Tochi(ctx, -2, top.kotak().bottom + (screen.bottom / 10), screen.right / 10, screen.bottom / 5,
				screen.right / 200, "tiles/water-1.png");
	}

	private void drawBlank() {
		Canvas c = h.lockCanvas();
		if (c != null) {
			c.drawColor(Color.WHITE);
			h.unlockCanvasAndPost(c);
		} maju = true;
		sora = new achmad.rifai.mathshogun.obj.Langit(screen, ctx);
	}

	@Override
	public boolean terus() {
		return maju;
	}

	@Override
	public boolean stun() {
		return false;
	}

	@Override
	public void sentuh(MotionEvent e) {
		int x = (int) e.getX(), y = (int) e.getY();
		if (play.kotak().contains(x, y)) {
			new achmad.rifai.mathshogun.utils.NpDialog(ctx).show();
		} else if (score.kotak().contains(x, y)) {
			Activity a = (Activity) ctx;
			a.startActivity(new Intent(a, ScoreActivity.class));
			a.finish();
		} else if (opt.kotak().contains(x, y)) {
			Activity a = (Activity) ctx;
			a.startActivity(new Intent(a, OptActivity.class));
			a.finish();
		} else if (info.kotak().contains(x, y)) {
			Activity a = (Activity) ctx;
			a.startActivity(new Intent(a, InfoActivity.class));
			a.finish();
		}
	}

	@Override
	public void update() {
		sora.update();
		top.update();
		mid.update();
		bot.update();
		gunung.update();
		kastil.update();
		wit.update();
		semak.update();
	}

	@Override
	public void hentikan() {
		maju = false;
	}

	@Override
	public void simpan() {}

	@Override
	public void draw() throws IOException {
		Canvas c = h.lockCanvas();
		if (c != null) {
			sora.draw(c);
			mid.draw(c);
			bot.draw(c);
			top.draw(c);
			gunung.draw(c);
			kastil.draw(c);
			wit.draw(c);
			semak.draw(c);
			play.draw(c);
			info.draw(c);
			score.draw(c);
			opt.draw(c);
			h.unlockCanvasAndPost(c);
		}
	}

}
