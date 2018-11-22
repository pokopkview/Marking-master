package com.intelligent.marking;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.intelligent.marking.common.utils.ClickUtils;
import com.intelligent.marking.common.utils.StringUtil;


/**
 * Created by Administrator on 2017/4/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract String title_text();
    public abstract void onClick(View v);
    public TextView title;
    public boolean showBack(){
        return true;
    }
    public void onLayoutClick(View v){
        if (ClickUtils.isContinuClick())return;
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                onClick(v);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract int getLayoutID();

    @Override
    protected void onStart() {
        super.onStart();
        if(!showBack())findViewById(R.id.back).setVisibility(View.INVISIBLE);
        title = (TextView)findViewById(R.id.title);
        if (!StringUtil.isEmpty(title_text())&&title!=null){
            title.setText(title_text());
        }
    }

}
