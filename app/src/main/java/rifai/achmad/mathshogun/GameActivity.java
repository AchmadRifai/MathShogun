package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

//import rifai.achmad.ksatria.GameView;
import rifai.achmad.mathshogun.util.Work;
import rifai.achmad.runner.GameView;
import rifai.achmad.runner.MyThreat;
//import rifai.achmad.runner.GameView;

public class GameActivity extends AppCompatActivity implements MyThreat.GameProcess{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(new GameView(this,null));
        //setContentView(new GameView(this));
        setContentView(new GameView(this,getIntent().getStringExtra("nama")));
        Work.setImmersive(getWindow());
    }

    @Override
    public void onBackPressed() {
        //gv.saveScore();
        Toast.makeText(this,"Surender",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    @Override
    public void endGame() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}
