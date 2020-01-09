package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.utils.Tools;
import achmad.rifai.mathshogun.view.PlayView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PlayActivity extends Activity {

	private PlayView pv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pv = new PlayView(this, getIntent().getStringExtra("np"));
		setContentView(pv);
		Tools.imersiving(getWindow(), getActionBar());
	}

	@Override
	public void onBackPressed() {
		pv.surfaceDestroyed(null);
		Toast.makeText(this, "Surender", Toast.LENGTH_LONG).show();
	}

}
