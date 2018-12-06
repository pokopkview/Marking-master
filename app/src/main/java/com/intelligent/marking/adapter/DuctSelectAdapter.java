package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intelligent.marking.R;

import java.util.List;

public class DuctSelectAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<Object> value;

    private ItemClick itemClick;


    public DuctSelectAdapter(Context context,List<Object> data){
        mContext = context;
        value = data;
    }

    public void setListener(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_duct_type_item_layout,viewGroup,false);
        return new ductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(i);
            }
        });
        ((ductViewHolder)viewHolder).tvDuctName.setText(value.get(i)+"");

    }

    @Override
    public int getItemCount() {
        return value.size();
    }

    static class ductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvDuctName;

        public ductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_duct_icon);
            tvDuctName = itemView.findViewById(R.id.tv_duct_name);
        }
    }

    public interface ItemClick{
        void itemClick(int position);
    }
}
