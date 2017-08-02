package com.wuzhichao.test1.mvp.contract.homeContract;

import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.presenter.BasePresenter;
import com.wuzhichao.test1.mvp.view.BaseView;

/**
 * Created by 黑客 on 2017/7/28.
 */

public interface ProjectDetailFragmentContract {

    interface View extends BaseView {

        ProjectBean getCurrentProject();

    }

    interface Presenter extends BasePresenter {


        void loadProjectData(int project_id);

        void loadVideosData(int project_id);
    }
}
