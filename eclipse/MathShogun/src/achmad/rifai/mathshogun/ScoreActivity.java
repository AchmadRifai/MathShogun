package achmad.rifai.mathshogun;

import achmad.rifai.mathshogun.beans.Nilai;
import achmad.rifai.mathshogun.utils.Tools;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreActivity extends Activity {

	private LinearLayout l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		Tools.imersiving(getWindow(), getActionBar());
		l = findViewById(R.id.daftar);
		muat();
	}

	private void muat() {
		java.util.List<Nilai> ln = Tools.getNilai(getApplicationContext());
		boolean b = false;
		for (Nilai n : ln) {
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
					LayoutParams.WRAP_CONTENT));
			ll.setOrientation(LinearLayout.HORIZONTAL);
			if (b) ll.setBackgroundColor(Color.GRAY);
			else ll.setBackgroundColor(Color.WHITE);
			ll.setPadding(10, 10, 10, 10);
			setData(ll, n);
			l.addView(ll);
			b = !b;
		}
	}

	@SuppressLint("RtlHardcoded")
	private void setData(LinearLayout ll, Nilai n) {
		LinearLayout lll = new LinearLayout(this);
		lll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		lll.setOrientation(LinearLayout.VERTICAL);
		TextView a = new TextView(this), b = new TextView(this), c = new TextView(this);
		a.setText(n.getNp());
		b.setText("" + n.getTgl());
		c.setText("" + Tools.akumNilai(n));
		lll.addView(a);
		lll.addView(b);
		ll.addView(lll);
		ll.addView(c);
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, DashActivity.class));
		finish();
	}

}
