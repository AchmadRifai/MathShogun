package achmad.rifai.mathshogun.view;

import java.io.IOException;
import java.util.Date;

import achmad.rifai.mathshogun.ScoreActivity;
import achmad.rifai.mathshogun.beans.Atur;
import achmad.rifai.mathshogun.beans.Nilai;
import achmad.rifai.mathshogun.obj.Europe;
import achmad.rifai.mathshogun.obj.Inochi;
import achmad.rifai.mathshogun.obj.Lakon;
import achmad.rifai.mathshogun.obj.Ornament;
import achmad.rifai.mathshogun.obj.Tochi;
import achmad.rifai.mathshogun.utils.Soal;
import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class PlayLayar implements achmad.rifai.mathshogun.beans.Layar, achmad.rifai.mathshogun.beans.Korektor {

	private Context ctx;
	private SurfaceHolder h;
	private Rect screen;
	private String np;
	private boolean maju, jeda;
	private achmad.rifai.mathshogun.beans.Obj sora, top, mid, bot, gunung, kastil, wit, semak;
	private Europe musuh;
	private Lakon iki;
	private Inochi stat;
	private Date tgl;
	private Soal.Tingkat dif;

	public PlayLayar(Context ctx, SurfaceHolder h, Rect screen, String np) {
		super();
		this.ctx = ctx;
		this.h = h;
		this.screen = screen;
		this.np = np;
	}

	@Override
	public void benar() throws IOException, InterruptedException {
		musuh.setSedang(Europe.State.LOSE);
		iki.setSedang(Lakon.State.WIN);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				draw();
				musuh.update();
				Thread.sleep(100);
			} iki.update();
		} int rew = musuh.getPas() == Europe.Bentuk.SHU ? 30 : musuh.getPas() == Europe.Bentuk.WEI ? 20 : 10;
		rew *= stat.getLvl();
		stat.setExp(stat.getExp() + rew);
		stat.setGold(stat.getGold() + rew);
		musuh.setSedang(Europe.State.DEAD);
		musuh.setSpeed(screen.right / 50);
		if (stat.getExp() >= stat.getMaxExp()) naikLevel();
		iki.setSedang(Lakon.State.RUN);
		jeda = false;
	}

	private void naikLevel() throws IOException, InterruptedException {
		iki.setSedang(Lakon.State.UP);
		stat.setLvl(stat.getLvl() + 1);
		stat.setExp(stat.getExp() - stat.getMaxExp());
		stat.setMaxExp(stat.getMaxExp() + 100);
		draw();
		Thread.sleep(100);
	}

	@Override
	public void salah() throws IOException, InterruptedException {
		musuh.setSedang(Europe.State.WIN);
		iki.setSedang(Lakon.State.LOSE);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				draw();
				iki.update();
				Thread.sleep(100);
			} musuh.update();
		} stat.setHp(stat.getHp() - 20);
		if (0 >= stat.getHp()) iki.setSedang(Lakon.State.DEAD);
		else iki.setSedang(Lakon.State.RUN);
		musuh.setSedang(Europe.State.RUN);
		jeda = false;
	}

	@Override
	public void awalan() {
		drawBlank();
		tanah();
		ornament();
		mainkan();
	}

	private void mainkan() {
		int t = screen.bottom / 5 * 3;
		musuh = new Europe(ctx, screen.right, t - (screen.bottom / 10), screen.right / 10, screen.bottom / 10, 
				screen.right / 20);
		iki = new Lakon(ctx, screen.right / 10, t - (screen.bottom / 10), screen.right / 10, screen.bottom / 10);
		stat = new Inochi(ctx, screen);
	}

	private void ornament() {
		int t = screen.bottom / 5 * 3;
		gunung = new Ornament(ctx, -3, t - (screen.bottom / 2), screen.right / 2, screen.bottom / 2, screen.right / 2, 
				screen.right / 50, "tiles/gunung-2.png");
		kastil = new Ornament(ctx, -3, t - (screen.bottom / 3), screen.right / 4, screen.bottom / 3, screen.right / 3, 
				screen.right / 50, "tiles/castle.png");
		wit = new Ornament(ctx, -3, t - (screen.bottom / 4), screen.right / 5, screen.bottom / 4, 0, screen.right / 50, 
				"tiles/tree-2.png");
		semak = new Ornament(ctx, -3, t - (screen.bottom / 7), screen.right / 10, screen.bottom / 7, 0, screen.right / 50, 
				"tiles/brush.png");
	}

	private void tanah() {
		int t = screen.bottom / 5 * 3;
		top = new Tochi(ctx, 0, t, screen.right / 10, screen.bottom / 5, screen.right / 50, "tiles/land-2.png");
		mid = new Tochi(ctx, -1, top.kotak().bottom, screen.right / 10, screen.bottom / 5, screen.right / 50, 
				"tiles/deep-5.png");
		bot = new Tochi(ctx, -2, top.kotak().bottom + (screen.bottom / 10), screen.right / 10, screen.bottom / 5,
				screen.right / 45, "tiles/water-1.png");
	}

	private void drawBlank() {
		tgl = new Date();
		Canvas c = h.lockCanvas();
		if (c != null) {
			c.drawColor(Color.WHITE);
			h.unlockCanvasAndPost(c);
		} maju = true;
		sora = new achmad.rifai.mathshogun.obj.Langit(screen, ctx);
		jeda = false;
		Atur a = Tools.getAtur(ctx);
		dif = a.getDif();
	}

	@Override
	public boolean terus() {
		return maju;
	}

	@Override
	public boolean stun() {
		return jeda;
	}

	@Override
	public void sentuh(MotionEvent e) {}

	@Override
	public void update() {
		if (!jeda) {
			sora.update();
			mid.update();
			bot.update();
			top.update();
			gunung.update();
			kastil.update();
			wit.update();
			semak.update();
			musuh.update();
			iki.update();
			if (0 >= musuh.kotak().right) {
				int t = screen.bottom / 5 * 3;
				musuh = new Europe(ctx, screen.right, t - (screen.bottom / 10), screen.right / 10, screen.bottom / 10, 
						screen.right / 20);
			} if (musuh.getSedang() != Europe.State.DEAD && iki.kotak().right >= musuh.getX()) {
				musuh.setSedang(Europe.State.FIGHT);
				iki.setSedang(Lakon.State.FIGHT);
			}
		}
	}

	@Override
	public void hentikan() {
		maju = false;
	}

	@Override
	public void simpan() {
		Nilai n = new Nilai(np, dif, tgl, stat.getGold(), stat.getLvl(), stat.getHp(), stat.getExp());
		Tools.addNilai(ctx, n);
		Activity a = (Activity) ctx;
		a.startActivity(new Intent(a, ScoreActivity.class));
		a.finish();
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
			iki.draw(c);
			musuh.draw(c);
			stat.draw(c);
			h.unlockCanvasAndPost(c);
		} if (musuh.getSedang() == Europe.State.FIGHT && iki.getSedang() == Lakon.State.FIGHT) {
			new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					tarung();
				}});
			jeda = true;
		} if (iki.getSedang() == Lakon.State.DEAD || 5 <= stat.getLvl()) {
			maju = false;
			jeda = true;
		}
	}

	private void tarung() {
		Soal s = Tools.buatSoal(dif, stat.getLvl());
		new achmad.rifai.mathshogun.utils.SoalDialog(ctx, s, this).show();
	}

}
