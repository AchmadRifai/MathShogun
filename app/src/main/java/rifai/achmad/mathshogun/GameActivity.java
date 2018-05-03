package rifai.achmad.mathshogun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Surender",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,Dash.class));
        finish();
    }
}
