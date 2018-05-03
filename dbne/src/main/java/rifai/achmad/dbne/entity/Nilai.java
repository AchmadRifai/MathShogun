package rifai.achmad.dbne.entity;

import org.joda.time.DateTime;

import rifai.achmad.dbne.Soal;

public class Nilai {
    private String pemain;
    private int gold,level,exp,nyawa;
    private DateTime tgl;
    private Soal.TipeSoal mode;

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
    }

    public String getPemain() {
        return pemain;
    }

    public void setPemain(String pemain) {
        this.pemain = pemain;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public DateTime getTgl() {
        return tgl;
    }

    public void setTgl(DateTime tgl) {
        this.tgl = tgl;
    }

    public Soal.TipeSoal getMode() {
        return mode;
    }

    public void setMode(Soal.TipeSoal mode) {
        this.mode = mode;
    }
}
