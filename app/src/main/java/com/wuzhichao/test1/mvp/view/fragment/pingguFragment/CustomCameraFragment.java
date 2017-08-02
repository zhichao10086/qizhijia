package com.wuzhichao.test1.mvp.view.fragment.pingguFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.dragger.components.CustomCameraFragmentComponent;
import com.wuzhichao.test1.dragger.components.DaggerCustomCameraFragmentComponent;
import com.wuzhichao.test1.dragger.modules.CustomCameraFragmentModule;
import com.wuzhichao.test1.mvp.contract.pingguContract.CustomCameraFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.pingguPresenter.CustomCameraFragmentPresenter;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/31.
 */

public class CustomCameraFragment extends BaseFragment implements CustomCameraFragmentContract.View,SurfaceHolder.Callback{


    CustomCameraFragmentComponent mCustomCameraFragmentComponent;

    @Inject
    CustomCameraFragmentPresenter mCustomCameraFragmentPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_custom_camera,container,false);

        initView();
        initListener();
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }



    public static CustomCameraFragment newInstance() {
        return new CustomCameraFragment();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initComponent() {
        mCustomCameraFragmentComponent = DaggerCustomCameraFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .customCameraFragmentModule(new CustomCameraFragmentModule(this))
                .build();

        mCustomCameraFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
