package com.wuzhichao.test1.ApiService;

import com.squareup.okhttp.ResponseBody;
import com.wuzhichao.test1.mvp.bean.BarInfoBean;
import com.wuzhichao.test1.mvp.bean.ResponseData;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by 黑客 on 2017/6/29.
 */

public interface PostService {

    @GET("bars/hotbar.php")
    Call<ResponseData<BarInfoBean>> getHotPost();

    @GET("bars/hotpost.php")
    Call<ResponseBody> getHotPost1();
}
