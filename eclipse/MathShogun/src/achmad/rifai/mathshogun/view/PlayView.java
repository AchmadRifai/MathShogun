package achmad.rifai.mathshogun.view;

import achmad.rifai.mathshogun.utils.ViewAnimator;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PlayView extends SurfaceView implements SurfaceHolder.Callback {

	private Context ctx;
	private String np;
	private SurfaceHolder h;
	private achmad.rifai.mathshogun.beans.Layar l;
	private ViewAnimator va;

	public PlayView(Context context, String np) {
		super(context);
		this.np = np;
		ctx = context;
		h = getHolder();
		h.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		l = new PlayLayar(ctx, h, new android.graphics.Rect(0, 0, getWidth(), getHeight()), np);
		va = new ViewAnimator(l);
		va.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		va.henti();
	}

}
