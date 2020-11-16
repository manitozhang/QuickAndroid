package com.library.testmvp.mvp;

import com.common.base.mvp.BaseView;
import com.common.http.base.BaseResponse;
import com.common.http.bean.ExampleBean;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */

public interface LoginContract {

    interface Model {
        Observable<BaseResponse<ExampleBean>> login(HashMap<String, Object> map);
    }

    interface View extends BaseView {
        void loginSuccess(ExampleBean bean);
    }

    interface Presenter {
        void login(HashMap map);
    }
}