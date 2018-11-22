package com.intelligent.marking.common.okgo.bean;

import java.io.Serializable;

/**
 * Created by ccb on 2017/10/11.
 *
 */


public class ResponseBean<T> implements Serializable {
//    status:状态 1获取成功
//    count:数据总量
//    data:详细数据数组
    public int status;
    public int count;
    public T Result;


}