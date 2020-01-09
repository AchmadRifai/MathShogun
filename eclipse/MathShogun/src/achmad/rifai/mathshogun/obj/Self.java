package achmad.rifai.mathshogun.obj;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Self implements achmad.rifai.mathshogun.beans.Obj {

	private Context ctx;
	private Rect hitbox;
	private int indek;
	private String[] kump;

	public Self(Context ctx, Rect hitbox) {
		super();
		this.ctx = ctx;
		this.hitbox = hitbox;
		kump = new String[] {"nama : achmad rifa'i", "npm : 1412120058", "email : officialrdevel@gmail.com",
				"hp : +6285708347846", "motto : masa depan adalah pilihan, bukan dipilihkan. walaupun takdir tetap menentukan"};
		indek = 0;
	}

	@Override
	public void update() {
		indek++;
	}

	@Override
	public Rect kotak() {
		return hitbox;
	}

	public boolean sudah() {
		return indek >= kump.length;
	}

	@Override
	public void draw(Canvas c) throws IOException {
		drawBG(c);
		drawBorder(c);
		if (indek < kump.length) drawSay(c, kump[indek]);
	}

	private void drawSay(Canvas c, String s) throws IOException {
		int width = hitbox.right / 44, height = hitbox.bottom / 12, r = 1, b = 1;
        for (char ch : s.toCharArray()) {
            String path = "huds/";
            if (ch == '.') path += "dot.png";
            else if (ch == ' ' ) path += "space.png";
            else if (ch == '\'' ) path += "qts.png";
            else if (ch == '@' ) path += "at.png";
            else if (ch == ':' ) path += "colon.png";
            else if (ch == ',' ) path += "comma.png";
            else if (ch == '+' ) path += "plus.png";
            else path += ch + ".png";
            InputStream i = ctx.getAssets().open(path);
            c.drawBitmap(BitmapFactory.decodeStream(i), null,
                    new Rect(hitbox.left + (width * r), hitbox.top + (height * b), hitbox.left + (width * (r + 1)),
                    		hitbox.top + (height * (b + 1))), null);
            r++;
            if(r == 42){
                r = 1;
                b++;
            } i.close();
        }
	}

	private void drawBorder(Canvas c) {
		Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        c.drawRect(hitbox, p);
	}

	private void drawBG(Canvas c) {
		Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        c.drawRect(hitbox, p);
	}

}
