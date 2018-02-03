package rifai.achmad.mathshogun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        findViewById(R.id.playStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainSekarang();
            }
        });
        rifai.achmad.mathshogun.util.Work.setImmersive(this.getWindow());
        findViewById(R.id.Score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highScore();
            }
        });
        findViewById(R.id.opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option();
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
        startActivity(new Intent(this,Dash.class));
        finish();
    }

    private void highScore() {
        startActivity(new Intent(this,TheScore.class));
        finish();
    }

    private void mainSekarang() {
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