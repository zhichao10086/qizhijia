package com.wuzhichao.test1.dragger.modules;

import com.wuzhichao.test1.mvp.data.Source.DataSource;
import com.wuzhichao.test1.mvp.data.Source.Local;
import com.wuzhichao.test1.mvp.data.Source.Remote;
import com.wuzhichao.test1.mvp.data.Source.local.LocalDataSource;
import com.wuzhichao.test1.mvp.data.Source.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by 黑客 on 2017/7/3.
 */

@Module
public abstract class DataRepositoryModule {

    @Singleton
    @Binds
    @Local
    public abstract DataSource provideLocalDataSource(LocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    public abstract DataSource provideRemoteDataSource(RemoteDataSource dataSource);
}
