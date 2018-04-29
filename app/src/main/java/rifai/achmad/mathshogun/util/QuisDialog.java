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
    private TextView soalText,jawab;
    private Button submit,no1,no2,no3,no4,no5,no6,no7,no8,no9,no0,del,min,titik;
    private Soal soale;

    public QuisDialog(@NonNull GameActivity context,Pemain player) {
        super(context);
        ga=context;
        this.player=player;
        setContentView(R.layout.quis);
        binding();
        initData();
        evente();
    }

    private void initData() {
        //soale=Work.genSoal(ga.getLevel(),Soal.TipeSoal.MUDAH);
        soalText.setText(""+soale);
    }

    private void evente() {
        titik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titikTerpakai=true;
                titik.setEnabled(!titikTerpakai);
                String j=""+jawab.getText()+".";
                jawab.setText(j);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jawabane();
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minTerpakai=true;
                min.setEnabled(!minTerpakai);
                jawab.setText("-"+jawab.getText());
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s.length()>1)jawab.setText(s.substring(0,s.length()-2));
                else jawab.setText("0");
            }
        });
        no0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s!="0")s+="0";
                jawab.setText(s);
            }
        });
        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="1";
                else s+="1";
                jawab.setText(s);
            }
        });
        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="2";
                else s+="2";
                jawab.setText(s);
            }
        });
        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="3";
                else s+="3";
                jawab.setText(s);
            }
        });
        no4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="4";
                else s+="4";
                jawab.setText(s);
            }
        });
        no5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="5";
                else s+="5";
                jawab.setText(s);
            }
        });
        no6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="6";
                else s+="6";
                jawab.setText(s);
            }
        });
        no7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="7";
                else s+="7";
                jawab.setText(s);
            }
        });
        no8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="8";
                else s+="8";
                jawab.setText(s);
            }
        });
        no9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="9";
                else s+="9";
                jawab.setText(s);
            }
        });
    }

    private void jawabane() {
        /*if(soale.isBenar(Float.parseFloat(""+jawab.getText()))){
            ga.setGold(ga.getGold()+(20*ga.getLevel()));
            ga.setExp(ga.getExp()+(20*ga.getLevel()));
        }else {
            ga.setNyawa(ga.getNyawa()-200);
        }this.dismiss();*/
    }

    private void binding() {
        soalText=(TextView)findViewById(R.id.soalText);
        jawab=(TextView)findViewById(R.id.jawabText);
        submit=(Button)findViewById(R.id.submitJawab);
        no0=(Button)findViewById(R.id.no0);
        no1=(Button)findViewById(R.id.no1);
        no2=(Button)findViewById(R.id.no2);
        no3=(Button)findViewById(R.id.no3);
        no4=(Button)findViewById(R.id.no4);
        no5=(Button)findViewById(R.id.no5);
        no6=(Button)findViewById(R.id.no6);
        no7=(Button)findViewById(R.id.no7);
        no8=(Button)findViewById(R.id.no8);
        no9=(Button)findViewById(R.id.no9);
        del=(Button)findViewById(R.id.del);
        min=(Button)findViewById(R.id.min);
        titik=(Button)findViewById(R.id.titik);
    }
}
