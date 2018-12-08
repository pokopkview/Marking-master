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

import java.util.ArrayList;
import java.util.List;


public class BedDuctInfoAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<Object> data;


    public BedDuctInfoAdapter(Context context, List<Object> value) {
        mContext = context;
        data = value;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bed_duct_info_item_layout, viewGroup, false);
        return new bedDuctInfoViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((bedDuctInfoViewHoler)viewHolder).tvBedName.setText(data.get(i).toString());
        ((bedDuctInfoViewHoler) viewHolder).tvBedPatientInfo.setText("");
        ((bedDuctInfoViewHoler) viewHolder).rlBedPatientInfo.setLayoutManager(new LinearLayoutManager(mContext));
        List<Object> value = new ArrayList<>();
        value.add("1");
        value.add("1");
        value.add("1");
        value.add("1");
        DuctInfoSonItemAdapter adapter = new DuctInfoSonItemAdapter(mContext,value);
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
