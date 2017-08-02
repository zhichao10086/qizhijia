package com.wuzhichao.test1.mvp.presenter.pingguPresenter;

import com.wuzhichao.test1.mvp.contract.pingguContract.CustomCameraFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/31.
 */

public class CustomCameraFragmentPresenter implements CustomCameraFragmentContract.Presenter{

    public static String TAG="CustomCameraFragmentPresenter";

    private final DataRepository mDataRepository;

    private final CustomCameraFragmentContract.View mView;


    @Inject
    public CustomCameraFragmentPresenter(DataRepository mDataRepository, CustomCameraFragmentContract.View mView) {
        this.mDataRepository = mDataRepository;
        this.mView = mView;
    }


    @Override
    public void start() {

    }
}
