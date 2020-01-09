package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class DashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new achmad.rifai.mathshogun.view.DashView(this));
		Tools.imersiving(getWindow(), getActionBar());
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("EXIT?").setMessage("Do you want to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                getApplication().onTerminate();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); b.create().show();
	}

}
