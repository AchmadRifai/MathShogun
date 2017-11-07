package rifai.achmad.mathshogun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Catatan;

/**
 * Created by ai on 31/10/17.
 */

public class NilaiHolder extends RecyclerView.ViewHolder{
    private TextView pemain,nilai,waktu;

    public NilaiHolder(View v) {
        super(v);
        pemain=(TextView)v.findViewById(R.id.nmPemain);
        nilai=(TextView)v.findViewById(R.id.pointPlay);
        waktu=(TextView)v.findViewById(R.id.waktu);
    }

    public void setData(Catatan c){
        pemain.setText(c.getNama());
        nilai.setText(""+c.getPoint());
        waktu.setText(""+c.getTgl());
    }
}