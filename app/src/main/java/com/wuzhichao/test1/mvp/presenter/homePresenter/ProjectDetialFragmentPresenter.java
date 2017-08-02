package com.wuzhichao.test1.mvp.presenter.homePresenter;

import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.contract.homeContract.ProjectDetailFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/28.
 */

public class ProjectDetialFragmentPresenter implements ProjectDetailFragmentContract.Presenter {

    public static String TAG="ProjectDetialPresenter";

    private final DataRepository mDataRepository;

    private final ProjectDetailFragmentContract.View mView;

    @Inject
    public ProjectDetialFragmentPresenter(DataRepository mDataRepository, ProjectDetailFragmentContract.View mView) {
        this.mDataRepository = mDataRepository;
        this.mView = mView;
    }


    @Override
    public void start() {
        ProjectBean projectBean = mView.getCurrentProject();
        loadProjectData(projectBean.getProject_id());
        loadVideosData(projectBean.getProject_id());
    }

    @Override
    public void loadProjectData(int project_id) {

    }

    @Override
    public void loadVideosData(int project_id) {

    }
}
