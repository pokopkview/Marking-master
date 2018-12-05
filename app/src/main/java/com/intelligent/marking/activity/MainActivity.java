package com.intelligent.marking.activity;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.RecyPagerAdapter;
import com.intelligent.marking.common.view.PageIndicatorView;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.set.TimerPicer;
import com.intelligent.marking.widget.HorizontalPageLayoutManager;
import com.intelligent.marking.widget.PagingItemDecoration;
import com.intelligent.marking.widget.PagingScrollHelper;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements PagingScrollHelper.onPageChangeListener,RecyPagerAdapter.BedInfoViewholer.ItemSelectListener {


    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.cusom_swipe_view)
    RecyclerView cusomSwipeView;
    @BindView(R.id.indicator)
    PageIndicatorView indicator;
    @BindView(R.id.v_add_bed)
    View vAddBed;

    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    RecyPagerAdapter adapter;


    @OnClick(R.id.ll_left_container)
    public void leftClick(View view){
        //TODO 左侧管理
        startActivity(new Intent(this,HospoitalManagerAvtivity.class));
    }

    @OnClick(R.id.ll_right_container)
    public void rightClick(View view){
        //TODO 右侧扫描
//        PopupWindow popupWindow = PopUpwindowUtil.createDuctToUserDialog(this);
//        WindowManager.LayoutParams lp=MainActivity.this.getWindow().getAttributes();
//        lp.alpha=0.4f;
//        MainActivity.this.getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(cusomSwipeView,Gravity.CENTER,0,0);
    }

    @OnClick(R.id.v_add_bed)
    public void addBed(View view){
        if(adapter.getDelete()) {
            adapter.setDeleteVisi(false);
        }else{
            adapter.setDeleteVisi(true);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("床位");
        ImageView rightView = new ImageView(this);
        rightView.setImageResource(R.mipmap.scan_white);
        llRightContainer.addView(rightView);
        llLeftContainer.addView(LayoutInflater.from(this).inflate(R.layout.bed_header_left_layout,null));
        getDate();
    }

    private void getDate() {
        Map<String,Object> value = new HashMap<>();
        value.put("subarea_id",1);
        HttpPost(AppConst.GETBEDINFO,value,1);

    }

    @Override
    public void getCallBack(String response, int flag) {
        switch (flag){
            case 1:
                response = "{\"status\":1,\"count\":2,\"data\":[{\"bed_sn\":\"975ca8804565c1a569450d61090b2743\",\"subarea_id\":1,\"prefix\":\"\",\"bed_no\":\"2\",\"level\":1,\"course_id\":0,\"bed_name\":\"2\",\"is_empty_bed\":1,\"name\":null,\"sex\":null,\"age\":null,\"is_leave\":null,\"leave_time\":null,\"info\":null,\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0}],\"info\":\"获取成功\"}";
                Type type = new TypeToken<BaseModel<List<BedInfoModel>>>(){}.getType();
                BaseModel<List<BedInfoModel>> listBaseModel = new Gson().fromJson(response,type);
                List<BedInfoModel> bedInfoModelList = listBaseModel.getData();
                initRecyclerView(bedInfoModelList);
                break;
        }
    }

    private void initRecyclerView(List<BedInfoModel> bedInfoModelList){
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        //构造HorizontalPageLayoutManager,传入行数和列数
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(5,4);
        //这是我自定义的分页分割线，样式是每一页的四周没有分割线。大家喜欢可以拿去用
        PagingItemDecoration pagingItemDecoration = new PagingItemDecoration(this, horizontalPageLayoutManager);

//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter =  new RecyPagerAdapter(MainActivity.this,bedInfoModelList);
        cusomSwipeView.setLayoutManager(horizontalPageLayoutManager);
//        cusomSwipeView.addItemDecoration(pagingItemDecoration);
        cusomSwipeView.setAdapter(adapter);
        adapter.setListener(this);
        scrollHelper.setUpRecycleView(cusomSwipeView);
        scrollHelper.setOnPageChangeListener(this);
        cusomSwipeView.post(new Runnable() {
            @Override
            public void run() {
                indicator.initIndicator(scrollHelper.getPageCount());
            }
        });
    }

    @Override
    public void onPageChange(int index) {
        indicator.setSelectedPage(index);
    }



    @Override
    public void deleteClick(int position) {
        System.out.println("deleteClick:"+position);
        PopupWindow dialog = PopUpwindowUtil.createPopUpWindowDialogStyle(this, "test出院", "name:name,sex:meal,age:12","出院","取消", new PopUpwindowUtil.dialogClickListener() {
            @Override
            public void confirm() {
                PopUpwindowUtil.popupWindow.dismiss();

            }

            @Override
            public void cancle() {
                PopUpwindowUtil.popupWindow.dismiss();

            }
        });
        WindowManager.LayoutParams lp=MainActivity.this.getWindow().getAttributes();
        lp.alpha=0.4f;
        MainActivity.this.getWindow().setAttributes(lp);
        dialog.showAtLocation(cusomSwipeView,Gravity.CENTER,0,0);
    }

    @Override
    public void itemLongClick(int position) {
        System.out.println("itemLongClick:"+position);
        PopupWindow popupWindow = PopUpwindowUtil.createPopUpWindowaddTemporaryBed(this, new PopUpwindowUtil.dialogClickContentListener() {
            @Override
            public void confirm(String prefix, String bed_no) {
                PopUpwindowUtil.popupWindow.dismiss();
            }

            @Override
            public void cancle() {
                PopUpwindowUtil.popupWindow.dismiss();
            }
        });
        WindowManager.LayoutParams lp=MainActivity.this.getWindow().getAttributes();
        lp.alpha=0.4f;
        MainActivity.this.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(cusomSwipeView,Gravity.CENTER,0,0);
    }

    @Override
    public void itemClick(int position) {
        System.out.println("itemClick"+position);
//        PopupWindow popupWindow = PopUpwindowUtil.createPopUpWindowDuctInfo(this, null, null, new PopUpwindowUtil.dialogClickDuctListener() {
//            @Override
//            public void firstClick() {
//
//            }
//
//            @Override
//            public void secendClick() {
//
//            }
//
//            @Override
//            public void closeClick() {
//                PopUpwindowUtil.popupWindow.dismiss();
//            }
//        });

        PopupWindow popupWindow = PopUpwindowUtil.createDuctToUserDialog(this, new PopUpwindowUtil.dialogClickPatient() {
            @Override
            public void closeClick() {
                //关闭
            }

            @Override
            public void setToPatient() {
                //置入
                TimerPicer.showTimePic(MainActivity.this,cusomSwipeView);


            }

            @Override
            public void editPatient() {
                //修改
            }
        });
        WindowManager.LayoutParams lp=MainActivity.this.getWindow().getAttributes();
        lp.alpha=0.4f;
        MainActivity.this.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(cusomSwipeView,Gravity.CENTER,0,0);
    }
}