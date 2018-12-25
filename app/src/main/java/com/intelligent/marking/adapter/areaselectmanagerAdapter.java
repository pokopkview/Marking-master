package com.intelligent.marking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.AreaModel;

import java.util.List;

public class areaselectmanagerAdapter extends BaseAdapter {

    Context mContext;
    List<AreaModel> data;
    String temp;

    public areaselectmanagerAdapter(Context context, List<AreaModel> data,String defAre){
        mContext = context;
        this.data = data;
        temp = defAre;
    }

    public void setSelectItem(String st){
        temp = st;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.area_select_manager_item_layout,parent,false);
        TextView textView = view.findViewById(R.id.tv_area_name);
        textView.setText(data.get(position).getArea_name());
        if(!temp.isEmpty()&&data.get(position).getArea_name().equals(temp)){
            textView.setTextColor(mContext.getResources().getColor(R.color.blue));
        }
        return view;
    }
}
