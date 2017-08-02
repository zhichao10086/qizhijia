package com.wuzhichao.test1.mvp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.dragger.components.DaggerLoginFragmentComponent;
import com.wuzhichao.test1.dragger.components.LoginFragmentComponent;
import com.wuzhichao.test1.dragger.modules.LoginFragmentModule;
import com.wuzhichao.test1.mvp.contract.LoginOrRegisterContract.LoginFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.LoginOrRegisterPresenter.LoginFragmentPresenter;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/19.
 */

@ContentView(R.layout.fragment_login)
public class LoginFragment extends BaseFragment implements LoginFragmentContract.View,View.OnClickListener{

    @Inject
    LoginFragmentPresenter mPresenter;

    LoginFragmentComponent mLoginFragmentComponent;



    @ViewInject(R.id.et_username)
    private EditText et_username;

    @ViewInject(R.id.et_password)
    private EditText et_password;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.btn_register)
    private Button btn_register;

    @ViewInject(R.id.btn_qq)
    private Button btn_qq;

    @ViewInject(R.id.btn_weixin)
    private Button btn_weixin;

    @ViewInject(R.id.btn_weibo)
    private Button btn_weibo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    @Override
    public void initView() {

    }

    @Override
    public void initComponent() {
        mLoginFragmentComponent = DaggerLoginFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .loginFragmentModule(new LoginFragmentModule(this))
                .build();

        mLoginFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {
        btn_login.setOnClickListener(this);
        btn_weibo.setOnClickListener(this);
        btn_weixin.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String username = et_username.getText().toString();
                String pwd = et_password.getText().toString();

                if(checkInputIsRight(username,pwd)){
                    if (mPresenter.loginByUsername(username,pwd, Constant.ACTION_LOGIN)){

                    }
                }
                break;
            case R.id.btn_register:
                break;
            case R.id.btn_qq:
                break;
            case R.id.btn_weixin:
                break;
            case R.id.btn_weibo:
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoginFailed() {

    }

    @Override
    public void showNotNullInfo(String username, String pwd) {

    }

    @Override
    public void showNotNullInfo() {
        Toast.makeText(mContext,"用户名和密码不能为空",Toast.LENGTH_SHORT);
    }

    @Override
    public boolean checkInputIsRight(String username, String pwd) {

        if (username == null || username == ""){
            showNotNullInfo();
            return false;
        }else if(pwd == null || username == ""){
            showNotNullInfo();
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void hasLogin() {
        mIntent = new Intent();
        mIntent.putExtra("user_id",et_username.getText().toString());
        getActivity().setResult(Activity.RESULT_OK,mIntent);
        getActivity().finish();
    }

    @Override
    public void notLogin() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }


}
