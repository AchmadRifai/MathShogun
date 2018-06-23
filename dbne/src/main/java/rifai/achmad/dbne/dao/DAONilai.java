package rifai.achmad.dbne.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.entity.Nilai;

public class DAONilai implements DAO<Nilai>{
    private ConnHelper ch;

    public DAONilai(ConnHelper ch) {
        this.ch = ch;
    }

    @Override
    public void insert(Nilai v) {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=ch.getWritableDatabase();
        cv.put("pemain",v.getPemain());
        cv.put("mode",""+v.getMode());
        cv.put("tgl",""+v.getTgl());
        cv.put("exp",v.getExp());
        cv.put("gold",v.getGold());
        cv.put("nyawa",v.getNyawa());
        cv.put("level",v.getLevel());
        db.insert("nilai",null,cv);
        db.close();
    }

    @Override
    public void delete(Nilai w) {
        SQLiteDatabase db=ch.getWritableDatabase();
        db.delete("nilai","pemain=? and tgl=? and mode=?",
                new String[]{w.getPemain(),""+w.getTgl(),""+w.getMode()});
        db.close();
    }

    @Override
    public void update(Nilai a, Nilai b) {
        SQLiteDatabase db=ch.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("pemain",b.getPemain());
        cv.put("mode",""+b.getMode());
        cv.put("tgl",""+b.getTgl());
        cv.put("exp",b.getExp());
        cv.put("gold",b.getGold());
        cv.put("nyawa",b.getNyawa());
        cv.put("level",b.getLevel());
        db.update("nilai",cv,"pemain=? and tgl=? and mode=?",
                new String[]{a.getPemain(),""+a.getTgl(),""+a.getMode()});
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Nilai> all() {
        List<Nilai>l=new LinkedList<>();
        SQLiteDatabase db=ch.getReadableDatabase();
        Cursor c=db.query("nilai",null,null,null
                ,null,null,null);
        if(c.moveToFirst()){
            fill(l,c);
            while (c.moveToNext())fill(l,c);
        }c.close();
        db.close();
        if(!l.isEmpty())
            Collections.sort(l,komparasi());
        return l;
    }

    private Comparator<? super Nilai> komparasi() {
        return new Comparator<Nilai>() {
            @Override
            public int compare(Nilai o1, Nilai o2) {
                int i;
                if(o1.genNilai()>o2.genNilai())i=-1;
                else if(o2.genNilai()>o1.genNilai())i=1;
                else i=0;
                return i;
            }
        };
    }

    private void fill(List<Nilai> l, Cursor c) {
        Nilai n=new Nilai();
        n.setExp(c.getInt(c.getColumnIndex("exp")));
        n.setGold(c.getInt(c.getColumnIndex("gold")));
        n.setLevel(c.getInt(c.getColumnIndex("level")));
        n.setMode(Soal.TipeSoal.valueOf(c.getString(c.getColumnIndex("mode"))));
        n.setPemain(c.getString(c.getColumnIndex("pemain")));
        n.setNyawa(c.getInt(c.getColumnIndex("nyawa")));
        n.setTgl(DateTime.parse(c.getString(c.getColumnIndex("tgl"))));
        l.add(n);
    }

    private int ai(Nilai o1, Nilai o2) {
        int i=0;
        if(better(o1,o2)==1)i++;
        if(o1.getNyawa()>o2.getNyawa())i++;
        if(o1.getLevel()>o2.getLevel())i++;
        if(o1.getExp()>o2.getExp())i++;
        if(o1.getGold()>o2.getGold())i++;
        if(o1.getTgl().isBefore(o2.getTgl()))i++;
        return i;
    }

    private int better(Nilai o1, Nilai o2) {
        int i,a=genAngka(o1.getMode()),b=genAngka(o2.getMode());
        if(a>b)i=1;
        else if(a<b)i=-1;
        else i=0;
        return i;
    }

    private int genAngka(Soal.TipeSoal mode) {
        int i=0;
        if(mode== Soal.TipeSoal.SULIT)i=3;
        else if(mode== Soal.TipeSoal.SEDANG)i=2;
        else if(mode== Soal.TipeSoal.MUDAH)i=1;
        return i;
    }
}
