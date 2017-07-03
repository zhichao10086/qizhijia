package com.wuzhichao.test1.mvp.contract;

import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.view.BaseView;

/**
 * Created by 黑客 on 2017/6/30.
 */

public interface ChufangContract {
    interface View extends BaseView<Presenter> {

        /*
        显示项目界面
         */
        void showList(String chufangType);

        /*
        显示错误信息
         */
        void showLoadError();


        /*
        设置标题
         */
        void setTitle(String title);

        /*
        判断是否处于活动状态
         */
        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        /*
        加载身体不同位置的信息
         */
        void loadData(String chufangType);


        void saveTask(String title, String description);

        void populateTask();

        boolean isDataMissing();
    }
}
