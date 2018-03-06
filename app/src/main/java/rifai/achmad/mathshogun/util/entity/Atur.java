package rifai.achmad.mathshogun.util.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import rifai.achmad.mathshogun.util.ConnHelper;

/**
 * Created by ai on 06/03/2018.
 */

public class Atur {
    private String mode;
    private int rendah,tinggi;
    private JSONArray operasi;
    private int hadiah;

    public Atur(String mode, ConnHelper c) throws JSONException {
        this.mode = mode;
        SQLiteDatabase d=c.getReadableDatabase();
        Cursor cur=d.rawQuery("select*from atur where mode='"+mode+"'",null);
        if(cur.moveToFirst()){
            rendah=cur.getInt(cur.getColumnIndex("rendah"));
            tinggi=cur.getInt(cur.getColumnIndex("tinggi"));
            operasi=new JSONArray(cur.getString(cur.getColumnIndex("operasi")));
            hadiah=cur.getInt(cur.getColumnIndex("hadiah"));
        }cur.close();
        d.close();
    }

    public Atur(String mode, int rendah, int tinggi, JSONArray operasi, int hadiah) {
        this.mode = mode;
        this.rendah = rendah;
        this.tinggi = tinggi;
        this.operasi = operasi;
        this.hadiah = hadiah;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getRendah() {
        return rendah;
    }

    public void setRendah(int rendah) {
        this.rendah = rendah;
    }

    public int getTinggi() {
        return tinggi;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    public JSONArray getOperasi() {
        return operasi;
    }

    public void setOperasi(JSONArray operasi) {
        this.operasi = operasi;
    }

    public int getHadiah() {
        return hadiah;
    }

    public void setHadiah(int hadiah) {
        this.hadiah = hadiah;
    }
}
