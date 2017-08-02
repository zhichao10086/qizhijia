package com.wuzhichao.test1.mvp.view.activity;

import android.os.Bundle;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;
import com.wuzhichao.test1.mvp.view.fragment.pingguFragment.CustomCameraFragment;
import com.wuzhichao.test1.util.ActivityUtil;

/**
 * Created by 黑客 on 2017/7/31.
 */

public class CustomCameraActivity extends BaseActivity {


    private CustomCameraFragment mCustomCameraFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initComponent();

        initListener();


    }



    @Override
    public void initComponent() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_common_notitle);

        mCustomCameraFragment = mCustomCameraFragment.newInstance();

        ActivityUtil.addFragmentToActivity(mFragmentManager,mCustomCameraFragment,R.id.contentFrame);

    }

    @Override
    public void initListener() {

    }
}
