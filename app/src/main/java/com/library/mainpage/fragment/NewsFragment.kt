package com.library.mainpage.fragment

import android.os.Bundle
import android.view.View
import com.common.base.BaseFragment
import com.library.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 *
 *
 * @Desc:
 */
class NewsFragment : BaseFragment() {
    override val layout: Int
        get() = R.layout.fragment_main

    override fun initViewIds(view: View?) {}
    override fun initView(view: View?) {
        tvDesc.setText("我是新闻页")
    }

    override fun initData(savedInstanceState: Bundle?) {}
}