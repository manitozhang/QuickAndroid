package com.common.base

import android.app.Application
import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.common.util.SpUtil
import com.liulishuo.filedownloader.FileDownloader

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SpUtil.init(this)
        initToast()
        FileDownloader.setup(this)
    }

    private fun initToast() {
        val toastUtils = ToastUtils.getDefaultMaker()
        toastUtils.setGravity(Gravity.CENTER, 0, 0)
    }
}