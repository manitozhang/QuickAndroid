package com.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.common.base.BaseActivity
import com.library.filedownload.FileDownloadActivity
import com.library.glide.GlideUseActivity
import com.library.http.HttpSampleActivity
import com.library.lazyload.LazyLoadActivity
import com.library.list.multiplechoice.MultipleChoiceListActivity
import com.library.list.refreshload.RefreshLoadListActivity
import com.library.list.singlechoice.SingleChoiceListActivity
import com.library.mainpage.MainPageActivity
import com.library.login.LoginActivity
import com.library.toolbar.CommonToolbarActivity

class MainActivity : BaseActivity() {
    override val layout: Int
        get() = R.layout.activity_main

    override fun initViewIds() {}
    override fun initView() {}
    override fun initData(savedInstanceState: Bundle?) {}
    /**
     * 首页框架
     * @param view
     */
    fun btnMainPage(view: View?) {
        startActivity(Intent(this, MainPageActivity::class.java))
    }

    /**
     * 网络请求相关
     *
     * @param view
     */
    fun btnHttp(view: View?) {
        startActivity(Intent(this, HttpSampleActivity::class.java))
    }

    /**
     * 懒加载
     * @param view
     */
    fun btnLazyLoad(view: View?) {
        startActivity(Intent(this, LazyLoadActivity::class.java))
    }

    /**
     * 封装的标题栏
     * @param view
     */
    fun btnCommonToolbar(view: View?) {
        startActivity(Intent(this, CommonToolbarActivity::class.java))
    }

    /**
     * Glide操作
     * @param view
     */
    fun btnGlideClip(view: View?) {
        startActivity(Intent(this, GlideUseActivity::class.java))
    }

    /**
     * 文件下载
     * @param view
     */
    fun btnDownload(view: View?) {
        startActivity(Intent(this, FileDownloadActivity::class.java))
    }

    /**
     * 列表刷新加载
     * @param view
     */
    fun btnListRefresh(view: View?) {
        startActivity(Intent(this, RefreshLoadListActivity::class.java))
    }

    /**
     * 列表单选
     * @param view
     */
    fun btnListSingleChoice(view: View?) {
        startActivity(Intent(this, SingleChoiceListActivity::class.java))
    }

    /**
     * 列表多选
     * @param view
     */
    fun btnListMultipleChoice(view: View?) {
        startActivity(Intent(this, MultipleChoiceListActivity::class.java))
    }

    /**
     * Mvp示例
     */
    fun btnMvp(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}