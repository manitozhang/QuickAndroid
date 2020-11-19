package com.common.base.mvp

import androidx.lifecycle.Lifecycle
import com.common.base.BaseActivity
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: Mvp Activity 基类
 */
abstract class BaseMvpActivity<P : BasePresenter<*>?> : BaseActivity(), BaseView {
    protected var mPresenter: P? = null
    /**
     * 接触View绑定
     */
    override fun onDestroy() {
        if (mPresenter != null && mPresenter?.isViewAttached!!) mPresenter?.detachView()
        super.onDestroy()
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
    </T> */
    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY))
    }
}