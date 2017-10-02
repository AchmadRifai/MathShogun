package rifai.achmad.mathshogun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Cerita extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerita);
        (findViewById(R.id.skipCerita)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setuju();
            }
        });
    }

    private void setuju() {
        List<rifai.achmad.mathshogun.beans.Catatan>l=new LinkedList<>();
        try {
            rifai.achmad.mathshogun.util.Work.setData(l);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }startActivity(new Intent(this,Dash.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}