package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rifai.achmad.mathshogun.beans.Soal;
import rifai.achmad.mathshogun.util.Work;

public class Quis extends AppCompatActivity {
    private int gold,waktu,nyawa,level,batasEXP,exp;
    private Soal soal;
    private boolean titikTerpakai=false,minTerpakai=false;
    private TextView soalText,jawab,timer;
    private Button submit,no1,no2,no3,no4,no5,no6,no7,no8,no9,no0,del,min,titik;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quis);
        rifai.achmad.mathshogun.util.Work.setImmersive(this.getWindow());
        binding();
        initDataPlayer();
        soalText.setText(""+soal);
        evente();
        startSoal();
    }

    private void startSoal() {
        countDownTimer=new CountDownTimer(10 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(Long.toString(l/1000));
            }
            @Override
            public void onFinish() {
                nyawa-=1;
                transferDatane();
            }
        };
        countDownTimer.start();
    }

    private void evente() {
        titik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titikTerpakai=true;
                titik.setEnabled(!titikTerpakai);
                String j=""+jawab.getText()+".";
                jawab.setText(j);
                checkAKurasi();
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
                checkAKurasi();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s.length()>1)jawab.setText(s.substring(0,s.length()-2));
                else jawab.setText("0");
                checkAKurasi();
            }
        });
        no0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s!="0")s+="0";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="1";
                else s+="1";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="2";
                else s+="2";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="3";
                else s+="3";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="4";
                else s+="4";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="5";
                else s+="5";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="6";
                else s+="6";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="7";
                else s+="7";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="8";
                else s+="8";
                jawab.setText(s);
                checkAKurasi();
            }
        });
        no9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=""+jawab.getText();
                if(s=="0")s="9";
                else s+="9";
                jawab.setText(s);
                checkAKurasi();
            }
        });
    }

    private void checkAKurasi() {
        String s=""+jawab.getText();
        submit.setEnabled(!s.equals(""));
    }

    @Override
    public void onBackPressed() {
        nyawa-=1;
        transferDatane();
    }

    private void jawabane() {
        if(soal.isBenar(Float.parseFloat(""+jawab.getText()))){
            gold+=20*level;
            exp+=20*level;
            chekcEXP();
        }else nyawa-=1;
        transferDatane();
    }

    private void transferDatane() {
        countDownTimer.cancel();
        Intent i=new Intent(this,GameActivity.class);
        Bundle b=new Bundle();
        b.putInt("waktu",5);
        b.putInt("nyawa",nyawa);
        b.putInt("level",level);
        b.putInt("batas_exp",batasEXP);
        b.putInt("exp",exp);
        b.putInt("gold",gold);
        i.replaceExtras(b);
        startActivity(i);
        finish();
    }

    private void chekcEXP() {
        if(exp>=batasEXP){
            int b=batasEXP;
            level++;
            batasEXP*=2;
            exp-=b;
            chekcEXP();
        }
    }

    private void binding() {
        soalText=(TextView)findViewById(R.id.quisSoal);
        jawab=(TextView)findViewById(R.id.quisJawab);
        submit=(Button)findViewById(R.id.quisSubmit);
        no0=(Button)findViewById(R.id.quisNo0);
        no1=(Button)findViewById(R.id.quisNo1);
        no2=(Button)findViewById(R.id.quisNo2);
        no3=(Button)findViewById(R.id.quisNo3);
        no4=(Button)findViewById(R.id.quisNo4);
        no5=(Button)findViewById(R.id.quisNo5);
        no6=(Button)findViewById(R.id.quisNo6);
        no7=(Button)findViewById(R.id.quisNo7);
        no8=(Button)findViewById(R.id.quisNo8);
        no9=(Button)findViewById(R.id.quisNo9);
        del=(Button)findViewById(R.id.quisDel);
        min=(Button)findViewById(R.id.quisMin);
        titik=(Button)findViewById(R.id.quisTitik);
        timer=(TextView)findViewById(R.id.quisWaktu);
    }

    private void initDataPlayer() {
        Bundle b=getIntent().getExtras();
        gold=b.getInt("gold");
        waktu=b.getInt("waktu");
        nyawa=b.getInt("nyawa");
        level=b.getInt("level");
        batasEXP=b.getInt("batas_exp");
        exp=b.getInt("exp");
        soal= Work.genSoal(level, Soal.TipeSoal.MUDAH);
    }
}
