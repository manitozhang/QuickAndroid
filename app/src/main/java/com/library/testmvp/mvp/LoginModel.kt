package com.library.testmvp.mvp

import com.common.http.XRetrofit.api
import com.common.http.base.BaseResponse
import com.common.http.bean.ExampleBean
import com.common.http.helper.RequestBodyHelper.getRequestBody
import io.reactivex.Observable
import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc:
 */
class LoginModel : LoginContract.Model {
    override fun login(map: HashMap<String, ExampleBean>): Observable<BaseResponse<ExampleBean>> {
        return api!!.example3(getRequestBody(map))
    }
}