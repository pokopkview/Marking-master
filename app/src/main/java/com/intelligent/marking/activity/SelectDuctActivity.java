package com.intelligent.marking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.DuctSelectAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DuctListInfo;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectDuctActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.rl_duct_select)
    RecyclerView rlDuctSelect;

    List<Object> data;

    DuctSelectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_duct);
        ButterKnife.bind(this);

        Map<String,Object> value = new HashMap<>();
        value.put("subarea_id",subareaNameid);
        HttpPost(AppConst.DUCTINDEX,value,1);

    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                response = "{\"status\":1,\"count\":2,\"data\":[{\"duct_id\":1,\"duct_name\":\"胃管\",\"duct_img\":\"https:\\/\\/data.zusux.com\\/upload\\/images\\/c0afa30689f7c8955b2cc6547cb02a5b.jpg\",\"intro\":\"\",\"number\":1,\"duct_cat\":[{\n" +
                        "                    \"duct_attr_id\":1,\n" +
                        "                    \"duct_cat_id\":1,\n" +
                        "                    \"duct_cat_name\":\"管\"\n" +
                        "                },{\n" +
                        "                    \"duct_attr_id\":2,\n" +
                        "                    \"duct_cat_id\":2,\n" +
                        "                    \"duct_cat_name\":\"膜\"\n" +
                        "                }],\"operate_place\":\"湘雅二医院-骨一科\"},{\"duct_id\":3,\"duct_name\":\"PICC\",\"duct_img\":\"https:\\/\\/data.zusux.com\\/upload\\/images\\/be5ae6362ed91798baa03f0dd3682586.jpg\",\"intro\":\"\",\"number\":0,\"duct_cat\":[{\n" +
                        "                    \"duct_attr_id\":1,\n" +
                        "                    \"duct_cat_id\":1,\n" +
                        "                    \"duct_cat_name\":\"管\"\n" +
                        "                },{\n" +
                        "                    \"duct_attr_id\":2,\n" +
                        "                    \"duct_cat_id\":2,\n" +
                        "                    \"duct_cat_name\":\"膜\"\n" +
                        "                }],\"operate_place\":\"湘雅二医院-骨一科\"}],\"info\":\"获取成功\"}";

                type = new TypeToken<BaseModel<List<DuctListInfo>>>(){}.getType();
                BaseModel<List<DuctListInfo>> baseModel = new Gson().fromJson(response,type);


                adapter = new DuctSelectAdapter(this,baseModel.getData());
                adapter.setListener(new DuctSelectAdapter.ItemClick() {
                    @Override
                    public void itemClick(int position) {
                        //TODO
                        PopUpwindowUtil.createPopUpWindowDuctInfo(SelectDuctActivity.this, baseModel.getData().get(position).getDuct_name() + "", null,baseModel.getData().get(position).getDuct_cat().get(0).getDuct_cat_name(),baseModel.getData().get(position).getDuct_cat().get(1).getDuct_cat_name(), new PopUpwindowUtil.dialogClickDuctListener() {
                            @Override
                            public void firstClick() {
                                //TODO
                                startActivity(new Intent(SelectDuctActivity.this,DuctinfoEditActivity.class));
                            }

                            @Override
                            public void secendClick() {
                                startActivity(new Intent(SelectDuctActivity.this,DuctinfoEditActivity.class));
                            }

                            @Override
                            public void closeClick() {

                            }
                        });
                        WindowManager.LayoutParams lp=((Activity)SelectDuctActivity.this).getWindow().getAttributes();
                        lp.alpha=0.4f;
                        ((Activity)SelectDuctActivity.this).getWindow().setAttributes(lp);
                        PopUpwindowUtil.popupWindow.showAtLocation(rlDuctSelect,Gravity.CENTER,0,0);
                    }
                });
                rlDuctSelect.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                rlDuctSelect.setLayoutManager(new LinearLayoutManager(this));
                rlDuctSelect.setAdapter(adapter);
                break;
        }
    }
}
