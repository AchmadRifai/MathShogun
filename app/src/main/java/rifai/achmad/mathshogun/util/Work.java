package rifai.achmad.mathshogun.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.view.View;
import android.view.Window;

import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import rifai.achmad.mathshogun.Atur;
import rifai.achmad.mathshogun.beans.Catatan;
import rifai.achmad.mathshogun.beans.Pengaturan;
import rifai.achmad.mathshogun.beans.Soal;

/**
 * Created by ai on 22/09/2017.
 */

public class Work {
    private static File f=new File(Environment.getDataDirectory().getAbsolutePath()+
            "/.MathShogun/catat.xml");

    public static boolean isCerita(Activity a) {
        boolean b=false;
        ConnHelper helper=new ConnHelper(a);
        SQLiteDatabase d=helper.getReadableDatabase();
        Cursor c=d.query("tenger",new String[]{"aku"},null,null,null,null,null);
        if(c.moveToFirst())b=1==c.getInt(c.getColumnIndex("aku"));
        c.close();
        d.close();
        helper.close();
        return b;
    }

    public static void setData(List<Catatan> l) {
        try {
            Document d= DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element e=d.createElement("data");
            d.appendChild(e);
            for(Catatan c:l)e.appendChild(genCatatan(d,c));
            save(d);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private static Node genCatatan(Document d, Catatan c) {
        Element e=d.createElement("catatan");
        e.setAttribute("nama",c.getNama());
        e.setAttribute("tgl",""+c.getTgl());
        e.setAttribute("gold",""+c.getGold());
        e.setAttribute("level",""+c.getLevel());
        e.setAttribute("point",""+c.getPoint());
        return e;
    }

    private static void save(Document d) throws TransformerException {
        DOMSource ds=new DOMSource(d);
        StreamResult sr=new StreamResult(f);
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        Transformer t= TransformerFactory.newInstance().newTransformer();
        t.transform(ds,sr);
    }

    public static void terbaca(Activity a) {
        ConnHelper helper=new ConnHelper(a);
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("aku",1);
        db.update("tenger",cv,null,null);
        db.close();
        helper.close();
    }

    public static List<Catatan> readNilai(Context c) {
        List<Catatan>l=new LinkedList<>();
        ConnHelper con=new ConnHelper(c);
        SQLiteDatabase db=con.getReadableDatabase();
        String sql="select id from pemain order by nilai desc;";
        Cursor cur=db.rawQuery(sql,null);
        cur.moveToFirst();
        if(0<cur.getCount())while (!cur.isLast()){
            l.add(genCatatane(con,cur.getInt(cur.getColumnIndex("id"))));
            cur.moveToNext();
        }cur.close();
        db.close();
        con.close();
        return l;
    }

    private static Catatan genCatatane(ConnHelper con, int id) {
        Catatan c=new Catatan();
        SQLiteDatabase db=con.getReadableDatabase();
        String[]fn={"nama","tingkat","uang","nilai","tgl"};
        String kondisi="id=?";
        String[]keadaan={""+id};
        Cursor cur=db.query("pemain",fn,kondisi,keadaan,null,null,null);
        cur.moveToFirst();
        c.setGold(cur.getInt(cur.getColumnIndex("uang")));
        c.setLevel(cur.getInt(cur.getColumnIndex("tingkat")));
        c.setNama(cur.getString(cur.getColumnIndex("nama")));
        c.setPoint(cur.getInt(cur.getColumnIndex("nilai")));
        c.setTgl(DateTime.parse(cur.getString(cur.getColumnIndex("tgl"))));
        cur.close();
        db.close();
        return c;
    }

    public static void setImmersive(Window w) {
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public static Soal genSoal(int level, Soal.TipeSoal tipeSoal) {
        int op,awal=1,akhir,add;
        Soal s=new Soal();
        if(tipeSoal== Soal.TipeSoal.MUDAH)op=genRandom(1,2);
        else op=genRandom(1,4);
        if(op==1)s.setOperasi(Soal.SoalOperasi.TAMBAH);
        else if(op==2)s.setOperasi(Soal.SoalOperasi.KURANG);
        else if(op==3)s.setOperasi(Soal.SoalOperasi.KALI);
        else if(op==4)s.setOperasi(Soal.SoalOperasi.BAGI);
        if(tipeSoal== Soal.TipeSoal.SULIT)add=10;
        else add=5;
        akhir=add;
        for(int x=1;x<level;x++){
            awal+=add;
            akhir+=add;
        }s.setAngka2(genRandom(awal,akhir));
        s.setAngka1(genRandom(awal,akhir));
        return s;
    }

    private static int genRandom(int i, int i1) {
        Random r=new Random();
        return r.nextInt((i1-i)+1)+i;
    }
}