package rifai.achmad.mathshogun.util;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rifai.achmad.mathshogun.GameActivity;
import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Soal;
import rifai.achmad.mathshogun.ongame.Pemain;

/**
 * Created by ashura on 21/04/18.
 */

public class QuisDialog extends Dialog {
    private Pemain player;
    private GameActivity ga;
    private boolean titikTerpakai=false,minTerpakai=false;
    private TextView soalText;
    private Soal soale;

    public QuisDialog(@NonNull GameActivity context) {
        super(context);
        ga=context;
        setContentView(R.layout.quis);
        binding();
    }

    @Override
    protected void onStart() {
        initData();
    }

    private void initData() {
        soale=Work.genSoal(ga.getLevel(),Soal.TipeSoal.MUDAH);
        soalText.setText(""+soale);
    }

    private void jawabane() {
        if(soale.isBenar(Float.parseFloat(""))){
            ga.setGold(ga.getGold()+(20*ga.getLevel()));
            ga.setExp(ga.getExp()+(20*ga.getLevel()));
        }else {
            ga.setLife(ga.getLife()-1);
        }ga.setWaktu(5);
        this.dismiss();
    }

    private void binding() {
        soalText=(TextView)findViewById(R.id.soalText);
    }
}
