package com.wuzhichao.test1.mvp.view.fragment.homeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wuzhichao.test1.R;
import com.wuzhichao.test1.adapter.ProjectAdapter;
import com.wuzhichao.test1.dragger.components.ChufangFragmentComponent;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.contract.homeContract.ChufangFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.homePresenter.ChufangFragmentPresenter;
import com.wuzhichao.test1.mvp.view.activity.ProjectDetailActivity;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;
import com.wuzhichao.test1.util.Log;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/8.
 */

public class VpSimpleFragment extends BaseFragment implements ChufangFragmentContract.View{

    private int mIndex;
    public static final String BUNDLE_TITLE="index";
    public static final String TAG = "VpSimpleFragment";

    private PullToRefreshListView mPullToRefreshListView;

    private ListView mListView;

    ChufangFragmentPresenter mPresenter;

    @Inject
    ProjectAdapter mProjectAdapter;

    private ChufangFragmentComponent mChufangFragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initComponent();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            mIndex=bundle.getInt(BUNDLE_TITLE);
        }
        mView = inflater.inflate(R.layout.fragment_video_list,null);

        initView();
        initListener();

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }



    public static VpSimpleFragment newInstance(int index){
        Bundle bundle=new Bundle();
        bundle.putInt(BUNDLE_TITLE,index);

        VpSimpleFragment fragment= new VpSimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (ChufangFragmentPresenter)presenter;
        Log.d(TAG,"chufangPresenter设置成功");
    }


    @Override
    public void initView() {


        mPullToRefreshListView = (PullToRefreshListView) mView.findViewById(R.id.pull_refresh_list);


        mListView = mPullToRefreshListView.getRefreshableView();

        //mPullToRefreshListView.setAdapter(mProjectAdapter);

        //mListView.setVisibility(View.VISIBLE);

        mListView.setAdapter(mProjectAdapter);

    }

    @Override
    public void initComponent() {
        mChufangFragmentComponent = ((ChufangFragment)this.getParentFragment()).getmChufangFragmentComponent();
        mChufangFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.d(TAG,"开始刷新数据。");
                mPresenter.RefreshProjectsData(getCurrentIndicatorIndex());
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectBean projectBean = (ProjectBean)parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(),ProjectDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("projectBean",projectBean);
                intent.putExtra("data",bundle);
                toAcvity(intent);
            }
        });
    }

    @Override
    public void showLoadError() {

    }

    @Override
    public int getCurrentIndicatorIndex() {

        Bundle bundle = this.getArguments();
        return bundle.getInt(BUNDLE_TITLE);
    }

    @Override
    public void updateProjectData(int index, List<ProjectBean> data) {
        mProjectAdapter.setmData(data);
        mProjectAdapter.notifyDataSetChanged();
        mPullToRefreshListView.onRefreshComplete();
        Log.d(TAG,"刷新数据完成。\n" + data.toString());
    }


}
