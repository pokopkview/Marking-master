package com.intelligent.marking.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.activity.MainActivity;
import com.intelligent.marking.adapter.DuctToUserAdapter;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.common.utils.StringUtil;

import java.util.ArrayList;
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

    public static PopupWindow createPopUpWindowDialogStyle(Context context,String Title,String content,String confirm,String cancle,dialogClickListener listener){
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_dialog_layout,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x260);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y120);
        popupWindow = new PopupWindow(width,height);
        TextView title = contentView.findViewById(R.id.tv_title);
        TextView tvContent = contentView.findViewById(R.id.tv_content);
        TextView tvConfirm = contentView.findViewById(R.id.tv_confrim);
        TextView tvCancle = contentView.findViewById(R.id.tv_cancle);
        title.setText(Title);
        tvContent.setText(content);
        tvConfirm.setText(confirm);
        tvCancle.setText(cancle);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.cancle();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.confirm();
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
                lp.alpha=1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
        popupWindow.setContentView(contentView);
        return popupWindow;
    }


    public static PopupWindow createPopUpWindowaddTemporaryBed(Context context,dialogClickContentListener listener){
        View contentView = LayoutInflater.from(context).inflate(R.layout.temporary_bed_layout,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x260);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y229);
        popupWindow = new PopupWindow(width,height);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        EditText prefix = popupWindow.getContentView().findViewById(R.id.tv_prefix);
        EditText bedNo = popupWindow.getContentView().findViewById(R.id.tv_bed_no);

        popupWindow.getContentView().findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.cancle();
            }
        });
        popupWindow.getContentView().findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefix.getText().toString().isEmpty() || bedNo.getText().toString().isEmpty()){
                    ToastUtil.getInstance(context).show("请完成输入！");
                    return;
                }
                listener.confirm(prefix.getText().toString(),
                        bedNo.getText().toString());
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
                lp.alpha=1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
        popupWindow.setFocusable(true);
        return popupWindow;
    }


    public static PopupWindow createPopUpWindowDuctInfo(Context context,String title,String content,dialogClickDuctListener listener){
        View contentView = LayoutInflater.from(context).inflate(R.layout.duct_add_dialog_layout,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x310);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y260);
        popupWindow = new PopupWindow(width,height);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        if(title!=null){
            ((TextView)popupWindow.getContentView().findViewById(R.id.tv_title)).setText(title);
        }
        if(content!=null){
            ((TextView)popupWindow.getContentView().findViewById(R.id.tv_content)).setText(content);
        }
        TextView confirm = popupWindow.getContentView().findViewById(R.id.tv_first);
        TextView cancle = popupWindow.getContentView().findViewById(R.id.tv_secend);
        View view = popupWindow.getContentView().findViewById(R.id.v_close);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.firstClick();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.secendClick();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.closeClick();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
                lp.alpha=1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
        return popupWindow;
    }

    public static PopupWindow createDuctToUserDialog(Context context){
        View contentView = LayoutInflater.from(context).inflate(R.layout.duct_to_user_dialog_layout,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x340);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y422);
        popupWindow = new PopupWindow(width,height);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        RecyclerView recyclerView = popupWindow.getContentView().findViewById(R.id.rv_duct_view);
        List<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("1");
        objects.add("1");
        objects.add("1");
        objects.add("1");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DuctToUserAdapter adapter = new DuctToUserAdapter(context, objects, new DuctToUserAdapter.DuctItemClick() {
            @Override
            public void slideChange(int position) {
            }

            @Override
            public void slideDelete(int position) {
                objects.remove(position);
                recyclerView.getAdapter().notifyItemRangeRemoved(position,1);
                recyclerView.getAdapter().notifyItemRangeChanged(position,objects.size());
            }

            @Override
            public void itemClick(int position) {

            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
                lp.alpha=1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
        return popupWindow;
    }







    public interface dialogClickContentListener{
        void confirm(String prefix,String bed_no);
        void cancle();
    }



    public interface dialogClickListener{
        void confirm();
        void cancle();
    }

    public interface dialogClickDuctListener{
        void firstClick();
        void secendClick();
        void closeClick();
    }
}
