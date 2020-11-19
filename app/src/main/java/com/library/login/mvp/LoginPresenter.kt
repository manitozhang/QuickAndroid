package com.library.login.mvp

import com.common.base.mvp.BasePresenter
import com.common.http.base.BaseObserver
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleBean
import com.library.login.mvp.LoginContract.Presenter
import kotlin.collections.HashMap

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc:
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), Presenter {
    private val model: LoginModel
    override fun login(map: HashMap<String, Any>) {
        model.login(map)
                .compose(observeOnMainThread())
                .subscribe(object : BaseObserver<ExampleBean>() {
                    override fun onSuccess(response: ExampleBean) {
                        mView!!.loginSuccess(response)
                    }
                })
    }



    init {
        model = LoginModel()
    }
}