package com.intelligent.marking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.SubAreModel;

import java.util.List;

public class SimpleSubAreaAdapter extends BaseAdapter {

    Context mContext;
    List<SubAreModel> data;

    public SimpleSubAreaAdapter(Context context, List<SubAreModel> value){
        mContext = context;
        data = value;
    }

    public void setData(List<SubAreModel> value){
        data.clear();
        data.addAll(value);
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
        return data.get(position).getSubarea_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subarea_item_laout,parent,false);
        ((TextView)view.findViewById(R.id.tv_subarea_name)).setText(data.get(position).getSubarea_name());
        return view;
    }
}
