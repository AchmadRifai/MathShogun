package rifai.achmad.mathshogun.util.entity;

/**
 * Created by ai on 06/03/2018.
 */

public class Advance_atur {
    private String mode;
    private boolean suara;
    private int kecerahan;
    private String gambar;

    public Advance_atur() {
    }

    public Advance_atur(String mode, boolean suara, int kecerahan, String gambar) {
        this.mode = mode;
        this.suara = suara;
        this.kecerahan = kecerahan;
        this.gambar = gambar;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isSuara() {
        return suara;
    }

    public void setSuara(boolean suara) {
        this.suara = suara;
    }

    public int getKecerahan() {
        return kecerahan;
    }

    public void setKecerahan(int kecerahan) {
        this.kecerahan = kecerahan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
