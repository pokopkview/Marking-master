package com.intelligent.marking.activity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.application.MarkingApplication;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.BedStatusModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class PrintPreviewActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.def_text_bed_no)
    TextView defTextBedNo;
    @BindView(R.id.tv_text_bed_no)
    TextView tvTextBedNo;
    @BindView(R.id.def_text_bed_name)
    TextView defTextBedName;
    @BindView(R.id.tv_text_bed_name)
    TextView tvTextBedName;
    @BindView(R.id.def_text_bed_sex)
    TextView defTextBedSex;
    @BindView(R.id.tv_text_bed_sex)
    TextView tvTextBedSex;
    @BindView(R.id.def_text_bed_age)
    TextView defTextBedAge;
    @BindView(R.id.tv_text_bed_age)
    TextView tvTextBedAge;
    @BindView(R.id.tv_duct_type_name)
    TextView tvDuctTypeName;
    @BindView(R.id.iv_printq)
    ImageView ivPrintq;
    @BindView(R.id.rl_bitmap_container)
    RelativeLayout rlBitmapContainer;
    @BindView(R.id.tv_time_create)
    TextView tvTimeCreate;
    @BindView(R.id.def_manager)
    TextView defManager;
    @BindView(R.id.tv_manager_name)
    TextView tvManagerName;
    @BindView(R.id.rl_print_btn)
    RelativeLayout rlPrintBtn;
    @BindView(R.id.rl_print_view)
    RelativeLayout rlPrintView;
    @BindView(R.id.rl_print_pruview)
    RelativeLayout rlPrintPruview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_preview);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText("打印预览");
        rlPrintBtn.setClickable(false);



        value.clear();
        value.put("treat_id",getIntent().getIntExtra("treatid",-1));
        HttpPost(AppConst.GETSHARECODE,value,1);
//        ivPrintq.setImageBitmap(MarkingApplication.createQRImage("https://data.zusux.com/share/20_1", 340, 340));
//
//        rlPrintBtn.setClickable(true);
    }

    @OnClick(R.id.rl_print_btn)
    public void print(View view) {
        //TODO

        rlPrintPruview.setDrawingCacheEnabled(true);
        rlPrintPruview.buildDrawingCache();
        Bitmap bitmap = rlPrintPruview.getDrawingCache();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = this.getResources().getDimensionPixelSize(R.dimen.x192);
        int newHeight = this.getResources().getDimensionPixelSize(R.dimen.y180);

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
//        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
//                true);
        int w = 29;//单位:mm
        float widthcm = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,w,getResources().getDisplayMetrics());

        Bitmap newbm = Bitmap.createScaledBitmap(bitmap,newWidth,290,true);
        MarkingApplication.printBitmap(0, newbm);
    }


    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<String>>(){}.getType();
                BaseModel<String> stringBaseModel = new Gson().fromJson(response,type);
                String codeurl = stringBaseModel.getData();
                Bitmap qrbitmap = MarkingApplication.createQRImage(codeurl,340, 340);
                ivPrintq.setImageBitmap(qrbitmap);
                rlPrintBtn.setClickable(true);
                break;
        }
    }
}
