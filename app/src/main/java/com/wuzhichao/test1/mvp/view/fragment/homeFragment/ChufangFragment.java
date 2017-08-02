package com.wuzhichao.test1.mvp.view.fragment.homeFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;
import com.wuzhichao.test1.R;
import com.wuzhichao.test1.adapter.MyFragmentPagerAdapter;
import com.wuzhichao.test1.dragger.components.ChufangFragmentComponent;
import com.wuzhichao.test1.dragger.components.DaggerChufangFragmentComponent;
import com.wuzhichao.test1.dragger.modules.ChufangFragmentModule;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.contract.homeContract.ChufangFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.homePresenter.ChufangFragmentPresenter;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by 黑客 on 2017/6/30.
 */

public class ChufangFragment extends BaseFragment implements ChufangFragmentContract.View{


    @Inject
    ChufangFragmentPresenter mPresenter;

    @Inject
    MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    @Inject
    List<VpSimpleFragment> mVpSimpleFragments;

    @Inject
    @Named("TabTitle")
    public List<String> mListTabTitle;


    private ChufangFragmentComponent mChufangFragmentComponent;

    private ViewPager mViewPager;

    private TabPageIndicator mTabPageIndicator;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_chufang,container,false);

        initView();
        initListener();
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    public static ChufangFragment newInstance() {
        return new ChufangFragment();
    }



    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    @Override
    public void showLoadError() {
        Toast.makeText(mContext,"加载失败",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void initView() {

        for(int i = 0 ; i < mVpSimpleFragments.size(); i++){
            mVpSimpleFragments.get(i).setPresenter(mPresenter);
        }

        mViewPager = (ViewPager) mView.findViewById(R.id.cf_pager);
        mTabPageIndicator = (TabPageIndicator) mView.findViewById(R.id.cf_indicator);

        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mTabPageIndicator.setViewPager(mViewPager);
        mTabPageIndicator.setCurrentItem(0);

    }

    @Override
    public void initComponent() {
        mChufangFragmentComponent = DaggerChufangFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .chufangFragmentModule(new ChufangFragmentModule(this,7))
                .build();

        mChufangFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public int getCurrentIndicatorIndex() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public void updateProjectData(int index, List<ProjectBean> data) {
        if(!mVpSimpleFragments.isEmpty()){
            mVpSimpleFragments.get(index).updateProjectData(index,data);
        }
    }


    public ChufangFragmentComponent getmChufangFragmentComponent(){
        return mChufangFragmentComponent;
    }
}
