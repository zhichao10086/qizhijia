package com.wuzhichao.test1.mvp.data.Source;

import android.support.annotation.NonNull;

import com.wuzhichao.test1.mvp.bean.ProjectBean;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
public class DataRepository implements DataSource {

    public static String TAG = "DataRepository";

    private final DataSource mLocalDataSource;

    private final DataSource mRemoteDataSource;



    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    @Inject
    DataRepository(@Remote DataSource remoteDataSource,
                   @Local DataSource localDataSource){
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getProjects(@NonNull final int pro_kind_id, @NonNull final LoadDataCallback<ProjectBean> callback) {


        mLocalDataSource.getProjects(pro_kind_id, new LoadDataCallback<ProjectBean>() {
            @Override
            public void onDataLoaded(List<ProjectBean> data) {
                callback.onDataLoaded(data);
            }

            @Override
            public void onDataNotAvailable() {
                refreshProjects(pro_kind_id,callback);
            }
        });


    }


    @Override
    public void refreshProjects(@NonNull final int pro_kind_id, @NonNull final LoadDataCallback<ProjectBean> callback) {
        mRemoteDataSource.getProjects(pro_kind_id, new LoadDataCallback<ProjectBean>() {
            @Override
            public void onDataLoaded(List<ProjectBean> data) {
                callback.onDataLoaded(data);
                saveProjects(data);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveProjects(List<ProjectBean> data) {
        mLocalDataSource.saveProjects(data);
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
        return mLocalDataSource.isLogin();
    }

    @Override
    public Set<String> getCurrentUserPwd() {
        return mLocalDataSource.getCurrentUserPwd();
    }

    @Override
    public void loginByUsername(@NonNull String username, @NonNull String pwd, @NonNull int act, @NonNull final LoadSingleDataCallback callback) {
        mRemoteDataSource.loginByUsername(username, pwd, act, new LoadSingleDataCallback() {
            @Override
            public void onDataLoaded(Object data) {
                callback.onDataLoaded(data);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
