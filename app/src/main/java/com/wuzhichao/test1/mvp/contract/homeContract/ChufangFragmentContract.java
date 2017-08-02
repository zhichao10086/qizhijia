package com.wuzhichao.test1.mvp.contract.homeContract;

import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.view.BaseView;

import java.util.List;

/**
 * Created by 黑客 on 2017/6/30.
 */

public interface ChufangFragmentContract {
    interface View extends BaseView{

        void showLoadError();
        /*
        获得当前的indexFragment
         */
        int getCurrentIndicatorIndex();

        void updateProjectData(int index,List<ProjectBean> data);

    }

    interface Presenter extends BasePresenter {
        /*

         */
        void loadProjectsData(int index);


        void RefreshProjectsData(int index);

    }
}
