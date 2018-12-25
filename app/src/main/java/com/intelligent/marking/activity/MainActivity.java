package com.intelligent.marking.activity;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.RecyPagerAdapter;
import com.intelligent.marking.application.MarkingApplication;
import com.intelligent.marking.common.view.PageIndicatorView;
import com.intelligent.marking.eventbus.MainActivityEvent;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.net.model.TreatInfoModel;
import com.intelligent.marking.widget.HorizontalPageLayoutManager;
import com.intelligent.marking.widget.PagingItemDecoration;
import com.intelligent.marking.widget.PagingScrollHelper;
import com.intelligent.marking.widget.PopUpwindowUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements PagingScrollHelper.onPageChangeListener, RecyPagerAdapter.BedInfoViewholer.ItemSelectListener {


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
    RelativeLayout vAddBed;

    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    RecyPagerAdapter adapter;

    PopupWindow popupWindow;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.ll_delete_one)
    LinearLayout llDeleteOne;
    @BindView(R.id.ll_one_key_delete)
    LinearLayout llOneKeyDelete;
    @BindView(R.id.iv_home_icon)
    ImageView ivHomeIcon;
    @BindView(R.id.rl_out_hospital)
    RelativeLayout rlOutHospital;

    int leftdeletepos = -1;
    int dragPosition = -1;
    List<BedInfoModel> bedInfoModelList;
    int currentpage = -1;
    private ScanBroadcastReceiver scanBroadcastReceiver;
    private String scanmsg;
    int deletePos = -1;
    int bedmainid = -1;
    TreatInfoModel addbacktreatInfoModel;

    class ScanBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            scanmsg = intent.getExtras().getString("code");
            System.out.println("scan:" + scanmsg);
            Intent intents = new Intent(MainActivity.this,ScanPatientInfoActivity.class);
            intents.putExtra("patientinfo",scanmsg);
            startActivityForResult(intents,1);
//            tv.setText(text1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                String no = data.getStringExtra("bedno");
                System.out.println("bedno:"+no);
                for(BedInfoModel bedInfoModel : bedInfoModelList){
                    if(bedInfoModel.getBed_name().equals(no)){
//                        bedInfoModel.setBed_name();
                        bedInfoModel.setIs_empty_bed(0);
                        bedInfoModel.setName("扫描");
                        bedInfoModel.setAge(20);
                        bedInfoModel.setCourse_id(1);
                        bedInfoModel.setBed_name("测试");
                        bedInfoModel.setBed_name("bad");
                    }
                }
                adapter.notifyDataSetChanged();

                break;
        }


    }

    @OnClick(R.id.ll_left_container)
    public void leftClick(View view) {
        //TODO 左侧管理
        startActivity(new Intent(this, HospoitalManagerAvtivity.class));
    }

    @OnClick(R.id.ll_right_container)
    public void rightClick(View view) {
        //TODO 右侧扫描
//        PopupWindow popupWindow = PopUpwindowUtil.createDuctToUserDialog(this);
//        WindowManager.LayoutParams lp=MainActivity.this.getWindow().getAttributes();
//        lp.alpha=0.4f;
//        MainActivity.this.getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(cusomSwipeView,Gravity.CENTER,0,0);
        MarkingApplication.openScan();
    }

    @OnClick(R.id.ll_add_temp_bed)
    public void addBed(View view) {
        PopupWindow popupWindow = PopUpwindowUtil.createPopUpWindowaddTemporaryBed(this, new PopUpwindowUtil.dialogClickContentListener() {
            @Override
            public void confirm(String prefix, String bed_no) {
                value.clear();
                value.put("subarea_id", subareaNameid);//
                value.put("prefix", prefix);
                value.put("bed_number", Integer.parseInt(bed_no));
                HttpPost(AppConst.ADDBED, value, 3);
            }

            @Override
            public void cancle() {
                PopUpwindowUtil.popupWindow.dismiss();
            }
        });
        WindowManager.LayoutParams lp = MainActivity.this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        MainActivity.this.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(cusomSwipeView, Gravity.CENTER, 0, 0);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
        if(addbacktreatInfoModel!=null){
//            TreatInfoModel treatInfoModel = (TreatInfoModel) getIntent().getSerializableExtra("addduct");
//
//            System.out.println(treatInfoModel.getDuct_name());
            PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().add(addbacktreatInfoModel);
            PopUpwindowUtil.getDuctToUserDialogAdapter().notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MainActivityEvent messageEvent) {
        addbacktreatInfoModel = messageEvent.getMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvHeaderTitle.setText("床位");
        ImageView rightView = new ImageView(this);
        rightView.setImageResource(R.mipmap.scan_white);
        llRightContainer.addView(rightView);
        llLeftContainer.addView(LayoutInflater.from(this).inflate(R.layout.bed_header_left_layout, null));
        getDate();
        scanBroadcastReceiver = new ScanBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qs.scancode");
        this.registerReceiver(scanBroadcastReceiver, intentFilter);
    }


    private void getDate() {
        value.clear();
        value.put("subarea_id", subareaNameid);
        HttpPost(AppConst.GETBEDINFO, value, 1);
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag) {
            case 1:
//                response = "{\"status\":1,\"count\":2,\"data\":[{\"bed_sn\":\"975ca8804565c1a569450d61090b2743\",\"subarea_id\":1,\"prefix\":\"\",\"bed_no\":\"2\",\"level\":1,\"course_id\":0,\"bed_name\":\"2\",\"is_empty_bed\":1,\"name\":null,\"sex\":null,\"age\":null,\"is_leave\":null,\"leave_time\":null,\"info\":null,\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":1,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0},{\"bed_sn\":\"83f96b5b54cc99e151607ebe386b07a9\",\"subarea_id\":1,\"prefix\":\"a\",\"bed_no\":\"2\",\"level\":0,\"course_id\":2,\"bed_name\":\"a2\",\"is_empty_bed\":0,\"name\":\"王某\",\"sex\":1,\"age\":30,\"is_leave\":0,\"leave_time\":0,\"info\":\"湘雅二医院-骨一科\",\"show_color\":\"\",\"show_number\":0}],\"info\":\"获取成功\"}\n";
                type = new TypeToken<BaseModel<List<BedInfoModel>>>() {
                }.getType();
                BaseModel<List<BedInfoModel>> listBaseModel = new Gson().fromJson(response, type);
                bedInfoModelList = listBaseModel.getData();
                initRecyclerView(bedInfoModelList);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                BaseModel<List<String>> baseModels = new Gson().fromJson(response, type);
                if (baseModels.getStatus() == 1) {
                    showToast(baseModels.getInfo());
                    adapter.removeItem(dragPosition);
                    PopUpwindowUtil.popupWindow.dismiss();
                } else {
                    PopUpwindowUtil.popupWindow.dismiss();
                    showToast(baseModels.getInfo());
                }
                break;
            case 3:
                type = new TypeToken<BaseModel<List<BedInfoModel>>>() {
                }.getType();
                BaseModel<List<BedInfoModel>> baseModel = new Gson().fromJson(response, type);
                if (baseModel.getStatus() == 1) {
                    if(baseModel.getData().isEmpty()){
                        showToast("添加失败");
                    }
                    int old = bedInfoModelList.size()/20;
                    bedInfoModelList.addAll(baseModel.getData());
                    int added = bedInfoModelList.size()/20;
                    adapter.notifyDataSetChanged();
                    if(old<added){
                        indicator.initIndicator(added+1);
                        indicator.setSelectedPage(currentpage);
                    }
                    showToast("添加成功");
                } else {
                    showToast(baseModel.getInfo());
                }
                PopUpwindowUtil.popupWindow.dismiss();
                break;
            case 4:
                type = new TypeToken<BaseModel<List<TreatInfoModel>>>(){}.getType();
                BaseModel<List<TreatInfoModel>> baseModel1 = new Gson().fromJson(response,type);
                popupWindow = PopUpwindowUtil.createDuctToUserDialog(this,baseModel1.getData(), new PopUpwindowUtil.dialogClickPatient() {
                    @Override
                    public void closeClick() {
                        //关闭
                        popupWindow.dismiss();
                    }

                    @Override
                    public void setToPatient() {
                        //置入
//                TimerPicer.showTimePic(MainActivity.this,cusomSwipeView);
                        Intent intoBedintent = new Intent(MainActivity.this, SelectDuctActivity.class);
                        intoBedintent.putExtra("bedmainid",bedmainid);
                        startActivity(intoBedintent);

                    }

                    @Override
                    public void editPatient() {
                        //修改


                    }

                    @Override
                    public void editDuct(int pos) {
                        //修改
                        deletePos = pos;
                        value.clear();
                        value.put("treat_id",baseModel1.getData().get(pos).getTreat_id());
                        value.put("subarea_id",subareaNameid);
                        value.put("course_id",baseModel1.getData().get(pos).getCourse_id());
                        HttpPost(AppConst.REMOVEDUCT,value,202);
                    }

                    @Override
                    public void deleteDuct(int pos) {
                        //删除
                        deletePos = pos;
                        value.clear();
                        value.put("treat_id",baseModel1.getData().get(pos).getTreat_id());
                        value.put("subarea_id",subareaNameid);
                        value.put("course_id",baseModel1.getData().get(pos).getCourse_id());
                        HttpPost(AppConst.REMOVEDUCT,value,201);
                    }

                    @Override
                    public void clickitem(int pos) {
                        //item点击

                    }
                });
                WindowManager.LayoutParams lp = MainActivity.this.getWindow().getAttributes();
                lp.alpha = 0.4f;
                MainActivity.this.getWindow().setAttributes(lp);
                popupWindow.showAtLocation(cusomSwipeView, Gravity.CENTER, 0, 0);
                break;
            case 201:
                type = new TypeToken<BaseModel<List<Object>>>(){}.getType();
                BaseModel<List<Object>> deleteBack = new Gson().fromJson(response,type);
                showToast(deleteBack.getInfo());
//                PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().remove(deletePos);
//                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyDataSetChanged();
                PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().remove(deletePos);
                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyItemRangeRemoved(deletePos,1);
                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyItemRangeChanged(deletePos+1,PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().size());

                break;
            case 202:
                type = new TypeToken<BaseModel<List<Object>>>(){}.getType();
                BaseModel<List<Object>> cangeBack = new Gson().fromJson(response,type);
//                showToast(cangeBack.getInfo());
//                PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().remove(deletePos);
//                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyDataSetChanged();
                PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().remove(deletePos);
                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyItemRangeRemoved(deletePos,1);
                PopUpwindowUtil.getDuctToUserDialogAdapter().notifyItemRangeChanged(deletePos+1,PopUpwindowUtil.getDuctToUserDialogAdapter().getDataList().size());
                Intent intoBedintent = new Intent(MainActivity.this, SelectDuctActivity.class);
                intoBedintent.putExtra("bedmainid",bedmainid);
                startActivity(intoBedintent);
                break;
            case 301:
                type = new TypeToken<BaseModel<List<Object>>>(){}.getType();
                BaseModel<List<Object>> deleteAllBack = new Gson().fromJson(response,type);
                showToast(deleteAllBack.getInfo());
                int count = bedInfoModelList.size()/20;
                adapter.removeAllTempBed(currentpage);
                int after = bedInfoModelList.size()/20;
                System.out.println("count:"+count+",after:"+after);
                if(count>after){
                    indicator.initIndicator(after+1);
                    indicator.setSelectedPage(currentpage);
                }
                break;
            case 22:
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                BaseModel<List<String>> baseModels2 = new Gson().fromJson(response, type);
                if (baseModels2.getStatus() == 1) {
                    showToast(baseModels2.getInfo());
                    adapter.removeItem(leftdeletepos);
                    PopUpwindowUtil.popupWindow.dismiss();
                } else {
                    PopUpwindowUtil.popupWindow.dismiss();
                    showToast(baseModels2.getInfo());
                }
                break;

        }
    }

    @OnClick({R.id.ll_delete_one, R.id.ll_one_key_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_delete_one:
                if (adapter.getDelete()) {
                    adapter.setDeleteVisi(false);
                } else {
                    adapter.setDeleteVisi(true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.ll_one_key_delete:
                //TODO 一键删除 删除当前页面内的临时床位
                List<String> deleteSn = new ArrayList<>();
                for(int j = currentpage*20;j<(((currentpage+1)*20 > bedInfoModelList.size())? bedInfoModelList.size():(currentpage+1)*20);j++){
                    if(bedInfoModelList.get(j).getLevel()==0){
                        deleteSn.add(bedInfoModelList.get(j).getBed_sn());
                    }
                }
                value.clear();
                value.put("subarea_id",subareaNameid);
                value.put("bed_sn_list",deleteSn);
                System.out.println(new Gson().toJson(value));
                HttpPost(AppConst.REMOVEALLBED,value,301);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    //是否已经长按
    boolean islongclick = false;

    private void initRecyclerView(List<BedInfoModel> bedInfoModelList) {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        //构造HorizontalPageLayoutManager,传入行数和列数
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(5, 4);
        //这是我自定义的分页分割线，样式是每一页的四周没有分割线。大家喜欢可以拿去用
        PagingItemDecoration pagingItemDecoration = new PagingItemDecoration(this, horizontalPageLayoutManager);

//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new RecyPagerAdapter(MainActivity.this, bedInfoModelList);
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
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        pagingScrollHelper.setUpRecycleView(cusomSwipeView);
        pagingScrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                indicator.setSelectedPage(index);
                currentpage = index;
                for (int i = index * 20; i < (index + 1) * 20; i++) {
                    if (i >= bedInfoModelList.size()) {
                        return;
                    }
                    if (bedInfoModelList.get(i).getLevel() == 0) {
                        llOneKeyDelete.setVisibility(View.VISIBLE);
                        llDeleteOne.setVisibility(View.VISIBLE);
                    } else {
                        llOneKeyDelete.setVisibility(View.GONE);
                        llDeleteOne.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onPageChange(int index) {
        System.out.println("onPageChange");
        indicator.setSelectedPage(index);
    }

    /**
     * X点击后的回调
     * @param position
     */
    @Override
    public void deleteClick(int position) {
        leftdeletepos = position;
        System.out.println("deleteClick:" + position);
        PopupWindow dialog = PopUpwindowUtil.createPopUpWindowDialogStyle(this, "要删除\""+bedInfoModelList.get(position).getBed_no() + "号床位\"吗？",
                "姓名：" + bedInfoModelList.get(position).getBed_name() + ",性别：" + MainActivity.this.getResources().getStringArray(R.array.sex)[bedInfoModelList.get(position).getSex()]+",年龄:"+bedInfoModelList.get(position).getAge()+"岁"
                , "删除", "取消", new PopUpwindowUtil.dialogClickListener() {
                    @Override
                    public void confirm() {
                        value.put("subarea_id", subareaNameid);
                        value.put("bed_sn", bedInfoModelList.get(position).getBed_sn());
                        System.out.println(new Gson().toJson(value));
                        HttpPost(AppConst.REMOVEBED, value, 22);
                    }

                    @Override
                    public void cancle() {
                        PopUpwindowUtil.popupWindow.dismiss();

                    }
                });
        WindowManager.LayoutParams lp = MainActivity.this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        MainActivity.this.getWindow().setAttributes(lp);
        dialog.showAtLocation(cusomSwipeView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void itemLongClick(int position) {
        dragPosition = position;
        if (islongclick = true) {
            adapter.setOutHospital(true);
            vAddBed.setVisibility(View.GONE);
            rlOutHospital.setVisibility(View.VISIBLE);
            rlOutHospital.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    System.out.println("onDrag" + event.getAction());
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_ENTERED:
                            PopUpwindowUtil.createPopUpWindowDialogStyle(MainActivity.this,
                                    bedInfoModelList.get(position).getBed_no() + "号床病人已出院？",
                                    "姓名：" + bedInfoModelList.get(position).getBed_name() + ",性别：" + MainActivity.this.getResources().getStringArray(R.array.sex)[bedInfoModelList.get(position).getSex()]+",年龄:"+bedInfoModelList.get(position).getAge()+"岁"
                                    , "出院", "取消", new PopUpwindowUtil.dialogClickListener() {
                                        @Override
                                        public void confirm() {
                                            value.clear();
                                            value.put("subarea_id", subareaNameid);
                                            value.put("bed_sn", bedInfoModelList.get(position).getBed_sn());
                                            HttpPost(AppConst.REMOVEBED, value, 2);
                                        }

                                        @Override
                                        public void cancle() {
                                            PopUpwindowUtil.popupWindow.dismiss();
                                        }
                                    });
                            WindowManager.LayoutParams lp = MainActivity.this.getWindow().getAttributes();
                            lp.alpha = 0.4f;
                            MainActivity.this.getWindow().setAttributes(lp);
                            PopUpwindowUtil.popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                            break;
                    }
                    return true;
                }
            });

        }
        islongclick = true;

        System.out.println("itemLongClick:" + position);
    }

    @Override
    public void itemClick(int position) {
        value.clear();
        value.put("subarea_id",subareaNameid);
        value.put("course_id",bedInfoModelList.get(position).getCourse_id());
        bedmainid = bedInfoModelList.get(position).getCourse_id();
        HttpPost(AppConst.TREATINFO,value,4);
    }

    @Override
    public void hastempbed() {
        llOneKeyDelete.setVisibility(View.VISIBLE);
        llDeleteOne.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return;
        }
        if (PopUpwindowUtil.popupWindow != null && PopUpwindowUtil.popupWindow.isShowing()) {
            PopUpwindowUtil.popupWindow.dismiss();
            return;
        }
        if (islongclick) {
            vAddBed.setVisibility(View.VISIBLE);
            rlOutHospital.setVisibility(View.GONE);
            islongclick = false;
            adapter.setOutHospital(false);
            return;
        }
        super.onBackPressed();
    }

    @OnClick(R.id.rl_out_hospital)
    public void setRlOutHospital(View view) {
//        adapter.removeItem(29);
    }
}