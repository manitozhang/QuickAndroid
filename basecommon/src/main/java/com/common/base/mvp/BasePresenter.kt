package com.common.base.mvp

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: Presenter基类
 */
open class BasePresenter<V : BaseView> {
    @JvmField
    protected var mView: V? = null
    /**
     * 绑定View 一般初始化调用
     *
     * @param view: view
     */
    fun attachView(view: V) {
        mView = view
    }

    /**
     * 解绑View 一般在destroy调用
     */
    open fun detachView() {
        mView = null
    }

    /**
     * View 是否绑定
     *
     * @return: 是否绑定
     */
    val isViewAttached: Boolean
        get() = mView != null
}