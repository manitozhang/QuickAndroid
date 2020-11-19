package com.library.testmvp.mvp

import com.common.base.mvp.BasePresenter
import com.common.http.base.BaseObserver
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleBean
import com.library.testmvp.mvp.LoginContract.Presenter
import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc:
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), Presenter {
    private val model: LoginModel
    override fun login(map: HashMap<String, ExampleBean>) {
        model.login(map)
                .compose(observeOnMainThread<ExampleBean>())
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