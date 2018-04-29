package rifai.achmad.mathshogun.beans;

/**
 * Created by ashura on 22/04/18.
 */

public class Soal {
    public enum SoalOperasi{
        TAMBAH,KURANG,KALI,BAGI
    }public enum TipeSoal{
        MUDAH,SEDANG,SULIT
    }

    private float angka1,angka2;
    private SoalOperasi operasi;

    public float getAngka1() {
        return angka1;
    }

    public void setAngka1(float angka1) {
        this.angka1 = angka1;
    }

    public float getAngka2() {
        return angka2;
    }

    public void setAngka2(float angka2) {
        this.angka2 = angka2;
    }

    public SoalOperasi getOperasi() {
        return operasi;
    }

    public void setOperasi(SoalOperasi operasi) {
        this.operasi = operasi;
    }

    public boolean isBenar(float jawab){
        boolean b=false;
        if(operasi==SoalOperasi.BAGI)b=jawab==angka1/angka2;
        else if(operasi==SoalOperasi.KALI)b=jawab==angka1*angka2;
        else if(operasi==SoalOperasi.KURANG)b=jawab==angka1-angka2;
        else if(operasi==SoalOperasi.TAMBAH)b=jawab==angka1+angka2;
        return b;
    }

    @Override
    public String toString() {
        String s=""+angka1+" ";
        if(operasi==SoalOperasi.BAGI)s+="/ ";
        else if(operasi==SoalOperasi.KALI)s+="* ";
        else if(operasi==SoalOperasi.KURANG)s+="- ";
        else if(operasi==SoalOperasi.TAMBAH)s+="+ ";
        s+=""+angka2;
        return s;
    }
}
