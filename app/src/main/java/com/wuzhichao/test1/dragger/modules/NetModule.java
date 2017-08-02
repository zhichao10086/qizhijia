package com.wuzhichao.test1.dragger.modules;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.wuzhichao.test1.ApiService.PostService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by 黑客 on 2017/7/1.
 */

@Module
public class NetModule {

    Application mApplication;
    String mBaseUrl;

    public NetModule(Application application,String baseUrl){
        this.mApplication = application;
        this.mBaseUrl = baseUrl;
    }


    @Provides
    public Application provideApplication(){
        return  mApplication;
    }


    @Provides
    @Singleton
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        //client.setCache(cache);
        return client;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public PostService providePostService(Retrofit retrofit){
        return retrofit.create(PostService.class);
    }

}
