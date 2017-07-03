package com.wuzhichao.test1.ApiService;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by 黑客 on 2017/6/29.
 */

public interface UserService {
    @GET("bars/hotpost.php")
    Call<JSONObject> getUser();
}
