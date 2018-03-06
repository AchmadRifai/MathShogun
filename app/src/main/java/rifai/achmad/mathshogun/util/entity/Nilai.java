package rifai.achmad.mathshogun.util.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.joda.time.DateTime;

import rifai.achmad.mathshogun.util.ConnHelper;

/**
 * Created by ai on 06/03/2018.
 */

public class Nilai {
    private String kode,nama;
    private DateTime tgl;
    private String mode;
    private int gold,level;
    private String kasta;

    public Nilai(String kode, ConnHelper c) {
        this.kode = kode;
        SQLiteDatabase d=c.getReadableDatabase();
        Cursor cur=d.rawQuery("select*from nilai where kode='"+kode+"'",null);
        if(cur.moveToFirst()){
            nama=cur.getString(cur.getColumnIndex("nama"));
            tgl=DateTime.parse(cur.getString(cur.getColumnIndex("tgl")));
            mode=cur.getString(cur.getColumnIndex("mode"));
            gold=cur.getInt(cur.getColumnIndex("gold"));
            level=cur.getInt(cur.getColumnIndex("level"));
            kasta=cur.getString(cur.getColumnIndex("kasta"));
        }cur.close();
        d.close();
    }

    public Nilai(String kode, String nama, String mode, int gold, int level, String kasta) {
        this.kode = kode;
        tgl=DateTime.now();
        this.nama = nama;
        this.mode = mode;
        this.gold = gold;
        this.level = level;
        this.kasta = kasta;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public DateTime getTgl() {
        return tgl;
    }

    public void setTgl(DateTime tgl) {
        this.tgl = tgl;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getKasta() {
        return kasta;
    }

    public void setKasta(String kasta) {
        this.kasta = kasta;
    }
}
