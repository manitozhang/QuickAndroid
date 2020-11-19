package com.library.login.mvp;

import com.common.base.mvp.BasePresenter;
import com.common.http.base.BaseObserver;
import com.common.http.base.RxJavaHelper;
import com.common.http.bean.ExampleBean;

import java.util.HashMap;


/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{


    private LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(HashMap map) {
        model.login(map)
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<ExampleBean>() {
                    @Override
                    public void onSuccess(ExampleBean response) {
                        mView.loginSuccess(response);
                    }
                });
    }
}