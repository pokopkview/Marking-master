package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.intelligent.marking.R;
import com.intelligent.marking.view.SelectTopItemView;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LinearLayout linearLayout = findViewById(R.id.ll_container);
        SelectTopItemView view = new SelectTopItemView(this);
        linearLayout.addView(view);
    }
}
