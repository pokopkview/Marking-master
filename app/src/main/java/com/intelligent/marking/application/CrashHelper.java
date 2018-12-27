package com.intelligent.marking.application;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;
import com.intelligent.marking.activity.LoginActivity;

public class CrashHelper implements Thread.UncaughtExceptionHandler {
    private Context context;
    public CrashHelper(Context context){
        this.context=context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showToast(t);
    }

    /**
     * 操作
     * @param thread
     */
    private void showToast(Thread thread) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "程序异常，重新启动", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();

        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restartApp();
    }

    /**
     * 重启应用
     */
    private void restartApp(){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());//再此之前可以做些退出等操作
    }
}
