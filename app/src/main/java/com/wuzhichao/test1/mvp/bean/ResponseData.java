package com.wuzhichao.test1.mvp.bean;

import java.util.List;

/**
 * Created by 黑客 on 2017/6/30.
 */

public class ResponseData<T> {

    /**
     * errcode :
     * data : [{"image":"http://niubixing-1252814985.cosgz.myqcloud.com/%E5%9B%BE%E7%89%87/%E8%83%8C%E5%A3%81%E7%AB%99%E7%AB%8B.jpg","follow_count":"24","name":"驼背吧","id":"3","post_count":"32"},{"image":"http://niubixing-1252814985.cosgz.myqcloud.com/%E5%9B%BE%E7%89%87/%E8%83%8C%E5%A3%81%E7%AB%99%E7%AB%8B.jpg","follow_count":"1","name":"健胸吧","id":"2","post_count":"30"},{"image":"http://niubixing-1252814985.cosgz.myqcloud.com/%E5%9B%BE%E7%89%87/%E9%98%BB%E6%8A%97%E5%8F%8C%E4%B8%8B%E5%B7%B4.jpg","follow_count":"47","name":"美臀吧","id":"1","post_count":"1"},{"image":"http://niubixing-1252814985.cosgz.myqcloud.com/%E5%9B%BE%E7%89%87/%E6%8B%89%E4%BC%B8%E8%83%8C%E9%98%94%E8%82%8C.jpg","follow_count":"0","name":"瘦腿吧","id":"10003","post_count":"0"}]
     * success : true
     * message : 获取热门贴吧成功
     */
    private String errcode;
    private List<T> data;
    private boolean success;
    private String message;

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrcode() {
        return errcode;
    }

    public List<T> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {

        String s = null;
        for(int i = 0 ; i < data.size() ;i++){
            s += data.get(i).toString();
        }

        return "ResponseData{" +
                "errcode='" + errcode + '\'' +
                ", data=" + s +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
