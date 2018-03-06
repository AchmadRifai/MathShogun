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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}