package com.intelligent.marking.set;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.intelligent.marking.R;
import com.intelligent.marking.set.bean.JsonBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimerPicer {
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    public static void showTimePic(Context context,View views){
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.pick_time_layout,null,false);
//        int height = context.getResources().getDimensionPixelSize(R.dimen.y320);
//        PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,height);
//        popupWindow.setContentView(view);
//        WheelView view1 = popupWindow.getContentView().findViewById(R.id.hour);
//        WheelView view2 = popupWindow.getContentView().findViewById(R.id.secend);
//        view1.setTextSize(16);
//        view2.setTextSize(16);
//        view1.setLineSpacingMultiplier(1.9f);
//        view2.setLineSpacingMultiplier(1.9f);
//        view1.setDividerColor(Color.rgb(204,204,204));
//        view2.setDividerColor(Color.rgb(204,204,204));
//        view1.setTextColorCenter(Color.rgb(80,80,80));
//        view2.setTextColorCenter(Color.rgb(80,80,80));
//        view1.setTextColorOut(Color.rgb(199,199,199));
//        view2.setTextColorOut(Color.rgb(199,199,199));
//        String [] hour = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
//
//        String [] min = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
//        List<Object> value = new ArrayList<>();
//        value.add("1");
//        value.add("2");
//        value.add("3");
//        value.add("4");
//        value.add("5");
//        view1.setAdapter(new WhellerAdapter(context, Arrays.asList(hour)));
//        view2.setAdapter(new WhellerAdapter(context,Arrays.asList(min)));
//        popupWindow.showAtLocation(views,Gravity.BOTTOM,0,0);
        PickerOptions pickerOptions = new PickerOptions(PickerOptions.TYPE_PICKER_TIME);
        pickerOptions.context = context;
        TimePickerView timePickerView = new TimePickerView(pickerOptions);
        timePickerView.show();
    }

    public static class WhellerAdapter implements WheelAdapter {
        private List<Object> value;
        private Context context;

        public WhellerAdapter(Context context, List<Object> date){
            this.context = context;
            value = date;
        }

        @Override
        public int getItemsCount() {
            return value.size();
        }

        @Override
        public Object getItem(int index) {
            return value.get(index);
        }

        @Override
        public int indexOf(Object o) {
            return value.indexOf(o);
        }
    }

}
