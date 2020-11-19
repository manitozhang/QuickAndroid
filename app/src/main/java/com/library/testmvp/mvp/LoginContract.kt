package com.library.testmvp.mvp

import com.common.base.mvp.BaseView
import com.common.http.base.BaseResponse
import com.common.http.bean.ExampleBean
import io.reactivex.Observable
import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc:
 */
interface LoginContract {
    interface Model {
        fun login(map: HashMap<String, ExampleBean>): Observable<BaseResponse<ExampleBean>>
    }

    interface View : BaseView {
        fun loginSuccess(bean: ExampleBean?)
    }

    interface Presenter {
        fun login(map: HashMap<String, ExampleBean>)
    }
}