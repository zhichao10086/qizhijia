package com.wuzhichao.test1.ApiService;

import com.wuzhichao.test1.mvp.bean.ResponseData;
import com.wuzhichao.test1.mvp.bean.UserBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 黑客 on 2017/6/29.
 */

public interface UserService {

    @GET("user/userinfo.php")
    Call<ResponseData<UserBean>> getUserInfo(@Query("user_id") String user_id);
}
