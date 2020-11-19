package com.library.mainpage

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.common.base.BaseActivity
import com.library.R
import com.library.mainpage.fragment.HomeFragment
import com.library.mainpage.fragment.MineFragment
import com.library.mainpage.fragment.NewsFragment
import kotlinx.android.synthetic.main.include_main_bottom.*

/**
 * 首页页面
 */
class MainPageActivity : BaseActivity() {
    private val idArrays = intArrayOf(R.id.tv_home, R.id.tv_news, R.id.tv_mine)
    private var beginTransaction: FragmentTransaction? = null
    private var homeFragment: HomeFragment? = null
    private var newsFragment: NewsFragment? = null
    private var mineFragment: MineFragment? = null
    override val layout: Int
        get() = R.layout.activity_main_page

    override fun initViewIds() {
    }

    override fun initView() {
        setFragment(0)
    }

    override fun initData(savedInstanceState: Bundle?) {}
    override fun initListener() {
        super.initListener()
        tv_home!!.setOnClickListener { v: View? -> setFragment(0) }
        tv_news!!.setOnClickListener { v: View? -> setFragment(1) }
        tv_mine!!.setOnClickListener { v: View? -> setFragment(2) }
    }

    /**
     * 设置Fragment
     * 其他地方可发送通知来执行该方法改变tab选中
     * @param index: 下标
     */
    private fun setFragment(index: Int) {
        val fragmentManager = supportFragmentManager
        beginTransaction = fragmentManager.beginTransaction()
        hideFragment()
        when (index) {
            0 ->  //首页
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    beginTransaction!!.add(R.id.fl_layout, homeFragment!!)
                } else {
                    beginTransaction!!.show(homeFragment!!)
                }
            1 ->  //发现
                if (newsFragment == null) {
                    newsFragment = NewsFragment()
                    beginTransaction!!.add(R.id.fl_layout, newsFragment!!)
                } else {
                    beginTransaction!!.show(newsFragment!!)
                }
            2 ->  //我的
                if (mineFragment == null) {
                    mineFragment = MineFragment()
                    beginTransaction!!.add(R.id.fl_layout, mineFragment!!)
                } else {
                    beginTransaction!!.show(mineFragment!!)
                }
        }
        beginTransaction!!.commitAllowingStateLoss()
        refreshBottomStatus(index)
    }

    /**
     * 改变底部图片和文字的状态
     *
     * @param position: 下标
     */
    private fun refreshBottomStatus(position: Int) {
        for (i in idArrays.indices) {
            findViewById<View>(idArrays[i]).isSelected = position == i
        }
    }

    /**
     * 隐藏所有Fragment
     */
    private fun hideFragment() {
        if (homeFragment != null) beginTransaction!!.hide(homeFragment!!)
        if (newsFragment != null) beginTransaction!!.hide(newsFragment!!)
        if (mineFragment != null) beginTransaction!!.hide(mineFragment!!)
    }
}