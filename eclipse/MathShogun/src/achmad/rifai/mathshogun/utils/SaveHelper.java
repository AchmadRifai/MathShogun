package achmad.rifai.mathshogun.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveHelper extends SQLiteOpenHelper {

	public SaveHelper(Context context) {
		super(context, "MathShogun", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase d) {
		d.execSQL("create table atur(cerita int,vol int,dif int)");
		d.execSQL("insert into atur values(0,1,1)");
		d.execSQL("create table nilai(np text,dif int,tgl bigint,gold int,lvl int,hp int,exp int)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}

}
