package achmad.rifai.mathshogun.utils;

public class Soal {

	public enum Tingkat {
		MUDAH, SEDANG, SULIT
	}

	public enum Operasi {
		TAMBAH, KURANG, KALI, BAGI
	}

	private Operasi op;
	private int ongko1, ongko2, pilA, pilB, pilC, pilD, jawab;

	public Soal(Operasi op, int ongko1, int ongko2, int pilA, int pilB, int pilC, int pilD, int jawab) {
		super();
		this.op = op;
		this.ongko1 = ongko1;
		this.ongko2 = ongko2;
		this.pilA = pilA;
		this.pilB = pilB;
		this.pilC = pilC;
		this.pilD = pilD;
		this.jawab = jawab;
	}

	@Override
	public String toString() {
		String s = "" + ongko1 + " ";
		s += op == Operasi.TAMBAH ? '+' : op == Operasi.KURANG ? '-' : op == Operasi.KALI ? '*' : '/';
		s += ongko2 + " ";
		return s;
	}

	public int jawabe() {
		return op == Operasi.TAMBAH ? ongko1 + ongko2 : op == Operasi.KURANG ? ongko1 - ongko2 :
			op == Operasi.KALI ? ongko1 * ongko2 : ongko1 / ongko2;
	}

	public Soal() {
		super();
		ongko1 = ongko2 = pilA = pilB = pilC = pilD = jawab = 0;
	}

	public Operasi getOp() {
		return op;
	}

	public void setOp(Operasi op) {
		this.op = op;
	}

	public int getOngko1() {
		return ongko1;
	}

	public void setOngko1(int ongko1) {
		this.ongko1 = ongko1;
	}

	public int getOngko2() {
		return ongko2;
	}

	public void setOngko2(int ongko2) {
		this.ongko2 = ongko2;
	}

	public int getPilA() {
		return pilA;
	}

	public void setPilA(int pilA) {
		this.pilA = pilA;
	}

	public int getPilB() {
		return pilB;
	}

	public void setPilB(int pilB) {
		this.pilB = pilB;
	}

	public int getPilC() {
		return pilC;
	}

	public void setPilC(int pilC) {
		this.pilC = pilC;
	}

	public int getPilD() {
		return pilD;
	}

	public void setPilD(int pilD) {
		this.pilD = pilD;
	}

	public int getJawab() {
		return jawab;
	}

	public void setJawab(int jawab) {
		this.jawab = jawab;
	}

}
