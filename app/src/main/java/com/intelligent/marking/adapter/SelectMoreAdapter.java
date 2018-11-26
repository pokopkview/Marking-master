package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelligent.marking.R;

import java.util.List;

public class SelectMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> value;
    private LayoutInflater layoutInflater;
    private itemClickListener listener;

    public SelectMoreAdapter(Context context,List<String> value){
        this.value = value;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setItemClickListener(itemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("viewType:"+viewType);
        switch (viewType){
            case 1:
                return new simpleItemViewHolder(layoutInflater.inflate(R.layout.item_layout,parent,false));
            case 0:
                return new headerItem(layoutInflater.inflate(R.layout.select_item_header_layout,parent,false));
            case 2:
                return new footItem(layoutInflater.inflate(R.layout.select_item_foot_layout,parent,false));
            case 3:
                return new headerItem(layoutInflater.inflate(R.layout.single_item_layout,parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof headerItem){
            ((headerItem) holder).tv_item.setText(value.get(position));
            ((headerItem) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemclick(position,view);
                }
            });
        }else if(holder instanceof simpleItemViewHolder){
            ((simpleItemViewHolder) holder).tv_item.setText(value.get(position));
            ((simpleItemViewHolder) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemclick(position,view);
                }
            });
        }else if(holder instanceof footItem){
            ((footItem) holder).tv_item.setText(value.get(position));
            ((footItem) holder).rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.footclick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return value.size();
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("position:"+position+",count:"+getItemCount());
        if(position == 0){
            if(getItemCount() == 1){
                return 3;
            }
            return 0;
        }else if(position == getItemCount()-1){
            return 2;
        }else{
            return 1;
        }
    }

    public class simpleItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_item;
        private View rootView;
        public simpleItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tv_item = itemView.findViewById(R.id.tv_item);
        }
    }

    public class headerItem extends RecyclerView.ViewHolder{
        private TextView tv_item;
        private View rootView;
        private View v_top;
        public headerItem(View itemView) {
            super(itemView);
            rootView = itemView;
            tv_item = itemView.findViewById(R.id.tv_item);
            v_top = itemView.findViewById(R.id.v_top);
        }
    }

    public class footItem extends RecyclerView.ViewHolder{
        private TextView tv_item;
        private View rootView;
        public footItem(View itemView) {
            super(itemView);
            rootView = itemView;
            tv_item = itemView.findViewById(R.id.tv_item);
        }
    }

    public interface itemClickListener{
        void itemclick(int pos,View view);
        void footclick();
    }
}
