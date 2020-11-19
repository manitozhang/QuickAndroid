package com.library.login

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.common.base.mvp.BaseMvpActivity
import com.common.http.bean.ExampleBean
import com.common.http.helper.Mobile
import com.common.util.ParamUtil
import com.library.R
import com.library.login.mvp.LoginContract
import com.library.login.mvp.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录页面
 *
 * Mvp模式
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {
    override val layout: Int
        get() = R.layout.activity_login

    override fun initViewIds() {}
    override fun initView() {
        mPresenter = LoginPresenter()
        mPresenter?.attachView(this)
    }
    override fun initData(savedInstanceState: Bundle?) {}

    override fun loginSuccess(bean: ExampleBean?) { //登录请求成功
        ToastUtils.showLong("登录成功")
    }

    fun btnLogin(view: View) {
        mPresenter?.login(Mobile.userLogin(ParamUtil.getEditStr(et_username),ParamUtil.getEditStr(et_password)))
    }
}