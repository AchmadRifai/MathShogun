package rifai.achmad.mathshogun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Catatan;

/**
 * Created by ai on 31/10/17.
 */

public class NilaiAdapter extends RecyclerView.Adapter<NilaiHolder>{
    private List<Catatan>l;

    public NilaiAdapter(List<Catatan> l) {
        this.l = l;
    }

    @Override
    public NilaiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iv= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.score_list_item,parent,false);
        return new NilaiHolder(iv);
    }

    @Override
    public void onBindViewHolder(NilaiHolder holder, int position) {
        Catatan c=l.get(position);
        holder.setData(c);
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}