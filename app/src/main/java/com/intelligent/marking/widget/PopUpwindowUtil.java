package com.intelligent.marking.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.intelligent.marking.R;
import com.intelligent.marking.adapter.SelectMoreAdapter;

import java.util.List;

public class PopUpwindowUtil {

    public static PopupWindow popupWindow;

    public static PopupWindow createPopUpWindow(int resID, Context context, List<String> value,SelectMoreAdapter.itemClickListener listener){
        View contentView = LayoutInflater.from(context).inflate(resID,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x306);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y152);
        popupWindow = new PopupWindow(width,ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = contentView.findViewById(R.id.rl_select_more);
        SelectMoreAdapter adapter = new SelectMoreAdapter(context,value);
        adapter.setItemClickListener(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        popupWindow.setContentView(contentView);
        return popupWindow;
    }

}
