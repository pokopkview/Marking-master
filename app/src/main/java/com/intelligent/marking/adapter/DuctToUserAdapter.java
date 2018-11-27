package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.Utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DuctToUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    LayoutInflater mLayoutInflater;
    List<Object> objectList;
    DuctItemClick ductItemClick;
    public DuctToUserAdapter(Context context, List<Object> objects,DuctItemClick itemClick) {
        mLayoutInflater = LayoutInflater.from(context);
        objectList = objects;
        ductItemClick = itemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.duct_in_item_alyout, viewGroup, false);
        return new DuctViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //TODO  缺少数据
        ((DuctViewHolder)viewHolder).tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ductItemClick.slideChange(i);
            }
        });
        ((DuctViewHolder)viewHolder).tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ductItemClick.slideDelete(i);
            }
        });
        ((DuctViewHolder)viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ductItemClick.itemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }



    static class DuctViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_duct_name)
        TextView tvDuctName;
        @BindView(R.id.tv_duct_time)
        TextView tvDuctTime;
        @BindView(R.id.tv_duct_place)
        TextView tvDuctPlace;
        @BindView(R.id.tv_duct_user)
        TextView tvDuctUser;
        @BindView(R.id.tv_change)
        TextView tvChange;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        DuctViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface DuctItemClick{
        void slideChange(int position);
        void slideDelete(int position);
        void itemClick(int position);
    }
}
