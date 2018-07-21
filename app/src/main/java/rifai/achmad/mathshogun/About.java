package rifai.achmad.mathshogun;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rifai.achmad.mathshogun.util.Work;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class About extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Work.setImmersive(this.getWindow());
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