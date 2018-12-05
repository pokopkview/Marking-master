package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.SubAreModel;

import java.util.List;

public class SubaAreaAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<SubAreModel> data;
    deleteItemClick deleteItemClick;

    public SubaAreaAdapter(Context context, List<SubAreModel> value) {
        mContext = context;
        data = value;
    }


    public void setListener(deleteItemClick listener){
        deleteItemClick = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new tempViewHolder(LayoutInflater.from(mContext).inflate(R.layout.subarea_manager_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((tempViewHolder)viewHolder).tvSubareaText.setText(data.get(i).getBed_number()+"");
        ((tempViewHolder)viewHolder).vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemClick.deleteClick(i);
            }
        });
        ((tempViewHolder)viewHolder).tvSubareaText.setText(data.get(i).getSubarea_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class tempViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubareaText;
        EditText etBedCount;
        View vDelete;

        public tempViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubareaText = itemView.findViewById(R.id.tv_subarea_text);
            etBedCount = itemView.findViewById(R.id.et_bed_count);
            vDelete = itemView.findViewById(R.id.v_delete);
        }
    }

    public interface deleteItemClick{
        void deleteClick(int position);
    }
}
