package rifai.achmad.mathshogun.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        insertData(db,"mudah");
    }

    private void insertData(SQLiteDatabase db, String s) {
        if(s=="mudah"){
            //
        }
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
