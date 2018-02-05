package rifai.achmad.mathshogun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Setting;

/**
 * Created by ai on 03/02/2018.
 */

public class AturHolder extends RecyclerView.ViewHolder {
    private View v;
    private Setting s;
    private TextView nama,val;
    private ImageButton up,down;

    public AturHolder(View itemView) {
        super(itemView);
        v=itemView;
        nama=(TextView)v.findViewById(R.id.nmAtur);
        val=(TextView)v.findViewById(R.id.valAtur);
        up=(ImageButton)v.findViewById(R.id.upAtur);
        down=(ImageButton)v.findViewById(R.id.downAtur);
    }

    public void setS(Setting s) {
        this.s = s;
        nama.setText(s.getNama());
        val.setText(""+s.getNilai());
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upEvent();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downEvent();
            }
        });
        refresh();
    }

    private void downEvent() {
        int x=Integer.parseInt(""+val.getText());
        x++;
        val.setText(""+x);
        refresh();
        s.setNilai(x);
    }

    private void upEvent() {
        int x=Integer.parseInt(""+val.getText());
        x--;
        val.setText(""+x);
        refresh();
        s.setNilai(x);
    }

    private void refresh() {
        int x=Integer.parseInt(""+val.getText());
        up.setEnabled(x<s.getBatasAtas());
        down.setEnabled(x>s.getBatasBawah());
    }
}
