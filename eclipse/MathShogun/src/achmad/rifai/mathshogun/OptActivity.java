package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.beans.Atur;
import achmad.rifai.mathshogun.utils.Soal;
import achmad.rifai.mathshogun.utils.Tools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class OptActivity extends Activity {

	private RadioGroup dif;
	private SeekBar vol;
	private Atur a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opt);
		Tools.imersiving(getWindow(), getActionBar());
		binding();
	}

	private void binding() {
		dif = findViewById(R.id.dif);
		vol = findViewById(R.id.vol);
		a = Tools.getAtur(this);
		vol.setProgress(a.getVol());
		dif.check(a.getDif() == Soal.Tingkat.MUDAH ? R.id.mudah : a.getDif() == Soal.Tingkat.SEDANG 
				? R.id.sedang : R.id.sulit);
	}

	@Override
	public void onBackPressed() {
		a.setDif(R.id.mudah == dif.getCheckedRadioButtonId() ? Soal.Tingkat.MUDAH : 
			R.id.sedang == dif.getCheckedRadioButtonId() ? Soal.Tingkat.SEDANG : Soal.Tingkat.SULIT);
		a.setVol(vol.getProgress());
		Tools.saveAtur(a, this);
		startActivity(new Intent(this, DashActivity.class));
		finish();
	}

}
