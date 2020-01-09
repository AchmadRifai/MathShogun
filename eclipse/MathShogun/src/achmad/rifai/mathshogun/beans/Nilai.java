package achmad.rifai.mathshogun.beans;

import java.util.Date;

import achmad.rifai.mathshogun.utils.Soal.Tingkat;
import android.content.ContentValues;
import android.database.Cursor;

public class Nilai {

	private String np;
	private Tingkat dif;
	private Date tgl;
	private int gold, lvl, hp, exp;

	public Nilai(String np, Tingkat dif, Date tgl, int gold, int lvl, int hp, int exp) {
		super();
		this.np = np;
		this.dif = dif;
		this.tgl = tgl;
		this.gold = gold;
		this.lvl = lvl;
		this.hp = hp;
		this.exp = exp;
	}

	public Nilai() {
		super();
	}

	public Nilai(Cursor c) {
		super();
		np = c.getString(c.getColumnIndex("np"));
		int d = c.getInt(c.getColumnIndex("dif"));
		dif = d == 1 ? Tingkat.MUDAH : d == 2 ? Tingkat.SEDANG : Tingkat.SULIT;
		tgl = new Date(c.getLong(c.getColumnIndex("tgl")));
		gold = c.getInt(c.getColumnIndex("gold"));
		lvl = c.getInt(c.getColumnIndex("lvl"));
		hp = c.getInt(c.getColumnIndex("hp"));
		exp = c.getInt(c.getColumnIndex("exp"));
	}

	public String getNp() {
		return np;
	}

	public void setNp(String np) {
		this.np = np;
	}

	public Tingkat getDif() {
		return dif;
	}

	public void setDif(Tingkat dif) {
		this.dif = dif;
	}

	public Date getTgl() {
		return tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public ContentValues genCv() {
		ContentValues cv = new ContentValues();
		cv.put("np", np);
		cv.put("dif", dif == Tingkat.MUDAH ? 1 : dif == Tingkat.SEDANG ? 2 : 3);
		cv.put("tgl", tgl.getTime());
		cv.put("gold", gold);
		cv.put("lvl", lvl);
		cv.put("hp", hp);
		cv.put("exp", exp);
		return cv;
	}

}
