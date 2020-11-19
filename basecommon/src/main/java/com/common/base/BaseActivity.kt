package com.common.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.common.library.eventbus.EventBus

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.remove("android:support:fragments")
        super.onCreate(savedInstanceState)
        setContentView(layout)
        attachView()
        initStatusBar()
        initViewIds()
        initView()
        try {
            initCache()
        } catch (ignored: Exception) {
        }
        initData(savedInstanceState)
        initListener()
        if (useEventBus()) EventBus.getDefault().register(this)
    }

    abstract val layout: Int
    fun attachView() {}
    fun initStatusBar() {}
    abstract fun initViewIds()
    abstract fun initView()
    fun initCache() {}
    abstract fun initData(savedInstanceState: Bundle?)
    open fun initListener() {}
    /**
     * 是否使用eventbus
     * 默认不使用注册
     *
     * @return: false
     */
    fun useEventBus(): Boolean {
        return false
    }

    /**
     * 是否允许多指触控
     * 默认不允许
     *
     * @return: true
     */
    fun useMorePoints(): Boolean {
        return false
    }

    /**
     * 是否允许当前点击输入框,再次点击空白处,隐藏软键盘
     * 默认允许
     * 可实现该方法返回false 代表不允许
     * @return true
     */
    fun useHiddenSoftInput(): Boolean {
        return true
    }

    /**
     * 分发处理activity的事件
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (!useMorePoints()) { //不允许多指触控, 默认允许
            if (ev.pointerCount > 1) {
                return true
            }
        }
        return if (useHiddenSoftInput()) { //是否启用
            if (ev.action == MotionEvent.ACTION_DOWN) {
                val v = currentFocus
                //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
                if (isShouldHideInput(v, ev)) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v!!.windowToken, 0)
                }
                return super.dispatchTouchEvent(ev)
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            try {
                if (window.superDispatchTouchEvent(ev)) {
                    return true
                }
            } catch (ignored: Exception) {
            }
            onTouchEvent(ev)
        } else {
            super.dispatchTouchEvent(ev)
        }
    }

    /**
     * 是否隐藏输入
     *
     * @param v
     * @param event
     * @return
     */
    fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return (event.x <= left || event.x >= right
                    || event.y <= top || event.y >= bottom)
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) EventBus.getDefault().unregister(this)
    }
}