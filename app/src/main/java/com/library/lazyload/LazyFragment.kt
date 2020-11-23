package com.library.lazyload

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.common.base.BaseFragment
import com.library.R
import kotlinx.android.synthetic.main.fragment_lazy.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 *
 *
 * @Desc:
 */
class LazyFragment : BaseFragment() {
    private var bundleTabName: String? = ""
    override val layout: Int
        get() = R.layout.fragment_lazy

    override fun initViewIds(view: View?) {
    }

    override fun initArguments() {
        if (arguments != null) bundleTabName = arguments!!.getString("bundle_tab")
        super.initArguments()
    }

    override fun initView(view: View?) {}
    /**
     * 在initData方法写需要懒加载的业务代码即可
     * @param savedInstanceState
     */
    override fun initData(savedInstanceState: Bundle?) {
        tv_tab_name.text = bundleTabName
    }

    /**
     * 使用懒加载 必须实现  需要配合viewpager.setOffscreenPageLimit()方法,有多少个就写几
     *
     * @return
     */
    override fun useLazyFragment(): Boolean {
        return true
    }
}