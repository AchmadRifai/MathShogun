package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import rifai.achmad.japanese.GameModel;
import rifai.achmad.japanese.GameThread;
import rifai.achmad.japanese.GameView;
import rifai.achmad.japanese.SoalView;
import rifai.achmad.mathshogun.util.Work;

public class GameActivity extends AppCompatActivity implements GameThread.GameProcess{
    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gv=new GameView(this,getIntent().getStringExtra("nama"));
        Work.setImmersive(getWindow());
        setContentView(gv);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Surender",Toast.LENGTH_LONG).show();
        gv.stopGame();
    }

    @Override
    public void onquis(GameModel gm, GameThread gt) {
        SoalView sv=new SoalView(this,gm,gt);
        sv.show();
    }

    @Override
    public void endGame() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}
