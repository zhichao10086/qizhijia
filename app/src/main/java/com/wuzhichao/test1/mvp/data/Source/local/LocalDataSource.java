package com.wuzhichao.test1.mvp.data.Source.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.dragger.qualifier.DefaultSharedPreferences;
import com.wuzhichao.test1.dragger.qualifier.LoginConfigSharedPreferences;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.data.Source.DataSource;
import com.wuzhichao.test1.util.Log;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
public class LocalDataSource implements DataSource {

    public static final String TAG = "LocalDataSourcee";

    private DBManager mDBManager;

    @DefaultSharedPreferences
    private final SharedPreferences mDefaultSharedPreferences;

    @LoginConfigSharedPreferences
    private final SharedPreferences mLoginDefaultSharedPreferences;

    @Inject
    public LocalDataSource(@AppContext Context context,
                           @DefaultSharedPreferences SharedPreferences sharedPreferences,
                           @LoginConfigSharedPreferences SharedPreferences mLoginDefaultSharedPreferences){

        mDBManager= new DBManager(context);
        mDefaultSharedPreferences = sharedPreferences;
        this.mLoginDefaultSharedPreferences = mLoginDefaultSharedPreferences;
    }


    @Override
    public void getProjects(@NonNull int pro_kind_id, @NonNull LoadDataCallback<ProjectBean> callback) {
        List<ProjectBean> projectBeanList = mDBManager.queryProjects(pro_kind_id);

        if(projectBeanList.isEmpty()){
            callback.onDataNotAvailable();
            Log.d(TAG,"获取本地project失败");
        }else {
            callback.onDataLoaded(projectBeanList);
            Log.d(TAG,"获取本地project成功");
        }

    }

    @Override
    public void refreshProjects(@NonNull int pro_kind_id, @NonNull LoadDataCallback<ProjectBean> callback) {

    }

    public void saveProjects(List<ProjectBean> data){
        mDBManager.updateProjects(data);

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
        if (mLoginDefaultSharedPreferences.getInt(Constant.FILE_LOGIN_CONFIG_ISLOGIN,Constant.LOGIN_FALSE) == 1){
            Log.d(TAG,"isLogin:true");
            return true;
        }else {
            Log.d(TAG,"isLogin:false");
            return false;
        }

    }

    @Override
    public Set<String> getCurrentUserPwd() {
        return mLoginDefaultSharedPreferences.getStringSet(Constant.FILE_LOGIN_CONFIG_CURRENT_USER,null);
    }

    @Override
    public void loginByUsername(@NonNull String username, @NonNull String pwd, @NonNull int act, @NonNull LoadSingleDataCallback callback) {

    }
}
