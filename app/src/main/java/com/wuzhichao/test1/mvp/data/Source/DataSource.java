package com.wuzhichao.test1.mvp.data.Source;

import android.support.annotation.NonNull;

import com.wuzhichao.test1.mvp.bean.ProjectBean;

import java.util.List;
import java.util.Set;

/**
 * Created by 黑客 on 2017/7/1.
 */

public interface DataSource {


    interface LoadSingleDataCallback<T>{
        void onDataLoaded(T data);

        void onDataNotAvailable();
    }

    interface LoadDataCallback<T> {

        void onDataLoaded(List<T> data);

        void onDataNotAvailable();
    }


    /*
        处方的数据获取
     */
    public void getProjects(@NonNull int pro_kind_id,@NonNull LoadDataCallback<ProjectBean> callback);

    public void refreshProjects(@NonNull int pro_kind_id,@NonNull LoadDataCallback<ProjectBean> callback);

    public void saveProjects(List<ProjectBean> data);

    public void getProject(@NonNull int pro_id,@NonNull LoadDataCallback<ProjectBean> callback);




    public void getVideos(@NonNull int pro_id,@NonNull LoadDataCallback callback);

    public void refreshVideos(@NonNull int pro_id,@NonNull LoadDataCallback callback);


    /*
    登陆
     */
    boolean isLogin();

    Set<String> getCurrentUserPwd();

    void loginByUsername(@NonNull String username,@NonNull String pwd,@NonNull int act,@NonNull LoadSingleDataCallback callback);

}
