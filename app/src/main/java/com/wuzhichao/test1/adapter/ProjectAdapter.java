package com.wuzhichao.test1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuzhichao.test1.R;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.util.Log;

import javax.inject.Inject;

/**
 * Created by 黑客 on 2017/7/18.
 */

public class ProjectAdapter extends MyAdapter<ProjectBean>{


    public static final String TAG = "ProjectAdapter";

    @Inject
    public ProjectAdapter(@ActivityContext Context context, ImageLoader imageLoader){
        super(context, imageLoader);
        Log.d(TAG,"projectAdapter 创建成功。");
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_video_item,null);
            holder.iv_project = (ImageView) convertView.findViewById(R.id.iv_project);
            holder.tv_project_name = (TextView) convertView.findViewById(R.id.tv_project_name);
            holder.tv_project_num = (TextView) convertView.findViewById(R.id.tv_project_num);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(!mData.isEmpty()){
            ProjectBean projectBean = mData.get(position);
            holder.tv_project_name.setText(projectBean.getProject_name());
            holder.tv_project_num.setText(projectBean.getProject_num() + "");
            if(projectBean.getProject_img() == null || projectBean.getProject_img() == ""){
                holder.iv_project.setBackgroundResource(R.drawable.img1);
            }else{
                mImageLoader.displayImage(projectBean.getProject_img(),holder.iv_project,mDisplayImageOptions);
            }
        }

        Log.d(TAG,"返回每个item_view");
        return convertView;
    }

    public final class ViewHolder{
        public ImageView iv_project;
        public TextView tv_project_name;
        public TextView tv_project_num;
    }

}
