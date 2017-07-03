package com.wuzhichao.test1.mvp.data.Source;


import com.wuzhichao.test1.dragger.modules.AppModule;
import com.wuzhichao.test1.dragger.modules.ChufangRepositoryModule;
import com.wuzhichao.test1.dragger.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/3.
 */

@Singleton
@Component(modules = {ChufangRepositoryModule.class, AppModule.class, NetModule.class})
public interface ChufangRepositoryComponent {

    ChufangRepository getChufangRepository();
}
