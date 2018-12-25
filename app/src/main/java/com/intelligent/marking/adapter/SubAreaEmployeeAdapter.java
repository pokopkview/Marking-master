package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.NurseInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAreaEmployeeAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<NurseInfo> data = new ArrayList<>();
    DeleteItemClick deleteItemClick;


    public SubAreaEmployeeAdapter(Context context,DeleteItemClick deleteItemClick) {
        mContext = context;
        this.deleteItemClick = deleteItemClick;
    }

    public void setDate(List<NurseInfo> data){
        System.out.println("setDate");
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addItemData(NurseInfo nurseInfo){
        this.data.add(nurseInfo);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        data.remove(position);
        notifyDataSetChanged();
    }
    public List<NurseInfo> getListDate(){
        return data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NurseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.edit_employee_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((NurseViewHolder)viewHolder).tvEmployeeName.setText(data.get(i).getNurse_name());
        ((NurseViewHolder) viewHolder).tvNurseNo.setText(data.get(i).getNurse_no());
        ((NurseViewHolder) viewHolder).rlDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemClick.clickitem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class NurseViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmployeeName;
        RelativeLayout rlDeleteView;
        TextView tvNurseNo;


        public NurseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeName = itemView.findViewById(R.id.tv_employee_name);
            tvNurseNo = itemView.findViewById(R.id.tv_nurse_no);
            rlDeleteView = itemView.findViewById(R.id.rl_delete_view);
        }
    }
    public interface DeleteItemClick{
        void clickitem(int position);
    }
}
