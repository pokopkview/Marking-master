package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.net.model.BedStatusModel;

import java.util.ArrayList;
import java.util.List;


public class BedDuctInfoAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<BedStatusModel> data;


    public BedDuctInfoAdapter(Context context, List<BedStatusModel> value) {
        mContext = context;
        data = value;
        System.out.println("data:"+data.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bed_duct_info_item_layout, viewGroup, false);
        return new bedDuctInfoViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((bedDuctInfoViewHoler)viewHolder).tvBedName.setText(data.get(i).getBed_name());
        ((bedDuctInfoViewHoler) viewHolder).tvBedPatientInfo.setText(data.get(i).getName()+"\u3000"+mContext.getResources().getStringArray(R.array.sex)[data.get(i).getSex()]+"\u3000"+data.get(i).getAge()+"岁");
        ((bedDuctInfoViewHoler) viewHolder).rlBedPatientInfo.setLayoutManager(new LinearLayoutManager(mContext));
        DuctInfoSonItemAdapter adapter = new DuctInfoSonItemAdapter(mContext,data.get(i).getData());
        ((bedDuctInfoViewHoler) viewHolder).rlBedPatientInfo.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class bedDuctInfoViewHoler extends RecyclerView.ViewHolder {
        TextView tvBedName;
        TextView tvBedPatientInfo;
        RecyclerView rlBedPatientInfo;

        public bedDuctInfoViewHoler(@NonNull View itemView) {
            super(itemView);
            tvBedName = itemView.findViewById(R.id.tv_bed_name);
            tvBedPatientInfo = itemView.findViewById(R.id.tv_bed_patient_info);
            rlBedPatientInfo = itemView.findViewById(R.id.rl_bed_patient_info);
        }
    }
}
