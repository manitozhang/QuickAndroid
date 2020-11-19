package com.library.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.common.base.mvp.BaseMvpActivity;
import com.common.http.bean.ExampleBean;
import com.common.http.helper.Mobile;
import com.common.util.ParamUtil;
import com.library.R;
import com.library.login.mvp.LoginContract;
import com.library.login.mvp.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {


    private EditText etPassword;
    private EditText etUsername;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViewIds() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    @Override
    public void initView() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void loginSuccess(ExampleBean bean) {
        //登录请求成功
        ToastUtils.showShort("登录成功");
    }

    public void btnLogin(View view) {
        mPresenter.login(Mobile.userLogin(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword)));
    }
}
