package rifai.achmad.gg;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rifai.achmad.dbne.Soal;
import rifai.achmad.dbne.Work;

@SuppressLint("ValidFragment")
public class QuisFragment extends DialogFragment {
    public interface GameResult{
        void menang();
        void kalah();
    }

    private int level;
    private Soal.TipeSoal tipe;
    private Soal soal;
    private GameResult gr;
    private CountDownTimer cdt;
    private TextView text,count;
    private EditText jawab;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.quis_bro,container,false);
        binding(v);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOM();
            }
        });return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        cdt=new CountDownTimer(10*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count.setText(Long.toString(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                checkOM();
            }
        };cdt.start();
    }

    private void checkOM() {
        if(""!=""+jawab.getText()){
            if(soal.isBenar(Float.parseFloat(""+jawab.getText())))gr.menang();
            else gr.kalah();
        }else gr.kalah();
        dismiss();
    }

    private void binding(View v) {
        text=(TextView)v.findViewById(R.id.soalText);
        count=(TextView)v.findViewById(R.id.countDown);
        jawab=(EditText)v.findViewById(R.id.jawabKu);
        submit=(Button)v.findViewById(R.id.submit);
    }

    public QuisFragment(int level) {
        this.level = level;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        gr= (GameResult) context;
        tipe= Work.getMyMode(context);
        soal=Work.genSoal(level,tipe);
        text.setText(""+soal);
    }
}
