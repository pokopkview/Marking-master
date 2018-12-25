package com.intelligent.marking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.DuctListInfo;

import java.util.List;

public class SimpleDuctInfoBeanAdapter extends BaseAdapter {

    Context mContext;
    List<DuctListInfo.DuctCatBean> data;

    public SimpleDuctInfoBeanAdapter(Context context, List<DuctListInfo.DuctCatBean> value){
        mContext = context;
        data = value;
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
        return data.get(position).getDuct_attr_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_duct_son_type_item_layout,parent,false);
        TextView ductName = view.findViewById(R.id.tv_first);
        ductName.setText(data.get(position).getDuct_cat_name());
        return view;
    }
}
