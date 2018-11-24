package com.intelligent.marking.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {

  private static ToastUtil instance;

  private Toast toast;

  private View view;

  private ToastUtil(Context context){

        toast =new Toast(context);

    view = Toast.makeText(context,"",Toast.LENGTH_SHORT).getView();

    toast.setView(view);

  }

    public static ToastUtil getInstance(Context context){

        if(instance==null){

            instance =new ToastUtil(context);

    }

        return instance;

  }

    public void show(String msg){

        toast.setText(msg);

    toast.setDuration(Toast.LENGTH_SHORT);

    toast.show();

  }

}
