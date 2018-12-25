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

    List<DepartModel> value = new ArrayList<>();
    Context mContext;
    int selecposition;

    public SimpleListTextAdapter(Context context,List<DepartModel> value){
        this.mContext = context;
        this.value.addAll(value);
    }
    public SimpleListTextAdapter(Context context){
        this.mContext = context;
    }

    public void setData(List<DepartModel> value){
        this.value.addAll(value);
        notifyDataSetChanged();
    }

    public void setSelecposition(int selecposition){
        this.selecposition = selecposition;
        notifyDataSetChanged();
    }

    public List<DepartModel> getListDate(){
        return value;
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
        if(selecposition == position){
            departName.setTextColor(mContext.getResources().getColor(R.color.blue));
            tab.setVisibility(View.VISIBLE);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else{
            departName.setTextColor(mContext.getResources().getColor(R.color.black_black));
            tab.setVisibility(View.INVISIBLE);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_listview));
        }
        departName.setText(value.get(position).getDepartment_name());
        return view;
    }
}
