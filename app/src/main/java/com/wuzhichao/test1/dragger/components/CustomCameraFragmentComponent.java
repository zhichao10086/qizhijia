package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.CustomCameraFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.pingguFragment.CustomCameraFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/31.
 */

@FragmentScoped
@Component( dependencies = FragmentComponent.class,modules = {CustomCameraFragmentModule.class})
public interface CustomCameraFragmentComponent {
    void inject(CustomCameraFragment fragment);
}
