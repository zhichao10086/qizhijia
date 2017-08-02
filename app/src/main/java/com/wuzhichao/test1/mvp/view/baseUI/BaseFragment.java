package com.wuzhichao.test1.mvp.view.baseUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuzhichao.test1.dragger.components.DaggerFragmentComponent;
import com.wuzhichao.test1.dragger.components.FragmentComponent;
import com.wuzhichao.test1.dragger.modules.FragmentModule;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.dragger.qualifier.ChildFragmentManager;
import com.wuzhichao.test1.util.Log;

import org.xutils.ViewInjector;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/3.
 */

public abstract class BaseFragment extends Fragment {

    public static final String TAG="BaseFragment";

    FragmentComponent mFragmentComponent;

    @Inject
    @ActivityContext
    public Context mContext;

    @Inject
    @ChildFragmentManager
    public FragmentManager mFragmentManager;

    @Inject
    public ViewInjector mViewInjector;

    protected View mView;

    protected Intent mIntent;


    protected boolean isRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(((BaseActivity)getActivity()).getmActivityComponent())
                .fragmentModule(new FragmentModule(this))
                .build();

        mFragmentComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d(TAG,"view注入");
        return mViewInjector.inject(this,inflater,container);
    }


    @Override
    public void onStart(){
        Log.d(TAG, "\n onStart <<<<<<<<<<<<<<<<<<<<<<<");
        super.onStart();
        Log.d(TAG, "onStart >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }


    @Override
    public void onResume() {
        Log.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        Log.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        Log.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    /**销毁并回收内存
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
   public void onDestroy() {
        Log.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
        isRunning = false;
        super.onDestroy();

        Log.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }



    public boolean isRunning(){
        return isRunning;
    }



    /**打开新的Activity,不传数据
     * @param intent
     */
    public void toAcvity(Intent intent) {
        startActivity(intent);
    }

    /**打开新的Activity,带回传
     * @param intent
     * @param requestCode
     */
    public void toActivity(final Intent intent, final int requestCode) {
        startActivityForResult(intent,requestCode);
    }

    public FragmentComponent getmFragmentComponent() {
        return mFragmentComponent;
    }

}
