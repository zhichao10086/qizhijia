package com.wuzhichao.test1.ApiService;

import com.squareup.okhttp.ResponseBody;
import com.wuzhichao.test1.mvp.bean.BarInfoBean;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.bean.ResponseData;
import com.wuzhichao.test1.mvp.bean.UserBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by 黑客 on 2017/6/29.
 */

public interface PostService {

    @GET("bars/hotbar.php")
    Call<ResponseData<BarInfoBean>> getHotPost();

    @GET("bars/hotpost.php")
    Call<ResponseBody> getHotPost1();

    @GET("training/project_list.php")
    Call<ResponseData<ProjectBean>> getProjects(@Query("project_kind_id")int project_kind_id);


    @GET("training/action.php")
    Call<ResponseData<ProjectBean>> getProject(@Query("project_id")int project_id);


    @FormUrlEncoded
    @POST("user/logintest.php")
    Call<ResponseData<UserBean>> loginByUsername(@Field("userid") String userid,@Field("act") int act,@Field("password") String pwd);

}
