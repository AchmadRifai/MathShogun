package achmad.rifai.mathshogun.view;

import achmad.rifai.mathshogun.beans.Layar;
import achmad.rifai.mathshogun.utils.ViewAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class InfoView extends android.view.SurfaceView implements SurfaceHolder.Callback {

	private Context ctx;
	private SurfaceHolder h;
	private Layar l;
	private ViewAnimator va;

	public InfoView(Context context) {
		super(context);
		ctx = context;
		h = getHolder();
		h.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		l = new InfoLayar(ctx, h, new android.graphics.Rect(0, 0, getWidth(), getHeight()));
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
	public void surfaceDestroyed(SurfaceHolder arg0) {
		va.henti();
	}

}
