package rifai.achmad.japanese;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.Work;

public class SoalView extends Dialog{
    private Soal s;
    private GameThread gt;
    private GameModel gm;
    private Context ctx;
    private LinearLayout pusat;
    private boolean onTime;
    private TextView soalText,timerku;
    private EditText jawabe;
    private Button submit;
    private MediaPlayer mp;

    public SoalView(@NonNull Context context, GameModel gm, GameThread gt) {
        super(context);
        ctx=context;
        this.gm=gm;
        this.gt=gt;
        onTime=true;
        initView();
        muat();
        setContentView(pusat);
    }

    private void muat() {
        s= Work.genSoal(gm.getLevel(),Work.getMyMode(ctx));
        soalText.setText(""+s);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTime=false;
                submitProses();
            }
        });
    }

    private void initView() {
        pusat=new LinearLayout(ctx);
        timerku=new TextView(ctx);
        timerku.setText("10");
        pusat.setOrientation(LinearLayout.VERTICAL);
        LinearLayout l2=new LinearLayout(ctx);
        l2.setOrientation(LinearLayout.HORIZONTAL);
        pusat.addView(timerku);
        pusat.addView(l2);
        soalText=new TextView(ctx);
        submit=new Button(ctx);
        submit.setText(" = ");
        jawabe=new EditText(ctx);
        jawabe.setWidth(200);
        jawabe.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL
        |InputType.TYPE_CLASS_NUMBER);
        l2.addView(soalText);
        l2.addView(submit);
        l2.addView(jawabe);
    }

    @Override
    protected void onStart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                waktuJalan();
            }
        }).start();
    }

    private void waktuJalan() {
        for(int x=10;x>=0;x--){
            final int finalX = x;
            if(!onTime)return;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    timerku.setText(""+ finalX);
                }
            });if(!onTime)return;
            try {
                if(!onTime)return;
                Thread.sleep(1000);
                if(!onTime)return;
            } catch (InterruptedException e) {
                if(!onTime)return;
                e.printStackTrace();
                if(!onTime)return;
            }if(!onTime)return;
        }submitProses();
    }

    private void submitProses() {
        final String s=""+jawabe.getText();
        new Thread(new Runnable() {
            @Override
            public void run() {
                kalkulasi(s);
            }
        }).start();
        hide();
    }

    @Override
    protected void onStop() {
        onTime=false;
        submitProses();
    }

    private void kalkulasi(String s) {
        if(s!=""){
            if(this.s.isBenar(Float.parseFloat(s)))menang();
            else kalah();
        }else kalah();
        gm.normaling();
        gt.setQuis_on(false);
    }

    private void menang() {
        shogun1();
        shogun2();
        shogun3();
        shogun4();
        shogun5();
        shogun6();
        int l=gm.getLevel();
        gm.reward();
        if(l!=gm.getLevel())levelUp();
    }

    private void levelUp() {
        playAudio(R.raw.qigong2);
        gm.leveling();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun6() {
        playAudio(R.raw.kiri2);
        gm.shogunSlash6();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun5() {
        playAudio(R.raw.kiri1);
        gm.shogunSlash5();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun4() {
        playAudio(R.raw.kiri2);
        gm.shogunSlash4();
        gm.repaint();
        if(mp!=null) while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun3() {
        playAudio(R.raw.kiri1);
        gm.shogunSlash3();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun2() {
        playAudio(R.raw.kiri2);
        gm.shogunSlash2();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void shogun1() {
        playAudio(R.raw.kiri1);
        gm.shogunSLash1();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void kalah() {
        ronin1();
        ronin2();
        ronin3();
        ronin4();
        ronin5();
        ronin6();
        gm.injured();
    }

    private void ronin6() {
        playAudio(R.raw.kiri2);
        gm.roninSlash6();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void ronin5() {
        playAudio(R.raw.kiri1);
        gm.roninSlash5();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void ronin4() {
        playAudio(R.raw.kiri2);
        gm.roninSLash4();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void ronin3() {
        playAudio(R.raw.kiri1);
        gm.roninSlash3();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void ronin2() {
        playAudio(R.raw.kiri2);
        gm.roninSlash2();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void ronin1() {
        playAudio(R.raw.kiri1);
        gm.roninSlash1();
        gm.repaint();
        if(mp!=null)while (mp.isPlaying()){}
        clearAudio();
    }

    private void clearAudio() {
        if(mp!=null)mp.release();
    }

    private void playAudio(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mp=MediaPlayer.create(ctx,i);
                mp.start();
            }
        });
    }
}
