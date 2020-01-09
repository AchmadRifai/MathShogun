package achmad.rifai.mathshogun.beans;

import achmad.rifai.mathshogun.utils.Soal.Tingkat;
import android.content.ContentValues;
import android.database.Cursor;

public class Atur {

	private int vol;
	private boolean cerita;
	private Tingkat dif;

	public Atur(int vol, boolean cerita, Tingkat dif) {
		super();
		this.vol = vol;
		this.cerita = cerita;
		this.dif = dif;
	}

	public Atur() {
		super();
	}

	public Atur(Cursor c) {
		super();
		cerita = 1 == c.getInt(c.getColumnIndex("cerita"));
		vol = c.getInt(c.getColumnIndex("vol"));
		int d = c.getInt(c.getColumnIndex("dif"));
		dif = d == 3 ? Tingkat.SULIT : d == 2 ? Tingkat.SEDANG : Tingkat.MUDAH;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public boolean isCerita() {
		return cerita;
	}

	public void setCerita(boolean cerita) {
		this.cerita = cerita;
	}

	public Tingkat getDif() {
		return dif;
	}

	public void setDif(Tingkat dif) {
		this.dif = dif;
	}

	public ContentValues buatCV() {
		ContentValues cv = new ContentValues();
		cv.put("cerita", cerita);
		cv.put("vol", vol);
		cv.put("dif", dif == Tingkat.SULIT ? 3 : dif == Tingkat.SEDANG ? 2 : 1);
		return cv;
	}

}
