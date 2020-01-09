package achmad.rifai.mathshogun.view;

import achmad.rifai.mathshogun.utils.ViewAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class CerView extends android.view.SurfaceView implements SurfaceHolder.Callback {

	private Context ctx;
	private SurfaceHolder h;
	private achmad.rifai.mathshogun.beans.Layar l;
	private ViewAnimator va;

	public CerView(Context context) {
		super(context);
		ctx = context;
		h = getHolder();
		h.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		l = new CerLayar(ctx, h, new android.graphics.Rect(0, 0, getWidth(), getHeight()));
		va = new ViewAnimator(l);
		va.start();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		l.sentuh(event);
		return false;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		va.henti();
	}

}
