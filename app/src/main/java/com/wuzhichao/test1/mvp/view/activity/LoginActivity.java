package com.wuzhichao.test1.mvp.view.activity;

import android.os.Bundle;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;
import com.wuzhichao.test1.mvp.view.fragment.LoginFragment;
import com.wuzhichao.test1.util.ActivityUtil;

import org.xutils.view.annotation.ContentView;

/**
 * Created by 黑客 on 2017/7/19.
 */

@ContentView(R.layout.activity_common)
public class LoginActivity extends BaseActivity{

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_login);
        initComponent();
        initView();
    }

    @Override
    public void onBackPressed() {
        this.setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void initView() {

        setTitle(Constant.LOGIN_TITLE);

        mLoginFragment = mLoginFragment.newInstance();

        ActivityUtil.addFragmentToActivity(mFragmentManager,mLoginFragment,R.id.contentFrame);
    }


    @Override
    public void initListener() {

    }
}
