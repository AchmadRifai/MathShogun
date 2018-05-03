package rifai.achmad.dbne.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.entity.Pengaturan;

public class DAOPengaturan implements DAO<Pengaturan>{
    private ConnHelper ch;

    public DAOPengaturan(ConnHelper ch) {
        this.ch = ch;
    }

    @Override
    public void insert(Pengaturan v) {
        SQLiteDatabase db=ch.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",""+v.getMode());
        cv.put("suara",v.getSuara());
        cv.put("kecerahan",v.getKecerahan());
        db.insert("pengaturan",null,cv);
        db.close();
    }

    @Override
    public void delete(Pengaturan w) {
        SQLiteDatabase db=ch.getWritableDatabase();
        db.delete("pengaturan","mode=? and suara=? and kecerahan=?",
                new String[]{""+w.getMode(),""+w.getSuara(),""+w.getKecerahan()});
        db.close();
    }

    @Override
    public void update(Pengaturan a, Pengaturan b) {
        SQLiteDatabase db=ch.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",""+b.getMode());
        cv.put("suara",b.getSuara());
        cv.put("kecerahan",b.getKecerahan());
        db.update("pengaturan",cv,"mode=? and suara=? and kecerahan=?",
                new String[]{""+a.getMode(),""+a.getSuara(),""+a.getKecerahan()});
        db.close();
    }

    @Override
    public List<Pengaturan> all() {
        List<Pengaturan>l=new LinkedList<>();
        SQLiteDatabase db=ch.getWritableDatabase();
        Cursor c=db.query("pengaturan",null,null,null,
                null,null,null);
        if(c.moveToFirst()){
            fill(c,l);
            while (c.moveToNext())fill(c,l);
        }c.close();
        db.close();
        return l;
    }

    private void fill(Cursor c, List<Pengaturan> l) {
        Pengaturan p=new Pengaturan();
        p.setKecerahan(c.getInt(c.getColumnIndex("kecerahan")));
        p.setSuara(c.getInt(c.getColumnIndex("suara")));
        p.setMode(Soal.TipeSoal.valueOf(c.getString(c.getColumnIndex("mode"))));
        l.add(p);
    }
}
