package rifai.achmad.mathshogun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Work.setImmersive(this.getWindow());
        RecyclerView r=(RecyclerView)findViewById(R.id.scoreNilai);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new NilaiAdapter(getAllNilai(this)));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}