package rifai.achmad.mathshogun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import rifai.achmad.mathshogun.ongame.GameView;
import rifai.achmad.mathshogun.util.Work;

public class GameActivity extends AppCompatActivity {
    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Work.setImmersive(getWindow());
        gv=(GameView)findViewById(R.id.permainannya);
    }

    @Override
    public void onBackPressed() {
        gv.pause();
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pindah();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gv.resume();
                dialogInterface.dismiss();
            }
        }).setTitle("Exit Game").setMessage("Are you sure to exit this progress?").create().show();
    }

    private void pindah() {
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}
