package com.wuzhichao.test1.mvp.view.fragment.homeFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.dragger.components.DaggerMineFragmentComponent;
import com.wuzhichao.test1.dragger.components.MineFragmentComponent;
import com.wuzhichao.test1.dragger.modules.MineFragmentModule;
import com.wuzhichao.test1.mvp.contract.homeContract.MineFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.homePresenter.MineFragmentPresenter;
import com.wuzhichao.test1.mvp.view.activity.LoginActivity;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;
import com.wuzhichao.test1.util.Log;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/6/30.
 */

@ContentView(R.layout.fragment_mine)
public class MineFragment extends BaseFragment implements MineFragmentContract.View ,View.OnClickListener {

    @Inject
    MineFragmentPresenter mPresenter;

    MineFragmentComponent mMineFragmentComponent;

    private TextView tv_title;

    @ViewInject(R.id.btn_login)
    private Button btn_login;



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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        switch (requestCode){
            case Constant.REQUEST_CODE_LOGIN:
                if(resultCode == Activity.RESULT_OK){

                }
                break;
            default:
                break;
        }
    }


    public static MineFragment newInstance() {
        return new MineFragment();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    @Override
    public void initView() {

    }

    @Override
    public void initComponent() {
        mMineFragmentComponent = DaggerMineFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .mineFragmentModule(new MineFragmentModule(this))
                .build();
        mMineFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void showHasLoginView() {
        btn_login.setVisibility(View.GONE);
    }

    @Override
    public void showNotLoginView() {
        btn_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void toLoginActivity() {
        mIntent = new Intent(getActivity(), LoginActivity.class);
        Log.d(TAG,"跳转到LoginActivity");
        startActivityForResult(mIntent, Constant.REQUEST_CODE_LOGIN);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                toLoginActivity();
                break;
            default:
                break;
        }
    }
}
