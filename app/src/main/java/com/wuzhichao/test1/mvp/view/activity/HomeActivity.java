package com.wuzhichao.test1.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.config.Constant;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.ChufangFragment;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.MineFragment;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.PingguFragment;
import com.wuzhichao.test1.util.ActivityUtil;

import static org.carrot2.shaded.guava.common.base.Preconditions.checkNotNull;

/**
 * Created by 黑客 on 2017/6/30.
 */
public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{



    ChufangFragment mChufangFragment = null;
    PingguFragment mPingguFragment = null;
    MineFragment mMineFragment = null;


    RadioGroup mRadioGroup;

    FrameLayout mFrameLayout;

    RadioButton mRbtn_chufang;
    RadioButton mRbtn_pinggu;
    RadioButton mRbtn_Mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initComponent();

        initListener();

        /*
        //初始化fragment，并将他们反序添加到content中
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if(mChufangFragment != null){
            mFragmentTransaction.add(R.id.tab_content,mMineFragment,mMineFragment.getTag());
        }
        if(mPingguFragment != null){
            mFragmentTransaction.add(R.id.tab_content,mPingguFragment,mMineFragment.getTag());
        }
        if(mMineFragment != null){
            mFragmentTransaction.add(R.id.tab_content,mChufangFragment,mChufangFragment.getTag());
        }

        mFragmentTransaction.commit();
        */




    }

    @Override
    public void initComponent() {

    }


    @Override
    public void initView() {

        setContentView(R.layout.activity_home);
        tv_title = (TextView) findViewById(R.id.tv_title);


        mChufangFragment = ChufangFragment.newInstance();
        mPingguFragment = PingguFragment.newInstance();
        mMineFragment = MineFragment.newInstance();

        mRadioGroup = (RadioGroup) findViewById(R.id.tabs_rg);
        mFrameLayout = (FrameLayout) findViewById(R.id.tab_content);
        mRbtn_chufang = (RadioButton) findViewById(R.id.tab_rb_chufang);
        mRbtn_pinggu = (RadioButton) findViewById(R.id.tab_rb_pinggu);
        mRbtn_Mine = (RadioButton) findViewById(R.id.tab_rb_mine);

        ActivityUtil.addFragmentToActivity(mFragmentManager,mMineFragment,R.id.tab_content);
        ActivityUtil.addFragmentToActivity(mFragmentManager,mPingguFragment,R.id.tab_content);
        ActivityUtil.addFragmentToActivity(mFragmentManager,mChufangFragment,R.id.tab_content);

    }

    @Override
    public void initListener() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        mFragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(mFragmentTransaction);

        switch (checkedId){
            case R.id.tab_rb_chufang:
                setTitle(Constant.CHUFANG_TITLE);
                mFragmentTransaction.show(mChufangFragment);
                mChufangFragment.onResume();
                break;
            case R.id.tab_rb_pinggu:
                setTitle(Constant.PINGU_TITLE);
                mFragmentTransaction.show(mPingguFragment);
                mPingguFragment.onResume();
                break;
            case R.id.tab_rb_mine:
                setTitle(Constant.MINE_TITLE);
                mFragmentTransaction.show(mMineFragment);
                mMineFragment.onResume();
                break;
        }

        mFragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction ft){


        if(mChufangFragment != null){
            ft.hide(checkNotNull(mChufangFragment));
            mChufangFragment.onHiddenChanged(true);
        }
        if(mPingguFragment != null){
            ft.hide(checkNotNull(mPingguFragment));
            mPingguFragment.onHiddenChanged(true);
        }
        if(mMineFragment != null){
            ft.hide(checkNotNull(mMineFragment));
            mMineFragment.onHiddenChanged(true);
        }

    }

}