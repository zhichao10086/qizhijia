package com.wuzhichao.test1.config;

/**
 * Created by 黑客 on 2017/6/30.
 */

public class Constant {
   public static final String INTENT_TITLE = "INTENT_TITLE";
    public static final String INTENT_ID = "INTENT_ID";
    public static final String INTENT_TYPE = "INTENT_TYPE";
    public static final String INTENT_PHONE = "INTENT_PHONE";
    public static final String INTENT_PASSWORD = "INTENT_PASSWORD";
    public static final String INTENT_VERIFY = "INTENT_VERIFY";
    public static final String INTENT_USER_ID = "INTENT_USER_ID";
    public static final String RESULT_DATA = "RESULT_DATA";
    public static final String ACTION_EXIT_APP = "ACTION_EXIT_APP";


    /*
    主界面需要用到的
     */
    public static final String CHUFANG_TITLE= "处方";
    public static final String PINGU_TITLE= "评估";
    public static final String MINE_TITLE = "个人";
    public static final String LOGIN_TITLE= "登陆";
    public static final String REGISTER_TITLE= "注册";

    /*
    处方中不同界面的标题
     */
    public static final String CHUFANG_INDICATOR_TUIJIAN= "推荐";
    public static final String CHUFANG_INDICATOR_JIZHU= "脊柱";
    public static final String CHUFANG_INDICATOR_GUPEN = "骨盆";
    public static final String CHUFANG_INDICATOR_XIAZHI= "下肢";
    public static final String CHUFANG_INDICATOR_ZUBU= "足部";
    public static final String CHUFANG_INDICATOR_SHANGZHI= "上肢";

    public static final String FILE_LOGIN_CONFIG = "login_config";

    public static final String FILE_LOGIN_CONFIG_CURRENT_USER = "current_user";
    public static final String FILE_LOGIN_CONFIG_ISLOGIN= "isLogin";

    /*
    处方fragmetn中的不同界面index
     */
   public static final int FRAGMENT_INDEX_TUIJIAN = 1001;
   public static final int FRAGMENT_INDEX_JIANJING= 1002;
   public static final int FRAGMENT_INDEX_JIZHU = 1003;
   public static final int FRAGMENT_INDEX_GUPEN = 1004;
   public static final int FRAGMENT_INDEX_XIAZHI = 1005;
   public static final int FRAGMENT_INDEX_ZUBU = 1006;
   public static final int FRAGMENT_INDEX_SHANGZHI = 1007;


    /*
    activity之间的request code
     */
    public static final int REQUEST_CODE_GET_IMAGE = 2001;
    public static final int REQUEST_CODE_LOGIN =2002;

    /*
    是否登陆
     */
    public static final int LOGIN_FALSE = 0;
    public static final int LOGIN_TRUE = 1;


    /*
    登陆端口的不同操作
     */
    public static final int ACTION_CAOZUO= 0 ;
    public static final int ACTION_REGISTER = 1;
    public static final int ACTION_LOGIN = 2;
    public static final int ACTION_QUERY = 3;



}
