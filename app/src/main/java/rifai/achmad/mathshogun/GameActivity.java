package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rifai.achmad.gg.GameView;
import rifai.achmad.gg.QuisFragment;
import rifai.achmad.mathshogun.util.Work;

public class GameActivity extends AppCompatActivity implements QuisFragment.GameResult{
    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Work.setImmersive(getWindow());
        gv=(GameView)findViewById(R.id.gameView);
        gv.setNama(getIntent().getStringExtra("nama"));
    }

    @Override
    public void onBackPressed() {
        gv.saveScore();
        Toast.makeText(this,"Surender",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    @Override
    public void menang() {
        gv.menang();
    }

    @Override
    public void kalah() {
        gv.kalah();
        if(gv.isLose()){
            gv.saveScore();
            startActivity(new Intent(this,Dash.class));
            finish();
        }
    }
}
