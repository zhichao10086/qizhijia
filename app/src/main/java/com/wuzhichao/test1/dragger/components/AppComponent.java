package com.wuzhichao.test1.dragger.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.wuzhichao.test1.ApiService.PostService;
import com.wuzhichao.test1.dragger.modules.AppModule;
import com.wuzhichao.test1.dragger.modules.DataRepositoryModule;
import com.wuzhichao.test1.dragger.modules.NetModule;
import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.dragger.qualifier.DefaultSharedPreferences;
import com.wuzhichao.test1.dragger.qualifier.LoginConfigSharedPreferences;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;

import org.xutils.ViewInjector;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataRepositoryModule.class})
public interface AppComponent {
    @AppContext
    Context getContext();

    @LoginConfigSharedPreferences
    SharedPreferences getLoginConfigSharedPreferences();

    @DefaultSharedPreferences
    SharedPreferences getDefaultSharedPreferences();

    Cache getOkHttpCache();

    Gson getGson();

    OkHttpClient getOkHttpClient();

    Retrofit getRetrofit();

    PostService getPostService();

    DataRepository getDataRepository();

    ImageLoader getImageLoader();

    ViewInjector getViewInjector();
}
