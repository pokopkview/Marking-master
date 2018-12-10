package com.intelligent.marking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intelligent.marking.R;

import java.util.List;

public class RegisterPagePopAdapter extends RecyclerView.Adapter {


    Context mContext;
    List<String> data;
    ItemClickListener listener;


    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }

    public RegisterPagePopAdapter(Context context, List<String> value){
        mContext = context;
        data  = value;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.register_pop_layout,viewGroup,false);
        return new SelectMoreAdapter.simpleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SelectMoreAdapter.simpleItemViewHolder) viewHolder).tv_item.setText(data.get(i));
        ((SelectMoreAdapter.simpleItemViewHolder) viewHolder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemclick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ItemClickListener{
        void itemclick(int position);
    }


}
