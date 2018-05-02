package rifai.achmad.mathshogun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Dash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rifai.achmad.mathshogun.util.Work.setImmersive(this.getWindow());
        findViewById(R.id.playStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainSekarang();
            }
        });
    }

    private void about() {
        startActivity(new Intent(this,About.class));
        finish();
    }

    private void option() {
        startActivity(new Intent(this,Atur.class));
        finish();
    }

    private void highScore() {
        startActivity(new Intent(this,TheScore.class));
        finish();
    }

    private void mainSekarang() {
        Intent i=new Intent(this,GameActivity.class);
        Bundle b=new Bundle();
        b.putInt("waktu",5);
        b.putInt("nyawa",5);
        b.putInt("level",1);
        b.putInt("batas_exp",100);
        b.putInt("exp",0);
        b.putInt("gold",0);
        i.replaceExtras(b);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle("Exit").setMessage("Do You exit now?").create().show();
    }
}