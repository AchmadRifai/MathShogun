package achmad.rifai.mathshogun.view;

import java.io.IOException;

import achmad.rifai.mathshogun.DashActivity;
import achmad.rifai.mathshogun.obj.Cerita;
import achmad.rifai.mathshogun.obj.Ornament;
import achmad.rifai.mathshogun.obj.Tochi;
import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class CerLayar implements achmad.rifai.mathshogun.beans.Layar {

	private Context ctx;
	private SurfaceHolder h;
	private Rect screen;
	private achmad.rifai.mathshogun.beans.Obj sora, top, mid, bot, gunung, kastil, wit, semak;
	private Cerita cer;
	private boolean maju;

	public CerLayar(Context ctx, SurfaceHolder h, Rect screen) {
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
		cer = new Cerita(ctx, new Rect(0, screen.bottom / 5 * 3, screen.right, screen.bottom));
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
		cer.update();
		maju = !cer.sudah();
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
	public void simpan() {
		if (cer.sudah()) {
			achmad.rifai.mathshogun.beans.Atur a = Tools.getAtur(ctx);
			a.setCerita(true);
			Tools.saveAtur(a, ctx);
			Activity act = (Activity) ctx;
			act.startActivity(new Intent(act,DashActivity.class));
			act.finish();
		}
	}

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
			cer.draw(c);
			h.unlockCanvasAndPost(c);
		}
	}

}
