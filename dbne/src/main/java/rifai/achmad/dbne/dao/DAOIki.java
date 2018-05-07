package rifai.achmad.dbne.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.entity.Iki;

public class DAOIki implements DAO<Iki>{
    private ConnHelper ch;

    public DAOIki(ConnHelper ch) {
        this.ch = ch;
    }

    @Override
    public void insert(Iki v) {
        ContentValues cv=new ContentValues();
        SQLiteDatabase db=ch.getWritableDatabase();
        cv.put("tenger",v.getTenger());
        db.insert("iki",null,cv);
        db.close();
    }

    @Override
    public void delete(Iki w) {
        SQLiteDatabase db=ch.getWritableDatabase();
        db.delete("iki","tenger=?",new String[]{""+w.getTenger()});
        db.close();
    }

    @Override
    public void update(Iki a, Iki b) {
        SQLiteDatabase db=ch.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("tenger",b.getTenger());
        db.update("iki",cv,"tenger=?",new String[]{""+a.getTenger()});
        db.close();
    }

    @Override
    public List<Iki> all() {
        List<Iki>l=new LinkedList<>();
        SQLiteDatabase db=ch.getReadableDatabase();
        Cursor c=db.query("iki",null,null,null,
                null,null,null);
        if(c.moveToFirst()){
            fill(c,l);
            while (c.moveToNext())fill(c,l);
        }c.close();
        db.close();
        return l;
    }

    private void fill(Cursor c, List<Iki> l) {
        Iki i=new Iki();
        i.setTenger(c.getInt(c.getColumnIndex("tenger")));
        l.add(i);
    }
}
