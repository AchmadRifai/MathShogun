package rifai.achmad.mathshogun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        findViewById(R.id.opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option();
            }
        });
        findViewById(R.id.Score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScore();
            }
        });
        findViewById(R.id.aboutBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
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
        Jenenge j=Jenenge.getInstance();
        j.show(getFragmentManager(),"namamu");
        //startActivity(new Intent(this,GameActivity.class));
        //finish();
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