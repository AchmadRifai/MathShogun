package achmad.rifai.mathshogun.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import achmad.rifai.mathshogun.beans.Atur;
import achmad.rifai.mathshogun.beans.Nilai;
import achmad.rifai.mathshogun.utils.Soal.Tingkat;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class Tools {

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void imersiving(Window w, ActionBar bar) {
		if (bar != null)
			bar.hide();
		w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}

	public static boolean wesCerito(Context ctx) {
		Atur a = getAtur(ctx);
		return a != null ? a.isCerita() : false;
	}

	public static Atur getAtur(Context ctx) {
		Atur a = null;
		SaveHelper sh = new SaveHelper(ctx);
		SQLiteDatabase d = sh.getReadableDatabase();
		Cursor c = d.query("atur", null, null, null, null, null, null);
		if (c.moveToFirst()) a = new Atur(c);
		c.close();
		d.close();
		sh.close();
		return a;
	}

	public static void saveAtur(Atur a, Context ctx) {
		ContentValues cv = a.buatCV();
		SaveHelper sh = new SaveHelper(ctx);
		SQLiteDatabase d = sh.getWritableDatabase();
		d.update("atur", cv, null, null);
		d.close();
		sh.close();
	}

	public static List<Nilai> getNilai(Context ctx) {
		List<Nilai> l = new java.util.LinkedList<Nilai>();
		SaveHelper sh = new SaveHelper(ctx);
		SQLiteDatabase d = sh.getReadableDatabase();
		Cursor c = d.query("nilai", null, null, null, null, null, null);
		if (c.moveToFirst()) {
			l.add(new Nilai(c));
			while (c.moveToNext()) l.add(new Nilai(c));
		} c.close();
		d.close();
		sh.close();
		urutkan(l);
		return l;
	}

	private static void urutkan(List<Nilai> l) {
		Collections.sort(l, new Comparator<Nilai>() {
			@Override
			public int compare(Nilai o1, Nilai o2) {
				if (o1.getTgl().before(o2.getTgl())) return 1;
				else if (o1.getTgl().before(o2.getTgl())) return -1;
				return 0;
			}});
		Collections.sort(l, new Comparator<Nilai>() {
			@Override
			public int compare(Nilai o1, Nilai o2) {
				int a = Tools.akumNilai(o1), b = Tools.akumNilai(o2);
				if (a > b) return -1;
				else if (a < b) return 1;
				return 0;
			}});
	}

	public static int akumNilai(Nilai n) {
		int i = n.getGold() + n.getHp();
		if (0 < n.getExp()) i *= n.getExp();
		i *= n.getLvl();
		i *= n.getDif() == Tingkat.SULIT ? 3 : n.getDif() == Tingkat.SEDANG ? 2 : 1;
		return i;
	}

	public static int acak(int i, int j) {
		Random r = new Random();
		return i + r.nextInt(j - i);
	}

	public static Soal buatSoal(Tingkat dif, int lvl) {
		Soal s = new Soal();
		int ongko = dif == Tingkat.SULIT ? 10 : 5;
		s.setOngko1(acak(lvl > 1 ? (lvl - 1) * ongko : 1, lvl * ongko));
		s.setOngko2(acak(lvl > 1 ? (lvl - 1) * ongko : 1, lvl * ongko));
		while (s.getOngko2() > s.getOngko1())
			s.setOngko2(acak(lvl > 1 ? (lvl - 1) * ongko : 1, lvl * ongko));
		int op = dif == Tingkat.MUDAH ? acak(1, 2) : acak(1, 5);
		s.setOp(1 == op ? Soal.Operasi.TAMBAH : 2 == op ? Soal.Operasi.KURANG : 
			3 == op ? Soal.Operasi.KALI : Soal.Operasi.BAGI);
		s.setJawab(acak(1, 4));
		tataPilihanSoal(s);
		return s;
	}

	private static void tataPilihanSoal(Soal s) {
		int j = s.jawabe();
		if (1 == s.getJawab()) {
			s.setPilA(j);
			s.setPilB(lainJawab(j));
			s.setPilC(lainJawab(j));
			s.setPilD(lainJawab(j));
		} else if (2 == s.getJawab()) {
			s.setPilA(lainJawab(j));
			s.setPilB(j);
			s.setPilC(lainJawab(j));
			s.setPilD(lainJawab(j));
		} else if (3 == s.getJawab()) {
			s.setPilA(lainJawab(j));
			s.setPilB(lainJawab(j));
			s.setPilC(j);
			s.setPilD(lainJawab(j));
		} else if (4 == s.getJawab()) {
			s.setPilA(lainJawab(j));
			s.setPilB(lainJawab(j));
			s.setPilC(lainJawab(j));
			s.setPilD(j);
		}
	}

	private static int lainJawab(int j) {
		int a = acak(j - 5, j + 5);
		while (a == j) a = acak(j - 5, j + 5);
		return a;
	}

	public static void addNilai(Context ctx, Nilai n) {
		ContentValues cv = n.genCv();
		SaveHelper sh = new SaveHelper(ctx);
		SQLiteDatabase d = sh.getWritableDatabase();
		d.insert("nilai", null, cv);
		d.close();
		sh.close();
	}

}
