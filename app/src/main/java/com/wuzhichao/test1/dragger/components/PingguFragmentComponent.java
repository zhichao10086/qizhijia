package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.PingguFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.PingguFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/6.
 */
@FragmentScoped
@Component(dependencies = FragmentComponent.class,modules = {PingguFragmentModule.class})
public interface PingguFragmentComponent {

    void inject(PingguFragment fragment);
}
