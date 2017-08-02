package com.wuzhichao.test1.adapter;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.wuzhichao.test1.mvp.view.fragment.homeFragment.VpSimpleFragment;

import java.util.List;

/**
 * Created by 黑客 on 2017/7/3.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter implements RadioGroup.OnCheckedChangeListener{

    FragmentManager mFragmentManager;

    List<String> mTabTitle;

    List<VpSimpleFragment> mVpSimpleFragments;


    public MyFragmentPagerAdapter(FragmentManager fm,List<String> tabTitle,List<VpSimpleFragment> fragments) {
        super(fm);
        this.mFragmentManager = fm;
        mVpSimpleFragments = fragments;
        mTabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mVpSimpleFragments.get(position);
    }

    @Override
    public int getCount() {
        return mVpSimpleFragments.size();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle.get(position % mTabTitle.size());
    }
}
