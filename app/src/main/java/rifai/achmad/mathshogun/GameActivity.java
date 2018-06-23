package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import rifai.achmad.mathshogun.util.Work;
import rifai.achmad.runner.GameController;
import rifai.achmad.runner.GameView;

//import rifai.achmad.ksatria.GameView;
//import rifai.achmad.runner.GameView;

public class GameActivity extends AppCompatActivity implements GameController.GameNext{
    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Work.setImmersive(getWindow());
        gv=new GameView(this,getIntent().getStringExtra("nama"));
        setContentView(gv);
    }

    @Override
    public void onBackPressed() {
        gv.destrooy();
        Toast.makeText(this,"Surender",Toast.LENGTH_LONG).show();
    }

    @Override
    public void endGame() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}
