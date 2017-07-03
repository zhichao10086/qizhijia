package com.wuzhichao.test1.dragger.modules;

import com.wuzhichao.test1.mvp.data.Source.ChufangDataSource;
import com.wuzhichao.test1.mvp.data.Source.Local;
import com.wuzhichao.test1.mvp.data.Source.Remote;
import com.wuzhichao.test1.mvp.data.Source.local.ChufangLocalDataSource;
import com.wuzhichao.test1.mvp.data.Source.remote.ChufangRemoteDataSource;

import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/3.
 */

@Module
abstract class ChufangRepositoryModule {

    @Singleton
    @Provides
    @Local
    abstract ChufangDataSource provideChufangLocalDataSource(ChufangLocalDataSource dataSource);

    @Singleton
    @Provides
    @Remote
    abstract ChufangDataSource provideChufangRemoteDataSource(ChufangRemoteDataSource dataSource);
}
