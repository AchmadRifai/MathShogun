package rifai.achmad.mathshogun.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;

import rifai.achmad.mathshogun.util.entity.Atur;

/**
 * Created by ai on 22/10/17.
 */

public class ConnHelper extends SQLiteOpenHelper{
    public ConnHelper(Context context) {
        super(context, "MathShogun", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table atur(mode text primary key,rendah int,tinggi int,operasi text,hadiah int)");
        db.execSQL("create table nilai(kode text primary key," +
                "nama text not null,tgl text not null,mode text not null,gold int,level int,kasta text," +
                "foreign key(mode)references atur(mode))");
        db.execSQL("create table advance_atur(mode text,suara boolean,kecerahan int,gambar text," +
                "foreign key(mode)references atur(mode))");
        db.execSQL("create table tenger(aku int)");
        insertData(db,"mudah");
        insertData(db,"sedang");
        insertData(db,"sulit");
        sisipTanda(db);
    }

    private void sisipTanda(SQLiteDatabase db) {
        ContentValues cv=new ContentValues();
        cv.put("aku",0);
        db.insert("tenger",null,cv);
    }

    private void insertData(SQLiteDatabase db, String s) {
        JSONArray ar=new JSONArray();
        if(s=="mudah"){
            ar.put("tambah");
            ar.put("kurang");
            Atur a=new Atur(s,1,5,ar,10);
            save(db,a);
        }else if(s=="sedang"){
            ar.put("tambah");
            ar.put("kali");
            ar.put("kurang");
            ar.put("bagi");
            Atur a=new Atur(s,1,5,ar,20);
            save(db,a);
        }else if(s=="sulit"){
            ar.put("tambah");
            ar.put("kali");
            ar.put("kurang");
            ar.put("bagi");
            Atur a=new Atur(s,1,10,ar,40);
            save(db,a);
        }defautlOption(db,s);
    }

    private void defautlOption(SQLiteDatabase db, String s) {
        ContentValues cv=new ContentValues();
        cv.put("mode",s);
        cv.put("suara",true);
        cv.put("kecerahan",50);
        cv.put("gambar","rendah");
        db.insert("advance_atur",null,cv);
    }

    private void save(SQLiteDatabase db, Atur a) {
        ContentValues cv=new ContentValues();
        cv.put("mode",a.getMode());
        cv.put("operasi",""+a.getOperasi());
        cv.put("rendah",a.getRendah());
        cv.put("tinggi",a.getTinggi());
        cv.put("hadiah",a.getHadiah());
        db.insert("atur",null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("drop table if exists advance_atur");
            db.execSQL("drop table if exists nilai");
            db.execSQL("drop table if exists atur");
            onCreate(db);
        }
    }
}
