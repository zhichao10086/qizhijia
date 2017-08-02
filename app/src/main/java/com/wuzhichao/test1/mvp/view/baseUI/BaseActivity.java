package com.wuzhichao.test1.mvp.view.baseUI;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wuzhichao.test1.MyApp;
import com.wuzhichao.test1.R;
import com.wuzhichao.test1.dragger.components.ActivityComponent;
import com.wuzhichao.test1.dragger.components.DaggerActivityComponent;
import com.wuzhichao.test1.dragger.modules.ActivityModule;
import com.wuzhichao.test1.dragger.qualifier.ActivityContext;
import com.wuzhichao.test1.mvp.listener.OnBottomDragListener;
import com.wuzhichao.test1.util.StringUtil;

import org.xutils.ViewInjector;

import javax.inject.Inject;
import javax.inject.Named;

import static com.wuzhichao.test1.config.Constant.ACTION_EXIT_APP;


/**
 * Created by 黑客 on 2017/6/30.
 */


/**基础android.support.v4.app.FragmentActivity，通过继承可获取或使用 里面创建的 组件 和 方法
 * *onFling内控制左右滑动手势操作范围，可自定义
 * @author Lemon
 * @see ActivityPresenter#getActivity
 * @see #context
 * @see #view
 * @see #fragmentManager
 * @see #setContentView
 * @see #runUiThread
 * @see #runThread
 * @see #onDestroy
 * @use extends BaseActivity, 具体参考 .DemoActivity 和 .DemoFragmentActivity
 */
public abstract class BaseActivity extends FragmentActivity implements GestureDetector.OnGestureListener {
    private static final String TAG = "BaseActivity";


    protected ActivityComponent mActivityComponent;

    /**
     * 该Activity实例，命名为context是因为大部分方法都只需要context，写成context使用更方便
     * @warn 不能在子类中创建
     */
    @Inject
    @ActivityContext
    public Context mContext;

    @Inject
    public ViewInjector mViewInjector;


    /**
     * 该Activity的界面，即contentView
     * @warn 不能在子类中创建
     */
    protected View view = null;
    /**
     * 布局解释器
     * @warn 不能在子类中创建
     */
    protected LayoutInflater inflater = null;
    /**
     * Fragment管理器
     * @warn 不能在子类中创建
     */
    @Inject
    @Named("ActivityFragmentManager")
    public FragmentManager mFragmentManager;

    protected FragmentTransaction mFragmentTransaction;

    private boolean isAlive = false;
    private boolean isRunning = false;

    //标题栏
    public TextView tv_title;//标题
    public Button btn_left;//左边按钮
    public Button btn_right;//右边按钮
    public View layout_title;

    public View fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(((MyApp)getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);

        mViewInjector.inject(this);

        Log.d(this.TAG,"activityComponent注入");
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        isAlive = true;
        //mFragmentManager = getSupportFragmentManager();

        inflater = getLayoutInflater();


        BaseBroadcastReceiver.register(mContext, receiver, ACTION_EXIT_APP);
    }

    /**
     * 默认标题TextView，layout.xml中用@id/tvBaseTitle绑定。子Activity内调用autoSetTitle方法 会优先使用INTENT_TITLE
     * @see #autoSetTitle
     * @warn 如果子Activity的layout中没有android:id="@id/tvBaseTitle"的TextView，使用前必须在子Activity中赋值
     */
    @Nullable
    protected TextView tvBaseTitle;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        /*
        // 状态栏沉浸，4.4+生效 <<<<<<<<<<<<<<<<<
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.topbar_bg);//状态背景色，可传drawable资源
        // 状态栏沉浸，4.4+生效 >>>>>>>>>>>>>>>>>

        //tvBaseTitle = (TextView) findViewById(R.id.tvBaseTitle);//绑定默认标题TextView
        */
    }

    //底部滑动实现同点击标题栏左右按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private OnBottomDragListener onBottomDragListener;
    private GestureDetector gestureDetector;
    /**设置该Activity界面布局，并设置底部左右滑动手势监听
     * @param layoutResID
     * @param listener
     * @use 在子类中
     * *1.onCreate中super.onCreate后setContentView(layoutResID, this);
     * *2.重写onDragBottom方法并实现滑动事件处理
     * *3.在导航栏左右按钮的onClick事件中调用onDragBottom方法
     */
    public void setContentView(int layoutResID, OnBottomDragListener listener) {
        setContentView(layoutResID);

        onBottomDragListener = listener;
        gestureDetector = new GestureDetector(this, this);//初始化手势监听类

        view = inflater.inflate(layoutResID, null);
        view.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    //底部滑动实现同点击标题栏左右按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /**
     * 用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
     */
    protected Intent intent = null;

    /**
     * 退出时之前的界面进入动画,可在finish();前通过改变它的值来改变动画效果
     */
    protected int enterAnim = R.anim.fade;
    /**
     * 退出时该界面动画,可在finish();前通过改变它的值来改变动画效果
     */
    protected int exitAnim = R.anim.right_push_out;

    //	/**通过id查找并获取控件，使用时不需要强转
    //	 * @param id
    //	 * @return
    //	 */
    //	@SuppressWarnings("unchecked")
    //	public <V extends View> V findViewById(int id) {
    //		return (V) view.findViewById(id);
    //	}
    /**通过id查找并获取控件，并setOnClickListener
     * @param id
     * @param l
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int id, View.OnClickListener l) {
        V v = (V) findViewById(id);
        v.setOnClickListener(l);
        return v;
    }


    //显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 进度弹窗
     */
    protected ProgressDialog progressDialog = null;



    //启动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**打开新的Activity,不传数据
     * @param intent
     */
    public void toAcvity(Intent intent) {
        this.startActivity(intent);
    }

    /**打开新的Activity,带回传
     * @param intent
     * @param requestCode
     */
    public void toActivity(final Intent intent, final int requestCode) {
        startActivityForResult(intent,requestCode);
    }
    //启动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    /**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     * @param stringResId
     */
    public void showShortToast(int stringResId) {
        try {
            showShortToast(mContext.getResources().getString(stringResId));
        } catch (Exception e) {
            Log.e(TAG, "showShortToast  context.getResources().getString(resId)" +
                    " >>  catch (Exception e) {" + e.getMessage());
        }
    }
    /**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     * @param string
     */
    public void showShortToast(String string) {
        showShortToast(string, false);
    }
    /**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     * @param string
     * @param isForceDismissProgressDialog
     */
    public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {

    }
    //show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    public final boolean isAlive() {
        return isAlive && mContext != null;// & ! isFinishing();导致finish，onDestroy内runUiThread不可用
    }

    @Override
    public void finish() {
        super.finish();//必须写在最前才能显示自定义动画

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        Log.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        Log.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    /**销毁并回收内存
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    protected void onDestroy() {
        Log.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
        BaseBroadcastReceiver.unregister(mContext, receiver);

        if (view != null) {
            try {
                view.destroyDrawingCache();
            } catch (Exception e) {
                Log.w(TAG, "onDestroy  try { view.destroyDrawingCache();" +
                        " >> } catch (Exception e) {\n" + e.getMessage());
            }
        }

        isAlive = false;
        isRunning = false;
        super.onDestroy();

        inflater = null;
        view = null;
        tvBaseTitle = null;

        //fragmentManager = null;
        progressDialog = null;
        intent = null;

        Log.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }



    private BroadcastReceiver receiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent == null ? null : intent.getAction();
            if (isAlive() == false || StringUtil.isNotEmpty(action, true) == false) {
                Log.e(TAG, "receiver.onReceive  isAlive() == false" +
                        " || StringUtil.isNotEmpty(action, true) == false >> return;");
                return;
            }

            if (ACTION_EXIT_APP.equals(action)) {
                finish();
            }
        }
    };



    //手机返回键和菜单键实现同点击标题栏左右按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private boolean isOnKeyLongPress = false;
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        isOnKeyLongPress = true;
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (isOnKeyLongPress) {
            isOnKeyLongPress = false;
            return true;
        }

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (onBottomDragListener != null) {
                    onBottomDragListener.onDragBottom(false);
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_MENU:
                if (onBottomDragListener != null) {
                    onBottomDragListener.onDragBottom(true);
                    return true;
                }
                break;
            default:
                break;
        }

        return super.onKeyUp(keyCode, event);
    }

    //手机返回键和菜单键实现同点击标题栏左右按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //底部滑动实现同点击标题栏左右按钮效果<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        //		/*原来实现全局滑动返回的代码，OnFinishListener已删除，可以自己写一个或者
        //		 * 用onBottomDragListener.onDragBottom(false);代替onFinishListener.finish();**/
        //		if (onFinishListener != null) {
        //
        //			float maxDragHeight = getResources().getDimension(R.dimen.page_drag_max_height);
        //			float distanceY = e2.getRawY() - e1.getRawY();
        //			if (distanceY < maxDragHeight && distanceY > - maxDragHeight) {
        //
        //				float minDragWidth = getResources().getDimension(R.dimen.page_drag_min_width);
        //				float distanceX = e2.getRawX() - e1.getRawX();
        //				if (distanceX > minDragWidth) {
        //					onFinishListener.finish();
        //					return true;
        //				}
        //			}
        //		}


        return false;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }


    public void setTitle(String title){
        if(tv_title != null){
            tv_title.setText(title);
        }
    }

    public void setTitle(String title, Color color){
        if(layout_title != null){
            layout_title.setBackgroundColor(Color.parseColor(color.toString()));
        }
        if(tv_title != null){
            tv_title.setText(title);
        }

    }

    //底部滑动实现同点击标题栏左右按钮效果>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public boolean isActive(){
        return isRunning;
    }



    //Component的初始化<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public abstract void initComponent();


    //Component的初始化>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //view的初始化<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public abstract void initView();


    //view的初始化>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //listener的初始化<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public abstract void initListener();


    //listener的初始化>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public ActivityComponent getmActivityComponent(){
        return mActivityComponent;
    }

}