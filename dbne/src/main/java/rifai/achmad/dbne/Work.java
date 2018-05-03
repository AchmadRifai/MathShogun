package rifai.achmad.dbne;

import java.util.Random;

public class Work {
    public static Soal genSoal(int level, Soal.TipeSoal tipeSoal) {
        int op,awal=1,akhir,add;
        Soal s=new Soal();
        if(tipeSoal== Soal.TipeSoal.MUDAH)op=genRandom(1,2);
        else op=genRandom(1,4);
        if(op==1)s.setOperasi(Soal.SoalOperasi.TAMBAH);
        else if(op==2)s.setOperasi(Soal.SoalOperasi.KURANG);
        else if(op==3)s.setOperasi(Soal.SoalOperasi.KALI);
        else if(op==4)s.setOperasi(Soal.SoalOperasi.BAGI);
        if(tipeSoal== Soal.TipeSoal.SULIT)add=10;
        else add=5;
        akhir=add;
        for(int x=1;x<level;x++){
            awal+=add;
            akhir+=add;
        }s.setAngka2(genRandom(awal,akhir));
        s.setAngka1(genRandom(awal,akhir));
        return s;
    }

    private static int genRandom(int awal, int akhir) {
        Random r=new Random();
        return r.nextInt((akhir-awal)+1)+awal;
    }
}
