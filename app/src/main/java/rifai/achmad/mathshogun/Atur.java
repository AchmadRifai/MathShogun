package rifai.achmad.mathshogun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rifai.achmad.mathshogun.adapter.AturAdapter;
import rifai.achmad.mathshogun.beans.Pengaturan;
import rifai.achmad.mathshogun.beans.Setting;
import rifai.achmad.mathshogun.util.Work;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Atur extends AppCompatActivity {
    private Pengaturan p;
    private RecyclerView rec;
    private AturAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Work.setImmersive(this.getWindow());
        p= Work.muatSetting(this);
        rec=(RecyclerView)findViewById(R.id.colAtur);
        muat();
    }

    private void muat() {
        adapter=new AturAdapter(genAtur(p));
        rec.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Setting> genAtur(Pengaturan p) {
        List<Setting>l=new LinkedList<>();
        Setting batas=new Setting();
        batas.setNama("Batas Level");
        batas.setNilai(p.getBatas());
        batas.setBatasBawah(2);
        batas.setBatasAtas(Integer.MAX_VALUE);
        l.add(batas);
        Setting muni=new Setting();
        muni.setNama("Suara");
        muni.setNilai(p.isMuni()?1:0);
        muni.setBatasAtas(1);
        muni.setBatasBawah(0);
        l.add(muni);
        Setting sulit=new Setting();
        sulit.setNama("Tingkat Kesulitan");
        sulit.setNilai(p.getSulit());
        sulit.setBatasBawah(1);
        sulit.setBatasAtas(3);
        l.add(sulit);
        return l;
    }

    @Override
    public void onBackPressed() {
        changeSaver();
        Work.simpanSetting(this,p);
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    private void changeSaver() {
    }
}