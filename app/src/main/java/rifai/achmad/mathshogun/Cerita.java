package rifai.achmad.mathshogun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import rifai.achmad.mathshogun.util.Work;

public class Cerita extends Activity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerita);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Work.setImmersive(this.getWindow());
        (findViewById(R.id.skipCerita)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setuju();
            }
        });
        mp=MediaPlayer.create(this,R.raw.bg_sound);
        mp.setLooping(true);
        mp.start();
    }

    private void setuju() {
        rifai.achmad.dbne.Work.terbaca(this);
        mp.stop();
        mp.release();
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        mp.release();
        finish();
    }
}