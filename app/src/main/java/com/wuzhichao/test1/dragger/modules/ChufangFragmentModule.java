package com.wuzhichao.test1.dragger.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wuzhichao.test1.adapter.MyFragmentPagerAdapter;
import com.wuzhichao.test1.dragger.MyAnnonation.FragmentScoped;
import com.wuzhichao.test1.dragger.qualifier.ChildFragmentManager;
import com.wuzhichao.test1.mvp.contract.homeContract.ChufangFragmentContract;
import com.wuzhichao.test1.mvp.view.fragment.homeFragment.VpSimpleFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 黑客 on 2017/7/8.
 */
@Module
public class ChufangFragmentModule{

    private Fragment mFragment;
    int mTab_count;

    public ChufangFragmentModule(Fragment fragment,int Tab_count){
        mFragment = fragment;
        mTab_count = Tab_count;
    }


    @Provides
    @FragmentScoped
    public MyFragmentPagerAdapter provideFragmentPagerAdapter(@ChildFragmentManager FragmentManager fragmentManager, @Named("TabTitle")List<String> tabTitle, List<VpSimpleFragment> fragments){
        return new MyFragmentPagerAdapter(fragmentManager,tabTitle,fragments);
    }

    @Provides
    @FragmentScoped
    public List<VpSimpleFragment> provideVpSimpleFragments(){

        List<VpSimpleFragment> fragments = new ArrayList<VpSimpleFragment>();
        for (int index = 0; index < mTab_count; index++){
            VpSimpleFragment fragment=VpSimpleFragment.newInstance(index);
            fragments.add(fragment);
        }

        return fragments;
    }

    @Provides
    @FragmentScoped
    @Named("TabTitle")
    public List<String> provideTabTitle(){
        return Arrays.asList ("推荐","肩颈", "脊柱", "骨盆", "下肢", "足部", "上肢");
    }


    @Provides
    public ChufangFragmentContract.View provideChufangContractView(){
        return (ChufangFragmentContract.View) mFragment;
    }

}
