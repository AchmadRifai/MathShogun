package rifai.achmad.mathshogun;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import rifai.achmad.mathshogun.util.Work;

public class Cerita extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerita);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Work.setImmersive(this.getWindow());
        (findViewById(R.id.skipCerita)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setuju();
            }
        });
    }

    private void setuju() {
        rifai.achmad.dbne.Work.terbaca(this);
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}