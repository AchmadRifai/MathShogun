package rifai.achmad.mathshogun.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ai on 22/10/17.
 */

public class ConnHelper extends SQLiteOpenHelper{
    public ConnHelper(Context context) {
        super(context, "aiPlaying", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tanda(readed integer not null);");
        db.execSQL("create table if not exists pemain(id integer primary key," +
                "nama text not null, tingkat int not null, nilai int not null," +
                "uang int not null, tgl text not null);");
        db.execSQL("create table if not exists atur(muni int not null," +
                "tambah int not null,batas int not null,sulit int not null," +
                "minim int not null,kali int not null);");
        db.execSQL("insert into tanda values(0);");
        db.execSQL("insert into atur values(1,5,5,1,3,4);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("drop table if exists tanda");
            db.execSQL("drop table if exists pemain");
            db.execSQL("drop table if exists atur");
            onCreate(db);
        }
    }
}
