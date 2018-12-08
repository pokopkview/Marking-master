package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligent.marking.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DuctInfoSonItemAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<Object> data;

    public DuctInfoSonItemAdapter(Context context, List<Object> value) {
        mContext = context;
        data = value;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bed_info_son_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_duct_name)
        TextView tvDuctName;
        @BindView(R.id.tv_duct_time)
        TextView tvDuctTime;
        @BindView(R.id.tv_duct_place)
        TextView tvDuctPlace;
        @BindView(R.id.tv_duct_user)
        TextView tvDuctUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }
}
