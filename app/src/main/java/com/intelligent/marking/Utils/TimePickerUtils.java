package com.intelligent.marking.Utils;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.intelligent.marking.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePickerUtils {

    private static TimePickerView pickerView;
    private static SimpleDateFormat sdf;

    public static void showTimePicker(Context context,timeBack listenner,int type){
        boolean [] timetype = null;
        switch (type){
            case 1:
                sdf = new SimpleDateFormat("HH:mm");
                timetype = new boolean[]{false, false, false, true, true, false};
                break;
            case 2:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                timetype = new boolean[]{true, true, true, false, false, false};
                break;
        }
//        SimpleDateFormat finalSdf = sdf;
        pickerView = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                listenner.getTime(sdf.format(date));

            }
        })
                .setLayoutRes(R.layout.time_pic_view_layout, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
//                        ButterKnife.bind(v);
                        v.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pickerView.dismiss();
                                pickerView.returnData();
                            }
                        });

                        v.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pickerView.dismiss();
                            }
                        });
                    }
                })
                .setType(timetype)// 默认全部显示
                .build();
        pickerView.show();
    }

    public interface timeBack{
        void getTime(String str);
    }
}
