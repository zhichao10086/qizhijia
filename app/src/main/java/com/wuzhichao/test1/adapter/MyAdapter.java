package com.wuzhichao.test1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuzhichao.test1.R;
import com.wuzhichao.test1.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黑客 on 2017/7/18.
 */

public abstract class MyAdapter<T> extends BaseAdapter {

    public static String TAG="MyAdapter";

    public Context mContext;

    public LayoutInflater mInflater;

    public List<T> mData;

    public final ImageLoader mImageLoader;

    public DisplayImageOptions mDisplayImageOptions;

    public MyAdapter(Context context, ImageLoader mImageLoader){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mImageLoader = mImageLoader;

        mData = new ArrayList<T>();

        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.pic_default)
                .showImageOnFail(R.drawable.pic_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


    }


    @Override
    public int getCount() {
        if(mData != null){
            return mData.size();
        }else{
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<T> getmData() {
        return mData;
    }

    public void setmData(List<T> mData) {
        this.mData = mData;
        Log.d(TAG,"数据设置完成");
    }
}
