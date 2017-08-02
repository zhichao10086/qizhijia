package com.wuzhichao.test1.mvp.contract.homeContract;

import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.view.BaseView;

/**
 * Created by 黑客 on 2017/7/7.
 */

public interface MineFragmentContract {

    interface View extends BaseView{

        void showHasLoginView();

        void showNotLoginView();

        void toLoginActivity();

        /*
        判断是否处于活动状态
         */
        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        boolean isLogin();

        void loadUserInfo();

    }
}
