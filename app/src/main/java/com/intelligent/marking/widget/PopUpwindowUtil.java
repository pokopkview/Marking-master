package com.intelligent.marking.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.activity.MainActivity;
import com.intelligent.marking.adapter.DuctToUserAdapter;
import com.intelligent.marking.adapter.RegisterPagePopAdapter;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.adapter.SimpleDuctInfoBeanAdapter;
import com.intelligent.marking.common.utils.StringUtil;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.net.model.DuctListInfo;
import com.intelligent.marking.net.model.TreatInfoModel;

import java.util.ArrayList;
import java.util.List;

public class PopUpwindowUtil {

    public static PopupWindow popupWindow;

    public static DuctToUserAdapter adapter;
    /**
     * 登陆页
     * @param resID
     * @param context
     * @param value
     * @param listener
     * @return
     */
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

    /**
     * 注册页
     * @param resID
     * @param context
     * @param value
     * @param listener
     * @return
     */
    public static PopupWindow createPopUpWindowReg(int resID, Context context, List<String> value,RegisterPagePopAdapter.ItemClickListener listener){
        View contentView = LayoutInflater.from(context).inflate(resID,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x306);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y152);
        popupWindow = new PopupWindow(width,ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = contentView.findViewById(R.id.rl_select_more);
        RegisterPagePopAdapter adapter = new RegisterPagePopAdapter(context,value);
        adapter.setListener(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        popupWindow.setContentView(contentView);
        return popupWindow;
    }

    /**
     * 删除dialog
     * @param context
     * @param Title
     * @param content
     * @param confirm
     * @param cancle
     * @param listener
     * @return
     */
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
                popupWindow.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.confirm();
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
        popupWindow.setContentView(contentView);
        return popupWindow;
    }


    /**
     * 添加床位
     * @param context
     * @param listener
     * @return
     */
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


    /**
     * 添加导管类型
     * @param context
     * @param title
     * @param content
     * @param listener
     * @return
     */
    public static PopupWindow createPopUpWindowDuctInfo(Context context, String title, String content, List<DuctListInfo.DuctCatBean> beanList, dialogClickDuctListener listener){
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
        ListView listView = popupWindow.getContentView().findViewById(R.id.lv_show_duct_type);
        SimpleDuctInfoBeanAdapter adapter = new SimpleDuctInfoBeanAdapter(context,beanList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.itemClick(position,(int)id);
            }
        });
//        TextView confirm = popupWindow.getContentView().findViewById(R.id.tv_first);
//        if(first!=null){
//            confirm.setText(first);
//        }
//        TextView cancle = popupWindow.getContentView().findViewById(R.id.tv_secend);
//        if(second!=null){
//            cancle.setText(second);
//        }
        View view = popupWindow.getContentView().findViewById(R.id.v_close);
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.firstClick();
//            }
//        });
//        cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.secendClick();
//            }
//        });
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

    public static DuctToUserAdapter getDuctToUserDialogAdapter(){
        return adapter;
    }





    /**
     * 床位详情设置
     * @param context
     * @param clickPatient
     * @return
     */
    public static PopupWindow createDuctToUserDialog(Context context, BedInfoModel bedInfoModel, List<TreatInfoModel> value, dialogClickPatient clickPatient){
        View contentView = LayoutInflater.from(context).inflate(R.layout.duct_to_user_dialog_layout,null,false);
        int width = context.getResources().getDimensionPixelSize(R.dimen.x340);
        int height = context.getResources().getDimensionPixelSize(R.dimen.y422);
        TextView tvbedno = contentView.findViewById(R.id.bed_info_name);
        tvbedno.setText(bedInfoModel.getBed_name()+"号床");
        TextView tvpatientinfo = contentView.findViewById(R.id.tv_name_sex_age);
        tvpatientinfo.setText(bedInfoModel.getName()+" "+context.getResources().getStringArray(R.array.sex)[bedInfoModel.getSex()]+" "+bedInfoModel.getAge()+"岁");
        popupWindow = new PopupWindow(width,height);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        RecyclerView recyclerView = popupWindow.getContentView().findViewById(R.id.rv_duct_view);

        popupWindow.getContentView().findViewById(R.id.iv_close_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPatient.closeClick();
            }
        });
        popupWindow.getContentView().findViewById(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPatient.editPatient();
            }
        });
        popupWindow.getContentView().findViewById(R.id.ll_bottom_ductin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPatient.setToPatient();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new DuctToUserAdapter(context, value, new DuctToUserAdapter.DuctItemClick() {
            @Override
            public void slideChange(int position) {
                clickPatient.editDuct(position);
            }

            @Override
            public void slideDelete(int position) {
                clickPatient.deleteDuct(position);
//                value.remove(position);
//                recyclerView.getAdapter().notifyItemRangeRemoved(position,1);
//                recyclerView.getAdapter().notifyItemRangeChanged(position,value.size());
            }

            @Override
            public void itemClick(int position) {
                clickPatient.clickitem(position);
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
        void itemClick(int pos,int id);
        void closeClick();
    }

    public interface dialogClickPatient{
        void closeClick();
        void setToPatient();
        void editPatient();

        void editDuct(int pos);
        void deleteDuct(int pos);
        void clickitem(int pos);
    }
}
