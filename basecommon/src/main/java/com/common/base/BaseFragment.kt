package com.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.common.library.eventbus.EventBus
import com.common.library.eventbus.Subscribe
import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: Fragment基类
 */
abstract class BaseFragment : Fragment() {
    private var convertView: View? = null
    private var isVisibles = false //当前Fragment是否可见
    private var isInitView = false //是否与View建立起映射关系
    private var isFirstLoad = true //是否是第一次加载数据
    @Subscribe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (useEventBus()) { //如果使用EventBus,请将此方法返回true
            EventBus.getDefault().register(this)
        }
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        convertView = inflater.inflate(layout, container, false)
        if (useLazyFragment()) {
            initViewIds(convertView)
            attachView()
            initArguments()
            try { //捕获一下异常
                initCatch()
            } catch (ignored: Exception) {
            }
            initView(convertView)
            isInitView = true
            lazyLoadData(savedInstanceState)
            initListener()
        }
        return convertView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //不使用懒加载的话
        if (!useLazyFragment()) {
            initViewIds(view)
            attachView()
            initArguments()
            try { //捕获一下异常
                initCatch()
            } catch (ignored: Exception) {
            }
            initView(view)
            initData(savedInstanceState)
            initListener()
        }
    }

    fun attachView() {}
    /**
     * 初始化布局
     *
     * @return R.layout_game_detail_outs_more_image_progress_bar.xx
     */
    abstract val layout: Int

    /**
     * 初始化控件id
     */
    abstract fun initViewIds(view: View?)

    /**
     * 初始化接收activity的传值
     */
    open fun initArguments() {}

    /**
     * 初始化缓存的数据
     */
    fun initCatch() {}

    /**
     * 初始化视图
     */
    abstract fun initView(view: View?)

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 初始化事件监听
     */
    fun initListener() {}

    /**
     * 处理懒加载的数据逻辑
     *
     * @param savedInstanceState
     */
    private fun lazyLoadData(savedInstanceState: Bundle?) {
        if (!isFirstLoad || !isVisibles || !isInitView) {
            return
        }
        initData(savedInstanceState)
        isFirstLoad = false
    }

    /**
     * 是否使用eventbus
     * 默认不使用注册
     *
     * @return: false
     */
    fun useEventBus(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) EventBus.getDefault().unregister(this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) { //使用懒加载的话
        if (useLazyFragment()) {
            if (isVisibleToUser) {
                isVisibles = true
                lazyLoadData(null)
            } else {
                isVisibles = false
            }
        }
        super.setUserVisibleHint(isVisibleToUser)
    }

    /**
     * 是否使用懒加载,默认false
     * 使用拦截在,如果是viewpager,需要写 viewPager.setOffscreenPageLimit(fragment.size())
     *
     * @return
     */
    open fun useLazyFragment(): Boolean {
        return false
    }
}