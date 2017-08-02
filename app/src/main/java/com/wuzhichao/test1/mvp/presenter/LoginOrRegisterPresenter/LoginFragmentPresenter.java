package com.wuzhichao.test1.mvp.presenter.LoginOrRegisterPresenter;

import android.support.annotation.NonNull;

import com.wuzhichao.test1.mvp.bean.UserBean;
import com.wuzhichao.test1.mvp.contract.LoginOrRegisterContract.LoginFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;
import com.wuzhichao.test1.mvp.data.Source.DataSource;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/19.
 */

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {

    public static final String TAG = "LoginFragmentPresenter";

    private final DataRepository mDataRepository;

    private final LoginFragmentContract.View mView;


    @Inject
    public LoginFragmentPresenter(DataRepository mDataRepository, LoginFragmentContract.View mView){

        this.mDataRepository = mDataRepository;
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean loginByUsername(@NonNull final String username, @NonNull String pwd, @NonNull int act) {

        UserBean userBean = null;

        mDataRepository.loginByUsername(username, pwd, act, new DataSource.LoadSingleDataCallback() {
            @Override
            public void onDataLoaded(Object data) {
                UserBean userBean = (UserBean)data;
            }

            @Override
            public void onDataNotAvailable() {
            }
        });

        if(userBean != null){
            if(userBean.getUser_id().equals(username)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    @Override
    public boolean loginByQQ() {
        return false;
    }

    @Override
    public boolean loginByWeinxin() {
        return false;
    }

    @Override
    public boolean loginByWeibo() {
        return false;
    }

    @Override
    public void hasLogin() {

    }
}
