package rifai.achmad.mathshogun.util.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import rifai.achmad.mathshogun.util.ConnHelper;
import rifai.achmad.mathshogun.util.entity.Nilai;

/**
 * Created by ai on 06/03/2018.
 */

public class DAONilai implements DAO<Nilai>{
    private ConnHelper c;

    public DAONilai(ConnHelper c) {
        this.c = c;
    }

    @Override
    public void insert(Nilai v) {
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("kode",v.getKode());
        cv.put("nama",v.getNama());
        cv.put("tgl",""+v.getTgl());
        cv.put("mode",v.getMode());
        cv.put("gold",v.getGold());
        cv.put("level",v.getLevel());
        cv.put("kasta",v.getKasta());
        d.insert("nilai",null,cv);
        d.close();
    }

    @Override
    public void delete(Nilai w) {
        SQLiteDatabase d=c.getWritableDatabase();
        d.delete("nilai","kode=?",new String[]{w.getKode()});
        d.close();
    }

    @Override
    public void update(Nilai a, Nilai b) {
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("kode",b.getKode());
        cv.put("nama",b.getNama());
        cv.put("tgl",""+b.getTgl());
        cv.put("mode",b.getMode());
        cv.put("gold",b.getGold());
        cv.put("level",b.getLevel());
        cv.put("kasta",b.getKasta());
        d.update("nilai",cv,"kode=?",new String[]{a.getKode()});
        d.close();
    }

    @SuppressLint("NewApi")
    @Override
    public List<Nilai> all() throws JSONException {
        List<Nilai>l=new LinkedList<>();
        SQLiteDatabase d=c.getReadableDatabase();
        Cursor cur=d.rawQuery("select kode from nilai",null);
        if(cur.moveToFirst()){
            l.add(new Nilai(cur.getString(cur.getColumnIndex("kode")),c));
            while (cur.moveToNext())l.add(new Nilai(cur.getString(cur.getColumnIndex("kode")),c));
        }l.sort(genSorter());
        d.close();
        return l;
    }

    private Comparator<? super Nilai> genSorter() {
        return new Comparator<Nilai>() {
            @Override
            public int compare(Nilai o1, Nilai o2) {
                int x,val1=genVal(o1),val2=genVal(o2);
                if(val1>val2)x=1;
                else if(val1<val2)x=-1;
                else x=0;
                return x;
            }
        };
    }

    private int genVal(Nilai n) {
        int x=n.getGold()+(100*n.getLevel());
        if(n.getKasta()=="pariya")x+=6;
        else if(n.getKasta()=="sudra")x+=5;
        else if(n.getKasta()=="pedagang")x+=4;
        else if(n.getKasta()=="cindekiawan")x+=3;
        else if("sudra"==n.getKasta())x+=2;
        else if("kaisar"==n.getKasta())x++;
        return x;
    }
}
