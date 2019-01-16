package com.intelligent.marking.net;

import com.intelligent.marking.Const.AppConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheUtils {

    Map<String,String> cache = new HashMap<>();
    List<String> cacheURL = new ArrayList<>();

    private static CacheUtils cacheUtils = null;

    public CacheUtils(){
        cacheURL.add(AppConst.DUCTINDEX);
    }

    public static CacheUtils getInstance(){
        synchronized (CacheUtils.class){
            if(cacheUtils==null){
                cacheUtils = new CacheUtils();
            }
        }
        return cacheUtils;
    }

    public void put(String key,String value){
        cache.put(key, value);
    }

    public String getKey(String key){
        return cache.get(key);
    }

    public Boolean containKey(String key){
        return cache.containsKey(key);
    }

    public Boolean containURL(String url){
        return cacheURL.contains(url);
    }


}
