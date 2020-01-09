package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.os.Bundle;

public class CerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new achmad.rifai.mathshogun.view.CerView(this));
		Tools.imersiving(getWindow(), getActionBar());
	}

	@Override
	public void onBackPressed() {
		finish();
	}

}
