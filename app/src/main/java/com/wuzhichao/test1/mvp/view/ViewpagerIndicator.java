package com.wuzhichao.test1.mvp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuzhichao.test1.R;
import com.wuzhichao.test1.util.Log;

import java.util.List;

/**
 * Created by 黑客 on 2017/7/6.
 */

public class ViewpagerIndicator extends LinearLayout {

    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;
    private int mTriangleHight;
    private static final float DEFAULT_TRIANGLEWIDTH=1/6f;
    private int mInitTranslationX;
    private int mTranslationX;
    private int mTabVisibleCount;
    private static final int COUNT_DEFAULT_TAB=4;
    private static final int COLOR_TEXT_NORMAL=0XFF808080;
    private static final int COLOR_TEXT_HEIGHLIGHT=0XFFFFFFFF;
    private ViewPager mViewpager;

    private List<String> mTitles;


    public ViewpagerIndicator(Context context) {
        this(context,null);
    }

    public ViewpagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取可见Tab的数量
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewpagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.ViewpagerIndicator_visible_tab_conut,COUNT_DEFAULT_TAB);
        if (mTabVisibleCount<0){
            mTabVisibleCount=COUNT_DEFAULT_TAB;
        }

        a.recycle();
        //初始化画笔
        mPaint=new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint. setPathEffect(new CornerPathEffect(3));

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount<0) return;
        for (int i=0;i<cCount;i++){
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight=0;
            lp.width=getScreenWidth()/mTabVisibleCount;
            view.setLayoutParams(lp);
        }
        setClickEvent();
    }
    /*
    *
    * 获得屏幕宽度
    * */
    private int getScreenWidth() {
        WindowManager vm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        vm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mInitTranslationX+mTranslationX,getHeight()+3);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth= (int) (w/mTabVisibleCount*DEFAULT_TRIANGLEWIDTH);
        mInitTranslationX= (w/mTabVisibleCount/2-mTriangleWidth/2);
        initTriangle();
    }

    /*
    *  初始化三角形
    * */
    private void initTriangle() {
        mTriangleHight = (int) (mTriangleWidth/2*Math.tan(Math.PI/4));
        mPath=new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleHight);
        mPath.close();
    }
    /*
    * 三角形跟随手指移动
    * */
    public void scroll(int position, float offset) {
        Log.i("test","scroll");
        int tabWidth=getWidth()/mTabVisibleCount;
        mTranslationX = (int) (offset*tabWidth+position*tabWidth);
        //容器移动，当tab移动到最后一个时移动容器
        if (position>=(mTabVisibleCount-2)&&offset>0&&getChildCount()>mTabVisibleCount){
            this.scrollTo((position-(mTabVisibleCount-2))*tabWidth+(int)(offset*tabWidth),0);
        }
        invalidate();
    }

    /*
* 根据title创建tab
* */
    public void setTabItems(List<String> titles){
        if (titles!=null&&titles.size()>0){
            this.removeAllViews();
            for (String title:titles) {
                addView(generateTextView(title));
            }
        }
        setClickEvent();
    }



    /*
    * 创建TextView*/
    private View generateTextView(String title) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width=getScreenWidth()/mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);
        return tv;
    }
    //*
// 设置可见tab数量*/
    public void setVisibleTabCount(int count){
        if (count>0)
            mTabVisibleCount=count;
    }
    /*
    * PagerOnChangedListener接口
    * */
    public interface PagerOnChangedListener{
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
    }
    public PagerOnChangedListener mListenner;

    public  void setOnPageChangeListener(PagerOnChangedListener listenner){
        this.mListenner=listenner;
    }

    /*
    * 设置关联的ViewPager*/
    public void setViewPager(ViewPager viewpager,int pos){

        mViewpager=viewpager;
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("test","addOnPageChangeListener");
                scroll(position,positionOffset);
                if (mListenner!=null){
                    mListenner.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                HeighlightTextView(position);
                if (mListenner!=null){
                    mListenner.onPageSelected(position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListenner!=null){
                    mListenner.onPageScrollStateChanged(state);
                }
            }
        });
        mViewpager.setCurrentItem(pos);
        HeighlightTextView (pos);
    }
    /*
    * 重置tab文本颜色*/
    private void resertTextViewColor (){
        for (int i=0;i<getChildCount();i++){
            View view=getChildAt(i);
            if (view instanceof TextView){
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }
    /*
    *  设置Tab文本高亮
    * */
    private void HeighlightTextView(int pos){
        resertTextViewColor ();
        View view=getChildAt(pos);
        if (view instanceof TextView){
            ((TextView) view).setTextColor(COLOR_TEXT_HEIGHLIGHT);
        }
    }

    /*
    * 设置tab点击事件*/
    private void setClickEvent(){
        int cCount=getChildCount();
        for (int i=0;i<cCount;i++){
            final int j=i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewpager.setCurrentItem(j);
                }
            });
        }
    }
}
