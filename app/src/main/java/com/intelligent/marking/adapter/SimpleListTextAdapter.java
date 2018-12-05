package com.intelligent.marking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.net.model.DepartModel;

import java.util.ArrayList;
import java.util.List;

public class SimpleListTextAdapter extends BaseAdapter {

    List<DepartModel> value ;
    Context mContext;
    List<Boolean> selectstatus = new ArrayList<>();

    public void setSelectItem(int position,int oldposition){
        selectstatus.set(oldposition,false);
        selectstatus.set(position,true);
        notifyDataSetChanged();
    }

    public SimpleListTextAdapter(Context context,List<DepartModel> value){
        this.mContext = context;
        this.value = value;
        selectstatus.add(true);
        for(int i = 1;i<value.size();i++){
            selectstatus.add(false);
        }
    }

    @Override
    public int getCount() {
        return value.size();
    }

    @Override
    public Object getItem(int position) {
        return value.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_list_text_layout,parent,false);
        TextView departName = view.findViewById(R.id.tv_depart_name);
        View tab = view.findViewById(R.id.left_tab_view);
        if(selectstatus.get(position)){
            departName.setTextColor(mContext.getResources().getColor(R.color.blue));
            tab.setVisibility(View.VISIBLE);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else{
            departName.setTextColor(mContext.getResources().getColor(R.color.black_black));
            tab.setVisibility(View.VISIBLE);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_listview));
        }
        departName.setText(value.get(position).getDepartment_name());
        return view;
    }
}
