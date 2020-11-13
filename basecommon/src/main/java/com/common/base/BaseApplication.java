package com.common.base;

import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;
import com.common.util.SpUtil;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpUtil.init(this);
        initToast();
    }

    private void initToast(){
        ToastUtils toastUtils = ToastUtils.getDefaultMaker();
        toastUtils.setGravity(Gravity.CENTER,0,0);
    }

}
