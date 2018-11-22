package com.intelligent.marking.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.intelligent.marking.R;


public class StringUtil {
    public static boolean isEmpty(String... str) {
        for (String ss : str) {
            if (ss == null || ss.replaceAll(" ", "").equals("")) return true;
        }
        return false;
    }

    public static String passwordPhone(String str) {
        if (isEmpty(str) || str.length() < 11) {
            return "";
        }
        return str.substring(0, 3) + "****" + str.substring(7);
    }

    public static String getSystemInfoIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId() == null ? "" : tm.getDeviceId();
    }

    public static String saveTwoNum(Object value) {
        return String.format("%.2f ", value);
    }

    public static String resultStr(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length) + "…";
        }
        return str;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static String getText(Activity context, int Id) {
        TextView text = (TextView) context.findViewById(Id);
        if (text == null) return "";
        return text.getText() + "";
    }

    public static String getText(Activity context, int Id, String relace) {
        TextView text = (TextView) context.findViewById(Id);
        if (text == null) return "";
        return (text.getText() + "").replaceAll(relace, "");
    }

    public static String getText(View view, int Id) {
        TextView text = (TextView) view.findViewById(Id);
        if (text == null) return "";
        return text.getText() + "";
    }

    public static String getText(View view, int Id, String relace) {
        TextView text = (TextView) view.findViewById(Id);
        if (text == null) return "";
        return (text.getText() + "").replaceAll(relace, "");
    }

    public static void fuzhi(Activity context, int id, String string) {
        TextView text = ((TextView) context.findViewById(id));
        text.setText(string);
        text.setTextColor(ContextCompat.getColor(context, R.color.black0));
    }

    public static void fuzhi(View view, int id, String string) {
        TextView text = ((TextView) view.findViewById(id));
        text.setText(string);
        text.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black0));
    }

}
