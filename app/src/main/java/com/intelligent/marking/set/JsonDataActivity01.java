package com.intelligent.marking.set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.Utils.UtilsChange;
import com.intelligent.marking.activity.LoginActivity;
import com.intelligent.marking.set.bean.JsonBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * 解析省市区数据示例
 */
public class JsonDataActivity01{
    public ArrayList<JsonBean> options1Items = new ArrayList<>();
    public ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
//    public ArrayList<Integer> options1Items1 = new ArrayList<>();
//    public ArrayList<ArrayList<Integer>> options2Items1 = new ArrayList<>();
//    public ArrayList<ArrayList<ArrayList<Integer>>> options3Items1 = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private Context context;
    private TextView  login_tv_position;
    private selectPosition listenner;



    public JsonDataActivity01 getPosition(Context context, TextView login_tv_position,selectPosition listenner) {
        this.context = context;
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        this.login_tv_position = login_tv_position;
        this.listenner = listenner;
        return this;
    }

//    if (mHandler != null) {
//        mHandler.removeCallbacksAndMessages(null);
//    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

//                        Toast.makeText(context, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS://数据解析成功
                        showPickerView();
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };







    private void showPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String Position = options1Items.get(options1).getPickerViewText() +"-"+
                        options2Items.get(options1).get(options2) +"-"+
                        options3Items.get(options1).get(options2).get(options3);

                        Log.e("-------tx", Position);
                listenner.getLocation(options1Items.get(options1).getPickerViewText(),
                        options2Items.get(options1).get(options2),
                        options3Items.get(options1).get(options2).get(options3));
                listenner.getLocationID(options1,options2,options3);

                login_tv_position.setText(Position);
                Toast.makeText(context, "1:"+options1Items.get(options1).getPickerViewText()+",2:"+options2Items.get(options1).get(options2)
                        +",3:"+options3Items.get(options1).get(options2).get(options3), Toast.LENGTH_SHORT).show();
            }
        })
                .setLayoutRes(R.layout.province_select_layout, null)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.TRANSPARENT)
                .setTitleText("请选择省市区")
                .setTitleSize(20)
                .setTitleBgColor(Color.WHITE)
                .setDividerColor(Color.rgb(204,204,204))
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setSubCalSize(18)
                .setContentTextSize(20)
                .setSelectOptions(PreferencesUtils.getInt(context,PreferencesUtils.PROVINCE,1)
                        ,PreferencesUtils.getInt(context,PreferencesUtils.CITY,1)
                        ,PreferencesUtils.getInt(context,PreferencesUtils.ARE,1))
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        System.out.println(pvOptions.getDialog());
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCity().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getArea() == null
                        || jsonBean.get(i).getCity().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for(int j =0;j<jsonBean.get(i).getCity().get(c).getArea().size();j++) {
                        City_AreaList.add(jsonBean.get(i).getCity().get(c).getArea().get(j).getName());
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    public interface selectPosition{
        void getLocation(String pronvince,String city,String area);
        void getLocationID(int pid,int cid,int aid);
    }

}
