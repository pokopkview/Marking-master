package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.net.model.BedStatusModel;

import java.util.List;

public class BedInfoPageAdapter extends PagerAdapter {


    Context mContext;
    List<BedStatusModel> dispose;
    List<BedStatusModel> onway;
    List<BedStatusModel> compelete;


    public BedInfoPageAdapter(Context context, List<BedStatusModel> dispose,List<BedStatusModel> onway,List<BedStatusModel> compelete){
        mContext = context;
        this.dispose = dispose;
        this.onway = onway;
        this.compelete = compelete;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "需处理";
            case 1:
                return "进行中";
            case 2:
                return "已处理";
        }
        return "";
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bed_status_page_layout,null);
        RecyclerView recyclerView = view.findViewById(R.id.rl_status);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BedDuctInfoAdapter adapter = null;
        switch (position){
            case 0:
                adapter = new BedDuctInfoAdapter(mContext, dispose);
                break;
            case 1:
                adapter = new BedDuctInfoAdapter(mContext, onway);
                break;
            case 2:
                adapter = new BedDuctInfoAdapter(mContext, compelete);
                break;
        }
        recyclerView.setAdapter(adapter);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

}
