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
import com.intelligent.marking.net.model.BedInfoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<BedInfoModel> date;
    private Context mContext;
    private LayoutInflater mLayoutInflate;
    private boolean isDetele = false;
    private BedInfoViewholer.ItemSelectListener selectListener;


    public RecyPagerAdapter(Context context, List<BedInfoModel> date) {
        this.date = date;
        this.mContext = context;
        mLayoutInflate = LayoutInflater.from(mContext);
    }


    public boolean getDelete(){
        return isDetele;
    }

    public void setDeleteVisi(boolean isDetele){
        this.isDetele = isDetele;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        BedInfoViewholer viewholer = new BedInfoViewholer(mLayoutInflate.inflate(R.layout.bed_info_item_layout, viewGroup, false));
        viewholer.setIsRecyclable(false);
        return viewholer;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (date.get(i).getIs_empty_bed() == 1) {
            ((BedInfoViewholer) viewHolder).bedNull.setVisibility(View.VISIBLE);
            ((BedInfoViewholer) viewHolder).tvBedName.setVisibility(View.GONE);
            ((BedInfoViewholer) viewHolder).tvSexAge.setVisibility(View.GONE);
            ((BedInfoViewholer) viewHolder).bedBg.setBackgroundResource(R.drawable.bed_status_gray_selector);
            ((BedInfoViewholer) viewHolder).tvNumber.setText(date.get(i).getBed_name());
        } else {
            ((BedInfoViewholer) viewHolder).tvBedName.setText(date.get(i).getName());
            ((BedInfoViewholer) viewHolder).tvSexAge.setText((date.get(i).getSex() == 0 ? "未知" : date.get(i).getSex() == 1 ? "男" : "女") + "-" + date.get(i).getAge() + "岁");
            ((BedInfoViewholer) viewHolder).bedNull.setVisibility(View.GONE);
            ((BedInfoViewholer) viewHolder).tvNumber.setText(date.get(i).getBed_name());
            ((BedInfoViewholer) viewHolder).bedBg.setBackgroundResource(R.drawable.bed_status_selector);
        }







        if(!isDetele){
            ((BedInfoViewholer) viewHolder).vDelete.setVisibility(View.GONE);
        }else{
            ((BedInfoViewholer) viewHolder).vDelete.setVisibility(View.VISIBLE);
        }
        ((BedInfoViewholer) viewHolder).vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.deleteClick(i);
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.itemClick(i);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                selectListener.itemLongClick(i);
                return false;
            }
        });

        switch (date.get(i).getShow_color()) {
            case "red":
                ((BedInfoViewholer) viewHolder).tvMsgCount.setBackgroundResource(R.mipmap.red);
                ((BedInfoViewholer) viewHolder).tvMsgCount.setText(date.get(i).getShow_number());
                ((BedInfoViewholer) viewHolder).tvMsgCount.setVisibility(View.VISIBLE);
                break;
            case "orange":
                ((BedInfoViewholer) viewHolder).tvMsgCount.setBackgroundResource(R.mipmap.orange);
                ((BedInfoViewholer) viewHolder).tvMsgCount.setText(date.get(i).getShow_number());
                ((BedInfoViewholer) viewHolder).tvMsgCount.setVisibility(View.VISIBLE);
                break;
            case "yellow":
                ((BedInfoViewholer) viewHolder).tvMsgCount.setBackgroundResource(R.mipmap.yellow);
                ((BedInfoViewholer) viewHolder).tvMsgCount.setText(date.get(i).getShow_number());
                ((BedInfoViewholer) viewHolder).tvMsgCount.setVisibility(View.VISIBLE);
                break;
            case "":
                ((BedInfoViewholer) viewHolder).tvMsgCount.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public void setListener(BedInfoViewholer.ItemSelectListener itemSelectListener){
        this.selectListener = itemSelectListener;
    }


    public static class BedInfoViewholer extends RecyclerView.ViewHolder {
        @BindView(R.id.bed_null)
        TextView bedNull;
        @BindView(R.id.tv_bed_name)
        TextView tvBedName;
        @BindView(R.id.tv_sex_age)
        TextView tvSexAge;
        @BindView(R.id.bed_bg)
        RelativeLayout bedBg;
        @BindView(R.id.tv_msg_count)
        TextView tvMsgCount;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.v_delete_icon)
        View vDelete;

        public BedInfoViewholer(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        public interface ItemSelectListener{
            void deleteClick(int position);
            void itemLongClick(int position);
            void itemClick(int position);
        }
    }

}
