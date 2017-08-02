package com.wuzhichao.test1.mvp.presenter.homePresenter;

import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.contract.homeContract.ChufangFragmentContract;
import com.wuzhichao.test1.mvp.data.Source.DataRepository;
import com.wuzhichao.test1.mvp.data.Source.DataSource;
import com.wuzhichao.test1.util.Log;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/6/30.
 */

public class ChufangFragmentPresenter implements ChufangFragmentContract.Presenter{

    public static String TAG="ChufangPresenter";

    private final DataRepository mDataRepository;

    private final ChufangFragmentContract.View mChufangView;

    private boolean mFirstLoad = true;


    @Inject
    ChufangFragmentPresenter(DataRepository dataRepository, ChufangFragmentContract.View view){
        mDataRepository = dataRepository;
        mChufangView = view;
        Log.d(TAG,this.toString()+"处方Presenter实例化成功！");

    }


    @Override
    public void start() {
        loadProjectsData(mChufangView.getCurrentIndicatorIndex());
    }




    @Override
    public void loadProjectsData(final int index) {



        mDataRepository.getProjects(index, new DataSource.LoadDataCallback<ProjectBean>() {
            @Override
            public void onDataLoaded(List<ProjectBean> data) {
                mChufangView.updateProjectData(index,data);
            }

            @Override
            public void onDataNotAvailable() {
                mChufangView.showLoadError();
            }
        });
    }




    @Override
    public void RefreshProjectsData(final int index) {
        mDataRepository.refreshProjects(index, new DataSource.LoadDataCallback<ProjectBean>() {
            @Override
            public void onDataLoaded(List<ProjectBean> data) {
                mChufangView.updateProjectData(index,data);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(TAG,"获取数据失败。");
            }
        });
    }

}
