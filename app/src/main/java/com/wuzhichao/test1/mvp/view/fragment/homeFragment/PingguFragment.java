package com.wuzhichao.test1.mvp.view.fragment.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.dragger.components.DaggerPingguFragmentComponent;
import com.wuzhichao.test1.dragger.components.PingguFragmentComponent;
import com.wuzhichao.test1.dragger.modules.PingguFragmentModule;
import com.wuzhichao.test1.mvp.contract.homeContract.PingguFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.homePresenter.PingguFragmentPresenter;
import com.wuzhichao.test1.mvp.view.activity.CustomCameraActivity;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/6/30.
 */


public class PingguFragment extends BaseFragment implements PingguFragmentContract.View,View.OnClickListener {

    @Inject
    PingguFragmentPresenter mPresenter;

    private PingguFragmentComponent mPingguFragmentComponent;


    private TextView tv_title;
    private Button btn_pinggu;
    private Button btn_zice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pinggu,container,false);
    }


    public static PingguFragment newInstance() {
        return new PingguFragment();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public void initView() {
        tv_title = (TextView) mView.findViewById(R.id.tv_title);
        btn_pinggu = (Button) mView.findViewById(R.id.btn_pinggu);
        btn_zice = (Button) mView.findViewById(R.id.btn_zice);
    }

    @Override
    public void initComponent() {
        mPingguFragmentComponent = DaggerPingguFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .pingguFragmentModule(new PingguFragmentModule(this))
                .build();
        mPingguFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {
        btn_pinggu.setOnClickListener(this);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pinggu:
                mIntent = new Intent(getActivity(), CustomCameraActivity.class);
                toAcvity(mIntent);
                break;
            case R.id.btn_zice:
                //mIntent = new Intent(getActivity(),CustomCameraActivity.class);
                //toAcvity(mIntent);
                break;
            default:
                break;
        }
    }
}