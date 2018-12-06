package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.DuctSelectAdapter;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_duct);
        ButterKnife.bind(this);
        DuctSelectAdapter adapter = new DuctSelectAdapter(this,null);
        adapter.setListener(new DuctSelectAdapter.ItemClick() {
            @Override
            public void itemClick(int position) {
                //TODO
                PopUpwindowUtil.createPopUpWindowDuctInfo(SelectDuctActivity.this, data.get(position) + "", null, new PopUpwindowUtil.dialogClickDuctListener() {
                    @Override
                    public void firstClick() {
                        //TODO
                        startActivity(new Intent(SelectDuctActivity.this,DuctinfoEditActivity.class));
                    }

                    @Override
                    public void secendClick() {

                    }

                    @Override
                    public void closeClick() {

                    }
                });
            }
        });
        rlDuctSelect.setLayoutManager(new LinearLayoutManager(this));
        rlDuctSelect.setAdapter(adapter);
    }

    @Override
    public void getCallBack(String response, int flag) {

    }
}
