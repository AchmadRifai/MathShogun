package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new achmad.rifai.mathshogun.view.InfoView(this));
		Tools.imersiving(getWindow(), getActionBar());
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, DashActivity.class));
		finish();
	}

}
