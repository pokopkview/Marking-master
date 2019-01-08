package com.intelligent.marking.activity;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Base64;

public class testbase {

    @TargetApi(Build.VERSION_CODES.O)
    public static void main(String [] args){
        String pwds = new String(Base64.getEncoder().encode("12321".getBytes()));
        System.out.println(pwds);
        String pwd = new String(Base64.getDecoder().decode(pwds));
        System.out.println(pwd);
    }
}
