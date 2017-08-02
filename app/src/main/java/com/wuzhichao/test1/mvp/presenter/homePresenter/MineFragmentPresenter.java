package com.wuzhichao.test1.mvp.presenter.homePresenter;

import com.wuzhichao.test1.mvp.contract.homeContract.MineFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/7.
 */

public class MineFragmentPresenter implements MineFragmentContract.Presenter {
    private final DataRepository mDataRepository;

    private final MineFragmentContract.View mView;

    private boolean mFirstLoad = true;


    @Inject
    MineFragmentPresenter(DataRepository dataRepository,MineFragmentContract.View view){
        mDataRepository = dataRepository;
        mView = view;

    }

    @Override
    public void start() {
        if(isLogin()){
            mView.showHasLoginView();
        }else {
            mView.showNotLoginView();
        }
    }



    @Override
    public boolean isLogin() {
        return mDataRepository.isLogin();
    }

    @Override
    public void loadUserInfo() {

    }
}
