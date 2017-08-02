package com.wuzhichao.test1.dragger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.dragger.qualifier.DefaultSharedPreferences;
import com.wuzhichao.test1.dragger.qualifier.LoginConfigSharedPreferences;

import org.xutils.ViewInjector;
import org.xutils.x;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/1.
 */

@Module
public class AppModule {

    @AppContext Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    @AppContext
    public Context provideContext(){
        return context;
    }


    @Provides
    public ImageLoaderConfiguration provideImageLoaderConfiguration(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(5) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiscCache(context.getCacheDir())) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        return config;
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(ImageLoaderConfiguration imageLoaderConfiguration){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(imageLoaderConfiguration);
        return imageLoader;
    }

    @Provides
    @Singleton
    @DefaultSharedPreferences
    public SharedPreferences provideDefaultSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    @LoginConfigSharedPreferences
    public  SharedPreferences provideLoginConfigSharedPreferences(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.FILE_LOGIN_CONFIG,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.FILE_LOGIN_CONFIG_ISLOGIN, Constant.LOGIN_FALSE);
        editor.putStringSet(Constant.FILE_LOGIN_CONFIG_CURRENT_USER, null);
        editor.commit();

        return sharedPreferences;
    }

    @Provides
    @Singleton
    public ViewInjector provideViewInjectorImpl(){
        return x.view();
    }
}
