package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import rifai.achmad.mathshogun.ongame.Pemain;

public class GameActivity extends AppCompatActivity {
    private int waktu,life,gold,exp,batasEXP,level;
    private TextView detik,nyawa,emas,xp,be,lvl;
    private boolean run;
    private Pemain pl;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        run=true;
        //pl=new Pemain(null,null,null,null);
        binding();
        initData();
        startGame();
    }

    private void startGame() {
        countDownTimer=new CountDownTimer(waktu * 1000, 1000) {
            @Override
            public void onTick(long l) {
                setWaktu((int) (l/1000));
            }
            @Override
            public void onFinish() {
                Intent i=new Intent(GameActivity.this,Quis.class);
                Bundle b=new Bundle();
                b.putInt("waktu",waktu);
                b.putInt("nyawa",life);
                b.putInt("level",level);
                b.putInt("batas_exp",batasEXP);
                b.putInt("exp",exp);
                b.putInt("gold",gold);
                i.replaceExtras(b);
                startActivity(i);
                finish();
            }
        };
        if(life>0&&level<5) countDownTimer.start();
        else kalah();
    }

    private void kalah() {
        countDownTimer.cancel();
        Toast.makeText(this,"GAME OVER",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    private void initData() {
        Bundle b=getIntent().getExtras();
        setWaktu(b.getInt("waktu"));
        setBatasEXP(b.getInt("batas_exp"));
        setLife(b.getInt("nyawa"));
        setLevel(b.getInt("level"));
        setGold(b.getInt("gold"));
        setExp(b.getInt("exp"));
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        Toast.makeText(GameActivity.this,"Menyerah",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    private void binding() {
        detik=(TextView)findViewById(R.id.waktu);
        nyawa=(TextView)findViewById(R.id.life);
        emas=(TextView)findViewById(R.id.gold);
        xp=(TextView)findViewById(R.id.exp);
        be=(TextView)findViewById(R.id.batasEXP);
        lvl=(TextView)findViewById(R.id.level);
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
        detik.setText(""+waktu);
        run=waktu>0;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        nyawa.setText(""+life+" Nyawa");
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
        emas.setText(""+gold+" Gold");
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        xp.setText(""+exp+" EXP");
        if(exp>=batasEXP){
            int b=batasEXP;
            setLevel(getLevel()+1);
            setBatasEXP(getBatasEXP()*2);
            setExp(getExp()-b);
        }
    }

    public int getBatasEXP() {
        return batasEXP;
    }

    public void setBatasEXP(int batasEXP) {
        this.batasEXP = batasEXP;
        be.setText(""+batasEXP+" EXP");
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        lvl.setText("Level "+level);
    }
}
