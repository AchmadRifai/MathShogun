package rifai.achmad.mathshogun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import rifai.achmad.mathshogun.adapter.NilaiAdapter;
import rifai.achmad.mathshogun.util.Work;

import static rifai.achmad.dbne.Work.getAllNilai;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TheScore extends AppCompatActivity {
    private MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Work.setImmersive(this.getWindow());
        RecyclerView r=(RecyclerView)findViewById(R.id.scoreNilai);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new NilaiAdapter(getAllNilai(this)));
        mp=MediaPlayer.create(this,R.raw.bg_sound);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        mp.release();
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}