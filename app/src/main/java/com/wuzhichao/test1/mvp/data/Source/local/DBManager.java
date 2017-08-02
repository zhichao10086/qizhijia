package com.wuzhichao.test1.mvp.data.Source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wuzhichao.test1.dragger.qualifier.AppContext;
import com.wuzhichao.test1.mvp.bean.ProjectBean;
import com.wuzhichao.test1.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黑客 on 2017/7/22.
 */

public class DBManager {

    public static final String TAG = "DBManager";

    private DataDBhelper mDataDBhelper;
    private SQLiteDatabase db;

    public DBManager(@AppContext Context context){
        mDataDBhelper = new DataDBhelper(context);
        db = mDataDBhelper.getWritableDatabase();
    }

    public boolean queryProject(int pro_id){
        String[] selectionArgs = {Integer.toString(pro_id)};
        Cursor c = db.rawQuery("select * from " + DataPersistnetContract.Project.TABLE_NAME + " where project_id = ?",selectionArgs);

        Log.d(TAG,"queryProject 执行完毕。");
        if(c.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }


    public List<ProjectBean> queryProjects(int pro_kind_id){
        List<ProjectBean> projectBeanList = new ArrayList<ProjectBean>();


        String[] projection = {
                DataPersistnetContract.Project.COLUMN_NAME_PROJECT_ID,
                DataPersistnetContract.Project.COLUMN_NAME_PROJECT_NAME,
                DataPersistnetContract.Project.COLUMN_NAME_PROJECT_KIND_ID,
                DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_KIND_NAME,
                DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_NUM,
                DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_IMG
        };

        String selection = DataPersistnetContract.Project.COLUMN_NAME_PROJECT_KIND_ID + " =?";

        String[] selectionArgs = {
                Integer.toString(pro_kind_id)
        };

        Cursor c = db.query(
                DataPersistnetContract.Project.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(c != null && c.getCount() > 0){
            while(c.moveToNext()){
                int project_id = c.getInt(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_ID));
                String project_name = c.getString(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_NAME));
                int project_kind_id1 = c.getInt(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_KIND_ID));
                String project_kind_name = c.getString(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_KIND_NAME));
                int project_num = c.getInt(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_NUM));
                String project_img = c.getString(c.getColumnIndexOrThrow(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_IMG));
                ProjectBean projectBean = new ProjectBean(project_id,project_name,project_kind_id1,
                        project_kind_name,project_num,project_img);
                projectBeanList.add(projectBean);
            }
        }

        Log.d(TAG,"queryProjects 执行完毕。");

        return projectBeanList;

    }

    public void updateProjects(List<ProjectBean> data){
        for(int i = 0 ; i < data.size() ; i++){

            ProjectBean projectBean = data.get(i);

            if(queryProject(projectBean.getProject_id())){
                updateProject(projectBean);
            }else{
                insertProject(projectBean);
            }
        }
        Log.d(TAG,"updateProjects 执行完毕。");
    }

    public void updateProject(ProjectBean data){
        ContentValues contentValues = data.toContentValues();

        db.update(
                DataPersistnetContract.Project.TABLE_NAME,
                contentValues,
                "proejct_id = ?",
                new String[]{Integer.toString(data.getProject_id())});

        Log.d(TAG,"updateProject 执行完毕。");
    }

    public void insertProject(ProjectBean data){
        db.insert(DataPersistnetContract.Project.TABLE_NAME,null,data.toContentValues());
        Log.d(TAG,"insertProject 执行完毕.");
    }

    public void deleteProject(ProjectBean data){

    }


}
