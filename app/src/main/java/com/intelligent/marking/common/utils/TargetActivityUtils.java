package com.intelligent.marking.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * activity 之间跳转处理
 */
public class TargetActivityUtils {
    /**
     * @param context        上下文
     * @param targetActivity 跳转的下一个Activity
     * @param bundle          参数
     * @param isFinish       是否关闭当前页面
     */
    public static void intent(Context context, Class<? extends Activity> targetActivity, Bundle bundle, boolean isFinish) {
        Intent i = new Intent(context, targetActivity);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        context.startActivity(i);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }
}
