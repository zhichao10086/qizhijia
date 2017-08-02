package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.MineFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.MineFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/7.
 */
@FragmentScoped
@Component(dependencies = FragmentComponent.class,modules = {MineFragmentModule.class})
public interface MineFragmentComponent {
    void inject(MineFragment fragment);
}
