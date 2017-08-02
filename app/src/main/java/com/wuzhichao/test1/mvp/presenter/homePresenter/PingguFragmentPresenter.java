package com.wuzhichao.test1.mvp.presenter.homePresenter;

import com.wuzhichao.test1.mvp.contract.homeContract.PingguFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/6.
 */

public class PingguFragmentPresenter implements PingguFragmentContract.Presenter {

    private final DataRepository mDataRepository;

    private final PingguFragmentContract.View mView;

    private boolean mFirstLoad = true;


    @Inject
    PingguFragmentPresenter(DataRepository dataRepository,PingguFragmentContract.View view){
        mDataRepository = dataRepository;
        mView = view;

    }

    @Override
    public void start() {

    }
}
