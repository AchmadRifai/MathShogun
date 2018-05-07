package rifai.achmad.mathshogun;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Jenenge extends DialogFragment{
    private EditText nama;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.namapemain,container,false);
        binding(v);
        evene();
        return v;
    }

    private void evene() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),GameActivity.class);
                i.putExtra("nama",""+nama.getText());
                startActivity(i);
                getActivity().finish();
            }
        });
    }

    private void binding(View v) {
        submit=(Button)v.findViewById(R.id.startMyGame);
        nama=(EditText)v.findViewById(R.id.namaAnda);
    }

    public static Jenenge getInstance(){
        return new Jenenge();
    }
}
