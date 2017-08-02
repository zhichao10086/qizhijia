package com.wuzhichao.test1.mvp.bean;

/**
 * Created by 黑客 on 2017/7/17.
 */

public class VideoBean {
    private int video_id;
    private int project_id;
    private String video_address;
    private int video_sumtime;
    private int video_size;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getVideo_address() {
        return video_address;
    }

    public void setVideo_address(String video_address) {
        this.video_address = video_address;
    }

    public int getVideo_sumtime() {
        return video_sumtime;
    }

    public void setVideo_sumtime(int video_sumtime) {
        this.video_sumtime = video_sumtime;
    }

    public int getVideo_size() {
        return video_size;
    }

    public void setVideo_size(int video_size) {
        this.video_size = video_size;
    }
}
