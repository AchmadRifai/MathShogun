package rifai.achmad.dbne;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnHelper extends SQLiteOpenHelper {
    public ConnHelper(Context context) {
        super(context, "MathShogun", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table nilai(pemain text not null,gold int not null,level int not null," +
                "exp int not null,tgl text not null,nyawa int not null,mode text not null)");
        db.execSQL("create table pengaturan(mode text not null,suara int not null,kecerahan int not null)");
        db.execSQL("create table iki(tenger int)");
        initAtur(db);
        sisipTanda(db);
    }

    private void sisipTanda(SQLiteDatabase db) {
        ContentValues cv=new ContentValues();
        cv.put("tenger",0);
        db.insert("iki",null,cv);
    }

    private void initAtur(SQLiteDatabase db) {
        ContentValues cv=new ContentValues();
        cv.put("mode","MUDAH");
        cv.put("suara",100);
        cv.put("kecerahan",100);
        db.insert("pengaturan",null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            db.execSQL("drop table if exists advance_atur");
            db.execSQL("drop table if exists nilai");
            db.execSQL("drop table if exists atur");
            onCreate(db);
        }
    }
}
