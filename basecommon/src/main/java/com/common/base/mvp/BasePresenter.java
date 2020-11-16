package com.common.base.mvp;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: Presenter基类
 */
public class BasePresenter<V extends BaseView> {

    protected V mView;

    /**
     * 绑定View 一般初始化调用
     *
     * @param view: view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解绑View 一般在destroy调用
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * View 是否绑定
     *
     * @return: 是否绑定
     */
    public boolean isViewAttached() {
        return mView != null;
    }

}
