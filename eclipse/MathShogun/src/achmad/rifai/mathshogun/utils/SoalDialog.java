package achmad.rifai.mathshogun.utils;

import java.io.IOException;

import achmad.rifai.mathshogun.R;
import achmad.rifai.mathshogun.beans.Korektor;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SoalDialog extends Dialog {

	private Soal s;
	private Korektor k;
	private TextView pewaktu, soale;
	private Button pilA, pilB, pilC, pilD;
	private boolean onTime;

	public SoalDialog(Context context, Soal s, Korektor k) {
		super(context);
		setContentView(R.layout.quis_fight);
		this.s = s;
		this.k = k;
		onTime = true;
		binding();
		muat();
		evene();
	}

	@Override
	protected void onStart() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				kala();
			}}).start();
		super.onStart();
	}

	@Override
	public void cancel() {
		pilih(0);
		super.cancel();
	}

	protected void kala() {
		for (int i = 10; i >= 0; i--) if (onTime) {
			cetakWaktu(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else break;
		if (onTime) {
			pilih(0);
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					dismiss();
				}});
		}
	}

	private void cetakWaktu(final int i) {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				pewaktu.setText("" + i);
			}});
	}

	private void evene() {
		pilA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pilih(1);
				dismiss();
			}
		}); pilB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pilih(2);
				dismiss();
			}
		}); pilC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pilih(3);
				dismiss();
			}
		}); pilD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pilih(4);
				dismiss();
			}
		});
	}

	private void muat() {
		soale.setText("" + s);
		pilA.setText("A. " + s.getPilA());
		pilB.setText("B. " + s.getPilB());
		pilC.setText("C. " + s.getPilC());
		pilD.setText("D. " + s.getPilD());
	}

	private void binding() {
		pewaktu = findViewById(R.id.pewaktu);
		soale = findViewById(R.id.soale);
		pilA = findViewById(R.id.pil_a);
		pilB = findViewById(R.id.pil_b);
		pilC = findViewById(R.id.pil_c);
		pilD = findViewById(R.id.pil_d);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		pilih(0);
	}

	private void pilih(final int i) {
		onTime = false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (i == s.getJawab()) k.benar();
					else k.salah();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}).start();
	}

}
