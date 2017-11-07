package rifai.achmad.mathshogun.beans;

import org.joda.time.DateTime;

/**
 * Created by ai on 22/09/2017.
 */

public class Catatan {
    private String nama;
    private int level,point,gold;
    private DateTime tgl;

    public DateTime getTgl() {
        return tgl;
    }

    public void setTgl(DateTime tgl) {
        this.tgl = tgl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}