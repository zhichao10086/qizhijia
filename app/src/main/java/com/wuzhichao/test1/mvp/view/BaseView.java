package com.wuzhichao.test1.mvp.view;

import com.wuzhichao.test1.mvp.presenter.BasePresenter;

/**
 * Created by 黑客 on 2017/6/30.
 */

public interface BaseView {

    /*
    设置presenter
     */
    void setPresenter(BasePresenter presenter);


    /*
    初始化view，找到view的实例
     */
    void initView();

    /*
    初始化注入器
     */
    void initComponent();

    /*
    初始化监听器

     */
    void initListener();
}
