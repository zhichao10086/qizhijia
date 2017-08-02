package com.wuzhichao.test1.mvp.contract.LoginOrRegisterContract;

import android.support.annotation.NonNull;

import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.view.BaseView;

/**
 * Created by 黑客 on 2017/7/19.
 */

public interface LoginFragmentContract {
    interface View extends BaseView {

        void showLoginFailed();

        void showNotNullInfo(String username,String pwd);

        void showNotNullInfo();

        boolean checkInputIsRight(String username,String pwd);

        void hasLogin();

        void notLogin();

    }

    interface Presenter extends BasePresenter {
        boolean loginByUsername(@NonNull String username, @NonNull String pwd, @NonNull int act);

        boolean loginByQQ();

        boolean loginByWeinxin();

        boolean loginByWeibo();

        void hasLogin();
    }
}
