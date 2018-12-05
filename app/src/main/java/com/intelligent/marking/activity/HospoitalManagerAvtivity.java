package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.SimpleListTextAdapter;
import com.intelligent.marking.adapter.SimpleSubAreaAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.SubAreModel;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HospoitalManagerAvtivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.tv_edit_subarea)
    TextView tvEditSubarea;
    @BindView(R.id.tv_edit_employee)
    TextView tvEditEmployee;
    @BindView(R.id.lv_show_detail)
    ListView lvShowDetail;
    @BindView(R.id.ll_right_detail_container)
    LinearLayout llRightDetailContainer;
    @BindView(R.id.left_menu_lv)
    ListView leftMenuLv;

    private int tempSelect = 0;
    private int tempSelectid = -1;
    private Map<Integer,List<SubAreModel>> datas = new HashMap<>();
    SimpleListTextAdapter simpleListTextAdapter;
    SimpleSubAreaAdapter simpleSubAreaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospoital_manager_avtivity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText(hospitalName);
        Map<String,Object> value = new HashMap<>();
        value.put("area_id",1);
        HttpPost(AppConst.GETDEPARTMENT,value,1);
    }


    /**
     * 片区管理
     * @param view
     */
    @OnClick(R.id.tv_edit_subarea)
    public void editSubArea(View view){
        Intent intent = new Intent(this,SubAreaManagerActivity.class);
        System.out.println(datas.get(tempSelectid));
        intent.putExtra("subarealist",(Serializable) datas.get(tempSelectid));
        startActivity(intent);
    }

    @OnClick(R.id.tv_edit_employee)
    public void editSubareaEmployee(View view){

    }


    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<List<DepartModel>>>(){}.getType();
                BaseModel<List<DepartModel>> model = new Gson().fromJson(response,type);
                simpleListTextAdapter = new SimpleListTextAdapter(HospoitalManagerAvtivity.this,model.getData());
                leftMenuLv.setAdapter(simpleListTextAdapter);
                leftMenuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        simpleListTextAdapter.setSelectItem(position,tempSelect);
                        tempSelect = position;
                        tempSelectid = model.getData().get(tempSelect).getDepartment_id();
                        System.out.println("tempSelectid:"+tempSelectid);
                        if(datas.containsKey(tempSelectid)){
                            List<SubAreModel>temp = datas.get(model.getData().get(position).getDepartment_id());
                            simpleSubAreaAdapter.setData(temp);
                        }else{
                            Map<String,Object> value = new HashMap<>();
                            value.put("department_id",model.getData().get(position).getDepartment_id());
                            HttpPost(AppConst.GETSUBAREA,value,2);
                        }
                    }
                });
                Map<String,Object> value = new HashMap<>();
                value.put("department_id",model.getData().get(0).getDepartment_id());
                HttpPost(AppConst.GETSUBAREA,value,2);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<SubAreModel>>>(){}.getType();
                BaseModel<List<SubAreModel>> subareamodel = new Gson().fromJson(response,type);
                List<SubAreModel> subAreModelList = subareamodel.getData();
                System.out.println(!datas.containsKey(tempSelectid));
                if(!datas.containsKey(tempSelectid)) {
                    datas.put(tempSelectid, subAreModelList);
                }
                if(simpleSubAreaAdapter==null) {
                    simpleSubAreaAdapter = new SimpleSubAreaAdapter(HospoitalManagerAvtivity.this, subAreModelList);
                    lvShowDetail.setAdapter(simpleSubAreaAdapter);
                }else{
                    simpleSubAreaAdapter.setData(subAreModelList);
                }
                lvShowDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //TODO
                        Intent intent = new Intent(HospoitalManagerAvtivity.this,BedInfoActivity.class);
                        intent.putExtra("subarea_id",subAreModelList.get(position).getSubarea_id());
                        startActivity(intent);
                    }
                });
                break;

        }
    }

    private void initChildView(){
        //TODO

    }
}
