package com.common.base.mvp;

import androidx.lifecycle.Lifecycle;

import com.common.base.BaseActivity;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: Mvp Activity 基类
 */
public abstract class BaseMvpActivity<P extends BasePresenter>
        extends BaseActivity implements BaseView {

    protected P mPresenter;

    /**
     * 接触View绑定
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null && mPresenter.isViewAttached())
            mPresenter.detachView();
        super.onDestroy();
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
