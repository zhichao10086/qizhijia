package com.wuzhichao.test1.mvp.data.Source.remote;

import android.support.annotation.NonNull;

import com.wuzhichao.test1.ApiService.PostService;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.bean.ResponseData;
import com.wuzhichao.test1.mvp.bean.UserBean;
import com.wuzhichao.test1.mvp.data.Source.DataSource;
import com.wuzhichao.test1.util.Log;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
public class RemoteDataSource implements DataSource {

    public static String TAG = "REMOTE_DATA_SOURCE";

    PostService postService;

    @Inject
    public RemoteDataSource(PostService postService){
        this.postService = postService;
    }



    @Override
    public void getProjects(@NonNull int pro_kind_id, @NonNull final LoadDataCallback callback) {
        Call<ResponseData<ProjectBean>> call = postService.getProjects(pro_kind_id);
        call.enqueue(new Callback<ResponseData<ProjectBean>>() {
            @Override
            public void onResponse(Response<ResponseData<ProjectBean>> response, Retrofit retrofit) {

                Log.i(TAG,"获取projects成功。\n" + response.body().toString());
                callback.onDataLoaded(response.body().getData());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    @Override
    public void refreshProjects(@NonNull int pro_kind_id, @NonNull LoadDataCallback<ProjectBean> callback) {

    }


    @Override
    public void saveProjects(List<ProjectBean> data) {

    }

    @Override
    public void getProject(@NonNull int pro_id, @NonNull LoadDataCallback<ProjectBean> callback) {

    }

    @Override
    public void getVideos(@NonNull int pro_id, @NonNull LoadDataCallback callback) {

    }

    @Override
    public void refreshVideos(@NonNull int pro_id, @NonNull LoadDataCallback callback) {

    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public Set<String> getCurrentUserPwd() {
        return null;
    }

    @Override
    public void loginByUsername(@NonNull String username, @NonNull String pwd, @NonNull int act, @NonNull final LoadSingleDataCallback callback) {
        final Call<ResponseData<UserBean>> call = postService.loginByUsername(username,act,pwd);
        call.enqueue(new Callback<ResponseData<UserBean>>() {
            @Override
            public void onResponse(Response<ResponseData<UserBean>> response, Retrofit retrofit) {
                callback.onDataLoaded(response.body().getData());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG,"用户名方式登陆失败。");
            }
        });
    }


}
