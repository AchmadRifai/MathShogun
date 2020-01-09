package achmad.rifai.mathshogun.utils;

import achmad.rifai.mathshogun.PlayActivity;
import achmad.rifai.mathshogun.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class NpDialog extends Dialog {

	private android.widget.EditText np;
	private android.widget.Button btn;
	private Context ctx;

	public NpDialog(Context context) {
		super(context);
		ctx = context;
		setContentView(R.layout.nama_anda);
		binding();
		evene();
	}

	private void evene() {
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kerjakan();
			}
		});
	}

	protected void kerjakan() {
		String s = "" + np.getText();
		if (!"".equals(s)) {
			Activity a = (Activity) ctx;
			if (a != null) {
				Intent i = new Intent(a, PlayActivity.class);
				i.putExtra("np", s);
				a.startActivity(i);
				a.finish();
			}
		} else Toast.makeText(getContext(), "Tolong tuliskan nama anda", Toast.LENGTH_SHORT).show();
	}

	private void binding() {
		np = findViewById(R.id.np);
		btn = findViewById(R.id.submit);
	}

}
