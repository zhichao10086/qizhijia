package com.wuzhichao.test1.mvp.data.Source;

import com.wuzhichao.test1.mvp.data.ChufangModel;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
public class ChufangRepository implements ChufangDataSource{

    private final ChufangDataSource mChufangLocalDataSource;

    private final ChufangDataSource mChufangRemoteDataSource;


    Map<String, ChufangModel> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    @Inject
    ChufangRepository(@Remote ChufangDataSource chufangRemoteDataSource,
                      @Local ChufangDataSource chufangLocalDataSource){
        mChufangLocalDataSource = chufangLocalDataSource;
        mChufangRemoteDataSource = chufangRemoteDataSource;
    }

}
