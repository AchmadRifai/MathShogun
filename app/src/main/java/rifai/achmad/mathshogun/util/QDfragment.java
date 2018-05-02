package rifai.achmad.mathshogun.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import rifai.achmad.mathshogun.GameActivity;
import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Soal;

public class QDfragment extends DialogFragment {
    private GameActivity ga;
    private TextView soalText;
    private EditText jawab;
    private Soal soale;
    private Dialog d;

    private void jawabane() {
        if(soale.isBenar(Float.parseFloat(""))){
            ga.setGold(ga.getGold()+(20*ga.getLevel()));
            ga.setExp(ga.getExp()+(20*ga.getLevel()));
        }else {
            ga.setLife(ga.getLife()-1);
        }ga.setWaktu(5);
        d.dismiss();
    }

    private void binding(View v) {
        soalText=(TextView)v.findViewById(R.id.soalText);
        jawab=(EditText)v.findViewById(R.id.jawab);
    }

    @Override
    public void onAttach(Context context) {
        ga= (GameActivity) context;
    }
}
