package rifai.achmad.mathshogun.util.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

import rifai.achmad.mathshogun.util.ConnHelper;
import rifai.achmad.mathshogun.util.entity.Atur;

/**
 * Created by ai on 06/03/2018.
 */

public class DAOAtur implements DAO<Atur>{
    private ConnHelper c;

    public DAOAtur(ConnHelper c) {
        this.c = c;
    }

    @Override
    public void insert(Atur v) {
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",v.getMode());
        cv.put("operasi",""+v.getOperasi());
        cv.put("rendah",v.getRendah());
        cv.put("tinggi",v.getTinggi());
        cv.put("hadiah",v.getHadiah());
        d.insert("atur",null,cv);
        d.close();
    }

    @Override
    public void delete(Atur w) {
        SQLiteDatabase d=c.getWritableDatabase();
        d.delete("atur","mode=?",new String[]{w.getMode()});
        d.close();
    }

    @Override
    public void update(Atur a, Atur b) {
        SQLiteDatabase d=c.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("mode",b.getMode());
        cv.put("operasi",""+b.getOperasi());
        cv.put("rendah",b.getRendah());
        cv.put("tinggi",b.getTinggi());
        cv.put("hadiah",b.getHadiah());
        d.update("atur",cv,"mode=?",new String[]{a.getMode()});
        d.close();
    }

    @Override
    public List<Atur> all() throws JSONException {
        List<Atur>l=new LinkedList<>();
        SQLiteDatabase d=c.getReadableDatabase();
        d.close();
        return l;
    }
}
