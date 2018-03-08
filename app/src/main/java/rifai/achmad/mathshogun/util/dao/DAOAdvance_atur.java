package rifai.achmad.mathshogun.util.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

import rifai.achmad.mathshogun.util.ConnHelper;
import rifai.achmad.mathshogun.util.entity.Advance_atur;

/**
 * Created by ai on 07/03/2018.
 */

public class DAOAdvance_atur implements DAO<Advance_atur>{
    private ConnHelper c;

    public DAOAdvance_atur(ConnHelper c) {
        this.c = c;
    }

    @Override
    public void insert(Advance_atur v) {
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",v.getMode());
        cv.put("suara",v.isSuara());
        cv.put("kecerahan",v.getKecerahan());
        cv.put("gambar",v.getGambar());
        d.insert("advance_atur",null,cv);
        d.close();
    }

    @Override
    public void delete(Advance_atur w) {
        String[]sa=new String[]{w.getMode(),""+w.isSuara(),""+w.getKecerahan(),w.getGambar()};
        SQLiteDatabase d=c.getWritableDatabase();
        d.delete("advance_atur","mode=? and suara=? and kecerahan=? and gambar=?",sa);
        d.close();
    }

    @Override
    public void update(Advance_atur a, Advance_atur b) {
        String[]sa=new String[]{a.getMode(),""+a.isSuara(),""+a.getKecerahan(),a.getGambar()};
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",b.getMode());
        cv.put("suara",b.isSuara());
        cv.put("kecerahan",b.getKecerahan());
        cv.put("gambar",b.getGambar());
        d.update("advance_atur",cv,"mode=? and suara=? and kecerahan=? and gambar=?",sa);
        d.close();
    }

    @Override
    public List<Advance_atur> all() throws JSONException {
        List<Advance_atur>l=new LinkedList<>();
        SQLiteDatabase d=c.getWritableDatabase();
        Cursor cur=d.query("advance_atur",null,null,null,null,null,null);
        if(cur.moveToFirst()){
            l.add(genCur(cur));
            while (cur.moveToNext())l.add(genCur(cur));
        }cur.close();
        d.close();
        return l;
    }

    private Advance_atur genCur(Cursor cur) {
        Advance_atur a=new Advance_atur();
        a.setGambar(cur.getString(cur.getColumnIndex("gambar")));
        a.setKecerahan(cur.getInt(cur.getColumnIndex("kecerahan")));
        a.setMode(cur.getString(cur.getColumnIndex("mode")));
        a.setSuara(1==cur.getInt(cur.getColumnIndex("suara")));
        return a;
    }
}
