package rifai.achmad.mathshogun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rifai.achmad.dbne.ConnHelper;
import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.dao.DAOPengaturan;
import rifai.achmad.mathshogun.adapter.AturAdapter;
import rifai.achmad.mathshogun.beans.Pengaturan;
import rifai.achmad.mathshogun.beans.Setting;
import rifai.achmad.mathshogun.util.Work;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Atur extends AppCompatActivity {
    private rifai.achmad.dbne.entity.Pengaturan data;
    private RadioGroup mode;
    private TextView suara;
    private Button yo,gag;
    private ImageButton up,down;
    private Soal.TipeSoal tipeSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur);
        binding();
        evene();
        muat();
    }

    private void muat() {
        ConnHelper c=new ConnHelper(this);
        DAOPengaturan dao=new DAOPengaturan(c);
        List<rifai.achmad.dbne.entity.Pengaturan>l=dao.all();
        data=l.get(0);
        c.close();
        tipeSoal=data.getMode();
        suara.setText(""+data.getSuara());
        validasiSuara();
        aturMode();
    }

    private void aturMode() {
        if(tipeSoal== Soal.TipeSoal.MUDAH)mode.check(R.id.mudah);
        else if(tipeSoal== Soal.TipeSoal.SEDANG)mode.check(R.id.sedang);
        else if(tipeSoal== Soal.TipeSoal.SULIT)mode.check(R.id.sulit);
        RadioButton mudah=(RadioButton)findViewById(R.id.mudah),sedang=(RadioButton)findViewById(R.id.sedang)
                ,sulit=(RadioButton)findViewById(R.id.sulit);
        mudah.setText("MUDAH");
        sedang.setText("SEDANG");
        sulit.setText("SULIT");
    }

    private void evene() {
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(""+suara.getText());
                i++;
                suara.setText(""+i);
                validasiSuara();
            }
        });
        gag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(""+suara.getText());
                i--;
                suara.setText(""+i);
                validasiSuara();
            }
        });
        yo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.mudah)tipeSoal= Soal.TipeSoal.MUDAH;
                else if(checkedId==R.id.sedang)tipeSoal= Soal.TipeSoal.SEDANG;
                else if(checkedId==R.id.sulit)tipeSoal= Soal.TipeSoal.SULIT;
            }
        });
    }

    private void saveData() {
        rifai.achmad.dbne.entity.Pengaturan p2=new rifai.achmad.dbne.entity.Pengaturan();
        p2.setKecerahan(data.getKecerahan());
        p2.setSuara(Integer.parseInt(""+suara.getText()));
        p2.setMode(tipeSoal);
        ConnHelper c=new ConnHelper(this);
        DAOPengaturan dao=new DAOPengaturan(c);
        dao.update(data,p2);
        c.close();
        onBackPressed();
    }

    private void validasiSuara() {
        int i=Integer.parseInt(""+suara.getText());
        up.setEnabled(i!=10);
        down.setEnabled(i!=0);
    }

    private void binding() {
        mode=(RadioGroup)findViewById(R.id.kesulitan);
        suara=(TextView)findViewById(R.id.volumene);
        yo=(Button)findViewById(R.id.savBtnSet);
        gag=(Button)findViewById(R.id.cclBtnSet);
        up=(ImageButton)findViewById(R.id.volUp);
        down=(ImageButton)findViewById(R.id.volDown);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}