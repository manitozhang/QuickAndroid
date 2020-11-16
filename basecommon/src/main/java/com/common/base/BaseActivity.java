package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.common.library.eventbus.EventBus;


/**
 * @Author: https://github.com/manitozhang
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: Activity基类
 */
public abstract class BaseActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            //如果系统回收掉了当前的Activity,但是保留了Fragment
            // 当Activity被重新初始化时,此时,系统保存的Fragment的getActivity为空
            //所以要移除旧的Fragment,重新初始化新的Fragment
            savedInstanceState.remove("android:support:fragments");
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        attachView();
        initStatusBar();
        initViewIds();
        initView();
        try {
            initCache();
        } catch (Exception ignored) {

        }
        initData(savedInstanceState);
        initListener();
        if (useEventBus())
            EventBus.getDefault().register(this);
    }

    public abstract int getLayout();

    public void attachView(){};

    public void initStatusBar(){};

    public abstract void initViewIds();

    public abstract void initView();

    public void initCache() {
    }

    ;

    public abstract void initData(@Nullable Bundle savedInstanceState);

    public void initListener() {
    }

    /**
     * 是否使用eventbus
     * 默认不使用注册
     *
     * @return: false
     */
    public boolean useEventBus() {
        return false;
    }

    /**
     * 是否允许多指触控
     * 默认不允许
     *
     * @return: true
     */
    public boolean useMorePoints() {
        return false;
    }

    /**
     * 是否允许当前点击输入框,再次点击空白处,隐藏软键盘
     * 默认允许
     * 可实现该方法返回false 代表不允许
     * @return true
     */
    public boolean useHiddenSoftInput() {
        return true;
    }

    /**
     * 分发处理activity的事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!useMorePoints()) {//不允许多指触控, 默认允许
            if (ev.getPointerCount() > 1) {
                return true;
            }
        }
        if (useHiddenSoftInput()) {//是否启用
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
                if (isShouldHideInput(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        assert v != null;
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            try {
                if (getWindow().superDispatchTouchEvent(ev)) {
                    return true;
                }
            } catch (Exception ignored) {
            }
            return onTouchEvent(ev);
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * 是否隐藏输入
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }
}
