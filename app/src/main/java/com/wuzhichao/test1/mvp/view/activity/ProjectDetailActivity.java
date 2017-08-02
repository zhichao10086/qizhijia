package com.wuzhichao.test1.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.mvp.view.baseUI.BaseActivity;
import com.wuzhichao.test1.mvp.view.fragment.ProjectDetailFragment;
import com.wuzhichao.test1.util.ActivityUtil;

/**
 * Created by 黑客 on 2017/7/23.
 */

public class ProjectDetailActivity extends BaseActivity {

    private ProjectDetailFragment mProjectDetailFragment;

    private ProjectBean mProjectBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        mProjectBean = (ProjectBean)bundle.getSerializable("projectBean");

        initView();

        initComponent();

        initListener();
    }


    @Override
    public void initComponent() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_common);

        setTitle(mProjectBean.getProject_name());

        btn_left = (Button)findViewById(R.id.btn_left);

        mProjectDetailFragment = mProjectDetailFragment.newInstance(mProjectBean);

        ActivityUtil.addFragmentToActivity(mFragmentManager, mProjectDetailFragment,R.id.contentFrame);

    }

    @Override
    public void initListener() {
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
