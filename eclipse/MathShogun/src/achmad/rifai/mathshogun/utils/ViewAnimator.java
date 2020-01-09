package achmad.rifai.mathshogun.utils;

import java.io.IOException;

import achmad.rifai.mathshogun.beans.Layar;

public class ViewAnimator extends Thread {

	private Layar l;

	public ViewAnimator(Layar l) {
		super();
		this.l = l;
	}

	@Override
	public void run() {
		super.run();
		l.awalan();
		while (l.terus()) {
			if (!l.stun()) try {
				l.draw();
				l.update();
			} catch (IOException e) {
				e.printStackTrace();
			} try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} l.simpan();
	}

	public void henti() {
		l.hentikan();
	}

}
