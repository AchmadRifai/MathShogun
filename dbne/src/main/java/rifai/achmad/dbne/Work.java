package rifai.achmad.dbne;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Random;

import rifai.achmad.dbne.dao.DAOIki;
import rifai.achmad.dbne.dao.DAONilai;
import rifai.achmad.dbne.dao.DAOPengaturan;
import rifai.achmad.dbne.entity.Iki;
import rifai.achmad.dbne.entity.Nilai;
import rifai.achmad.dbne.entity.Pengaturan;

public class Work {
    public static Soal genSoal(int level, Soal.TipeSoal tipeSoal) {
        Soal s=new Soal();
        generateSoal(s,tipeSoal,level);
        while (0>s.jawaban())generateSoal(s,tipeSoal,level);
        return s;
    }

    private static void generateSoal(Soal s, Soal.TipeSoal tipeSoal, int level) {
        int op,awal=1,akhir,add;
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
    }

    public static int genRandom(int awal, int akhir) {
        Random r=new Random();
        return r.nextInt((akhir-awal)+1)+awal;
    }

    public static boolean cerita(Activity a) {
        boolean b=false;
        ConnHelper ch=new ConnHelper(a);
        DAOIki dao=new DAOIki(ch);
        List<Iki>l=dao.all();
        if(!l.isEmpty()){
            Iki i=l.get(0);
            b=i.getTenger()==1;
        }ch.close();
        return b;
    }

    public static void terbaca(Activity a) {
        ConnHelper ch=new ConnHelper(a);
        DAOIki dao=new DAOIki(ch);
        Iki i=new Iki();
        i.setTenger(1);
        List<Iki>l=dao.all();
        if(!l.isEmpty()){
            Iki i2=l.get(0);
            dao.update(i2,i);
        }ch.close();
    }

    public static Soal.TipeSoal getMyMode(Context context) {
        ConnHelper ch=new ConnHelper(context);
        DAOPengaturan dao=new DAOPengaturan(ch);
        List<Pengaturan>l=dao.all();
        Soal.TipeSoal t=null;
        if(!l.isEmpty()){
            Pengaturan p=l.get(0);
            t=p.getMode();
        }ch.close();
        return t;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Nilai> getAllNilai(Activity activity) {
        ConnHelper c=new ConnHelper(activity);
        DAONilai dao=new DAONilai(c);
        List<Nilai>l=dao.all();
        c.close();
        return l;
    }
}
