package rifai.achmad.mathshogun.beans;

/**
 * Created by ai on 03/02/2018.
 */

public class Setting {
    private String nama;
    private int nilai,batasAtas,batasBawah;

    public int getBatasAtas() {
        return batasAtas;
    }

    public void setBatasAtas(int batasAtas) {
        this.batasAtas = batasAtas;
    }

    public int getBatasBawah() {
        return batasBawah;
    }

    public void setBatasBawah(int batasBawah) {
        this.batasBawah = batasBawah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}