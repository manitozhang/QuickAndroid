package com.common.base.mvp;

import androidx.lifecycle.Lifecycle;

import com.common.base.BaseFragment;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @Author: https://github.com/manitozhang
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */
public abstract class BaseMvpFragment<T extends BasePresenter>
        extends BaseFragment
        implements BaseView {

    private T mPresenter;

    @Override
    public void onDestroyView() {
        if (mPresenter != null && mPresenter.isViewAttached())
            mPresenter.detachView();
        super.onDestroyView();
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <L>:
     * @return
     */
    @Override
    public <L> AutoDisposeConverter<L> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
