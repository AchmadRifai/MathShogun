package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Tools.imersiving(getWindow(), getActionBar());
		new Thread(new Runnable() {
			@Override
			public void run() {
				mulaiSekarang();
			}}).start();
	}

	protected void mulaiSekarang() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} boolean b = Tools.wesCerito(getApplicationContext());
		if (b) startActivity(new Intent(this,DashActivity.class));
		else startActivity(new Intent(this,CerActivity.class));
		finish();
	}

}
