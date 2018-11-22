package com.intelligent.marking.common.app;

/**
 * Created by Administrator on 2017/4/26.
 */

public class CommonIp {
    public static int windowWidth = 0;
    public static int windowHeight = 0;


    public final static String URL="https://data.zusux.com";


    /**
     * 获取省份
     */
    public final static String getProvince=URL+"/api/region/index";

    //  /account/accountLogin  /account/register



    public static String state;          //登录成功状态

//    public static String phone;            //用户账号
//    public static String token;            //原始token
//    public static String accountId;            //账号id
//    public static String chatId;
//    public static String mToken;            //加密token
//    public static String MaccountId;            //我的账号id
//    public static String MaccountProfilePhoto;            //我的头像
//    public static String MaccountNickname;            //我的账号昵称
//    public static String Mcertification;            //
//    public static String MrealName;          //我的名字
//    public static String MidentityCard;            //我的身份证
//    public static String Msex;            //我的性别
//    public static String MhealthCare;            //我的医护人员
//    public static String Maddress;            //我的地址
//    public static String MhospitalBed;            //我的病床编号
//    public static String MhospitalStatus;            //我的医院状况
//    public static String McareWorker;            //我的护工
//    public static String MemergencyContact;            //我的紧急联系人
//    public static String MtreatmentStatus;            //我的治疗状况
//    public static String McreateTime;            //我的创建时间
//    public static String MlabelList;            //我的标签列表
//    public static String Mphone;            //我的标签列表

    public static final int TYPENUM_CONTENT = 4;
    public static final int TUPIAN = 1;
    public static final int SHIPIAN = 2;
    public static final int LUYIN = 3;

    public static final String REQUEST_PARAMETER= "请求参数=" ;
    public static final String REQUEST_SUCCESS= "返回参数=" ;
    public static final String REQUEST_EXCEOTON= "请求异常=" ;

    public static final String LISTVIEW_FIRSTLOAD_NULL = "没有任何记录" ;
    public static final String LISTVIEW_LOADING = "加载中，请稍后" ;
    public static final String LISTVIEW_LOADING_FAILD = "加载失败，点击重新加载" ;
    public static final String LISTVIEW_REFRESHING_TOUCH_DOWN = "下拉加载" ;
    public static final String LISTVIEW_REFRESHING_TOUCH_HALF = "释放刷新" ;
    public static final String LISTVIEW_REFRESHING = "正在刷新，请稍后" ;

}
