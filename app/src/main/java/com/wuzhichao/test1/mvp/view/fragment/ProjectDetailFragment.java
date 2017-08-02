package com.wuzhichao.test1.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.dragger.components.DaggerProjectDetailFragmentComponent;
import com.wuzhichao.test1.dragger.components.ProjectDetailFragmentComponent;
import com.wuzhichao.test1.dragger.modules.ProjectDetailFragmentModule;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.contract.homeContract.ProjectDetailFragmentContract;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.presenter.homePresenter.ProjectDetialFragmentPresenter;
import com.wuzhichao.test1.mvp.view.baseUI.BaseFragment;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/28.
 */

public class ProjectDetailFragment extends BaseFragment implements ProjectDetailFragmentContract.View {


    private ProjectDetailFragmentComponent mProjectDetailFragmentComponent;


    @Inject
    ProjectDetialFragmentPresenter mPresenter;

    ProjectBean mProjectBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProjectBean = (ProjectBean)this.getArguments().getSerializable("projectBean");

        initComponent();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_project_detial,container,false);

        initView();
        initListener();
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    public static ProjectDetailFragment newInstance(ProjectBean projectBean) {

        ProjectDetailFragment projectDetailFragment = new ProjectDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("projectBean",projectBean);
        projectDetailFragment.setArguments(bundle);

        return projectDetailFragment;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    @Override
    public void initView() {

    }

    @Override
    public void initComponent() {
        mProjectDetailFragmentComponent = DaggerProjectDetailFragmentComponent.builder()
                .fragmentComponent(this.getmFragmentComponent())
                .projectDetailFragmentModule(new ProjectDetailFragmentModule(this))
                .build();

        mProjectDetailFragmentComponent.inject(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public ProjectBean getCurrentProject() {
        return mProjectBean;
    }
}
