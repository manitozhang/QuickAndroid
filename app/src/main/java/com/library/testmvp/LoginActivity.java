package com.library.testmvp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.common.base.mvp.BaseMvpActivity;
import com.common.base.mvp.BasePresenter;
import com.common.http.bean.ExampleBean;
import com.library.R;
import com.library.testmvp.mvp.LoginContract;
import com.library.testmvp.mvp.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {


    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViewIds() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void loginSuccess(ExampleBean bean) {
        //登录请求成功
    }
}
