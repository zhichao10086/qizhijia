package com.wuzhichao.test1.mvp.bean;

import android.content.ContentValues;

import com.wuzhichao.test1.mvp.data.Source.local.DataPersistnetContract;

import java.io.Serializable;

/**
 * Created by 黑客 on 2017/7/17.
 */

public class ProjectBean implements Serializable{

    private static final long serialVersionUID = 1L;
    int project_id;
    String project_name;
    int project_kind_id;
    String project_kind_name;
    int project_num;
    String project_img;

    public ProjectBean(int project_id, String project_name, int project_kind_id, String project_kind_name, int project_num, String project_img) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_kind_id = project_kind_id;
        this.project_kind_name = project_kind_name;
        this.project_num = project_num;
        this.project_img = project_img;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getProject_kind_id() {
        return project_kind_id;
    }

    public void setProject_kind_id(int project_kind_id) {
        this.project_kind_id = project_kind_id;
    }

    public String getProject_kind_name() {
        return project_kind_name;
    }

    public void setProject_kind_name(String project_kind_name) {
        this.project_kind_name = project_kind_name;
    }

    public int getProject_num() {
        return project_num;
    }

    public void setProject_num(int project_num) {
        this.project_num = project_num;
    }

    public String getProject_img() {
        return project_img;
    }

    public void setProject_img(String project_img) {
        this.project_img = project_img;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", project_kind_id=" + project_kind_id +
                ", project_kind_name='" + project_kind_name + '\'' +
                ", project_num=" + project_num +
                ", project_img='" + project_img + '\'' +
                '}';
    }

    public ContentValues toContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_ID,project_id);
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_NAME,project_name);
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECT_KIND_ID,project_kind_id);
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_KIND_NAME,project_kind_name);
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_NUM,project_num);
        contentValues.put(DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_IMG,project_img);

        return contentValues;
    }
}
