package achmad.rifai.mathshogun.beans;

import android.view.MotionEvent;

public interface Layar {

	void awalan();

	boolean terus();

	boolean stun();

	void sentuh(MotionEvent e);

	void update();

	void hentikan();

	void simpan();

	void draw() throws java.io.IOException;

}
