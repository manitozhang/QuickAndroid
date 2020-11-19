package com.library.lazyload

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.common.base.BaseActivity
import com.common.weight.CommonViewPager
import com.google.android.material.tabs.TabLayout
import com.library.R
import kotlinx.android.synthetic.main.activity_lazy_load.*

/**
 * 懒加载的Activity
 */
class LazyLoadActivity : BaseActivity() {
    private val tabArray = arrayOf("推荐", "关注", "娱乐", "国内", "军事", "财经")
    override val layout: Int
        get() = R.layout.activity_lazy_load

    override fun initViewIds() {
    }

    override fun initView() {
        tab_layout!!.setupWithViewPager(view_pager)
        view_pager!!.offscreenPageLimit = tabArray.size
        view_pager!!.adapter = MyPagerAdapter(supportFragmentManager)
    }

    override fun initData(savedInstanceState: Bundle?) {}
    internal inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getPageTitle(position: Int): CharSequence? {
            return tabArray[position]
        }

        override fun getItem(position: Int): Fragment { //传递数据
            val lazyFragment = LazyFragment()
            val bundle = Bundle()
            bundle.putString("bundle_tab", tabArray[position])
            lazyFragment.arguments = bundle
            return lazyFragment
        }

        override fun getCount(): Int {
            return tabArray.size
        }
    }
}