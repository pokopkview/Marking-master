package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.BedStatusModel;
import com.intelligent.marking.net.model.TreatInfoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DuctInfoSonItemAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<BedStatusModel.DataBean> data;

    public DuctInfoSonItemAdapter(Context context, List<BedStatusModel.DataBean> value) {
        mContext = context;
        data = value;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bed_info_son_item_layout, viewGroup, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolders)viewHolder).tvDuctName.setText(data.get(i).getDuct_name());
        ((ViewHolders) viewHolder).tvDuctTime.setText(data.get(i).getInsert_time());
        ((ViewHolders) viewHolder).tvDuctPlace.setText(data.get(i).getOperate_place());
        ((ViewHolders) viewHolder).tvDuctUser.setText(data.get(i).getNurse_name());
        if(isFoot(i)){
            ((ViewHolders) viewHolder).view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        TextView tvDuctName;
        TextView tvDuctTime;
        TextView tvDuctPlace;
        TextView tvDuctUser;
        View view;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            tvDuctName = itemView.findViewById(R.id.tv_duct_name);
            tvDuctTime = itemView.findViewById(R.id.tv_duct_time);
            tvDuctPlace = itemView.findViewById(R.id.tv_duct_place);
            tvDuctUser = itemView.findViewById(R.id.tv_duct_user);
            view = itemView.findViewById(R.id.v_bottom_line);
        }


//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }

    private boolean isFoot(int pos){
        return pos == data.size()-1;
    }
}
