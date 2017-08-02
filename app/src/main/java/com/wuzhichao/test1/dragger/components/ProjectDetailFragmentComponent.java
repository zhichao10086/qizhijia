package com.wuzhichao.test1.dragger.components;

import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.modules.ProjectDetailFragmentModule;
import com.wuzhichao.test1.mvp.view.fragment.ProjectDetailFragment;

import dagger.Component;

/**
 * Created by 黑客 on 2017/7/28.
 */

@FragmentScoped
@Component( dependencies = FragmentComponent.class,modules = {ProjectDetailFragmentModule.class})
public interface ProjectDetailFragmentComponent {
    void inject(ProjectDetailFragment fragment);
}
