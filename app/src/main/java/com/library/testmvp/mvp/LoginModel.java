package com.library.testmvp.mvp;


import com.common.http.XRetrofit;
import com.common.http.base.BaseResponse;
import com.common.http.bean.ExampleBean;
import com.common.http.helper.RequestBodyHelper;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @Author: https://github.com/manitozhang
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<BaseResponse<ExampleBean>> login(HashMap<String, Object> map) {
        return XRetrofit.getApi().example3(RequestBodyHelper.getRequestBody(map));
    }
}