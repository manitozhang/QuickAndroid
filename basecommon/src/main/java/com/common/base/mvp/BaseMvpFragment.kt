package com.common.base.mvp

import androidx.lifecycle.Lifecycle
import com.common.base.BaseFragment
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc:
 */
abstract class BaseMvpFragment<T : BasePresenter<*>?> : BaseFragment(), BaseView {
    private val mPresenter: T? = null
    override fun onDestroyView() {
        if (mPresenter != null && mPresenter.isViewAttached) mPresenter.detachView()
        super.onDestroyView()
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <L>:
     * @return
    </L> */
    override fun <L> bindAutoDispose(): AutoDisposeConverter<L> {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY))
    }
}