package rifai.achmad.runner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.Work;

public class Quise extends Dialog{
    private Soal soal;
    private Soal.TipeSoal tipeSoal;
    private Player player;
    private Game game;
    private EditText jawab;
    private TextView count,soale;
    private Thread t;

    public Quise(@NonNull Context context, Soal.TipeSoal tipeSoal, Player player, Game game) {
        super(context);
        this.tipeSoal=tipeSoal;
        this.player=player;
        this.game=game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        genSoal();
        setContentView(R.layout.quise);
        binding();
    }

    private void genSoal() {
        soal= Work.genSoal(player.getLevel(),tipeSoal);
    }

    private void binding() {
        jawab=(EditText)findViewById(R.id.jawab);
        jawab.setText("0");
        count=(TextView)findViewById(R.id.mundur);
        soale=(TextView)findViewById(R.id.soaleSu);
        soale.setText(""+soal);
        jawab.setKeepScreenOn(true);
        jawab.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    t.interrupt();
                    simpanJawaban();
                    return true;
                }return false;
            }
        });
    }

    @Override
    protected void onStart() {
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=10;x>0;x--) {
                    final int finalX = x;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            count.setText(""+ finalX);
                        }
                    });try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        simpanJawaban();
                    }
                });
            }
        });t.start();
    }

    private void simpanJawaban() {
        if(!"".equals("" + jawab.getText())){
            if(soal.isBenar(Float.parseFloat(""+jawab.getText()))){
                player.setGold(20*player.getLevel()+player.getGold());
                player.setExp(20*player.getLevel()+player.getExp());
            }else player.setNyawa(player.getNyawa()-1);
        }else player.setNyawa(player.getNyawa()-1);
        if(0==player.getNyawa()||5==player.getLevel())game.setState(Game.State.LOST);
        else game.setState(Game.State.RUN);
        dismiss();
    }
}
