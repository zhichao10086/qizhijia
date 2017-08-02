package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.ChufangFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.ChufangFragment;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.VpSimpleFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/3.
 */

@FragmentScoped
@Component( dependencies = FragmentComponent.class,modules = {ChufangFragmentModule.class})
public interface ChufangFragmentComponent {
    //void inject(HomeActivity activity);
    void inject(ChufangFragment fragment);
    void inject(VpSimpleFragment fragment);
}
