package rifai.achmad.dbne.entity;

import rifai.achmad.dbne.Soal;

public class Pengaturan {
    private Soal.TipeSoal mode;
    private int suara,kecerahan;

    public Soal.TipeSoal getMode() {
        return mode;
    }

    public void setMode(Soal.TipeSoal mode) {
        this.mode = mode;
    }

    public int getSuara() {
        return suara;
    }

    public void setSuara(int suara) {
        this.suara = suara;
    }

    public int getKecerahan() {
        return kecerahan;
    }

    public void setKecerahan(int kecerahan) {
        this.kecerahan = kecerahan;
    }
}
