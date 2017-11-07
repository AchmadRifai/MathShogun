package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import rifai.achmad.mathshogun.beans.Pengaturan;
import rifai.achmad.mathshogun.util.Work;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Atur extends AppCompatActivity {
    private Pengaturan p;
    private RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur);
        p= Work.muatSetting(this);
        rec=(RecyclerView)findViewById(R.id.colAtur);
    }

    @Override
    public void onBackPressed() {
        Work.simpanSetting(this,p);
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}