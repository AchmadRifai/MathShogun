package rifai.achmad.mathshogun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rifai.achmad.mathshogun.R;
import rifai.achmad.mathshogun.beans.Setting;

/**
 * Created by ai on 03/02/2018.
 */

public class AturAdapter extends RecyclerView.Adapter<AturHolder>{
    private List<Setting>l;

    public AturAdapter(List<Setting> l) {
        this.l = l;
    }

    @Override
    public AturHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iv= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.atur_list_item,parent,false);
        return new AturHolder(iv);
    }

    @Override
    public void onBindViewHolder(AturHolder holder, int position) {
        Setting s=l.get(position);
        holder.setS(s);
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}
