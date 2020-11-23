package com.common.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 *
 *
 * @Desc:
 */
class CommonViewPager : ViewPager {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    /**
     * 滑动到边界时无阴影
     */
    override fun setOverScrollMode(overScrollMode: Int) {
        super.setOverScrollMode(View.OVER_SCROLL_NEVER)
    }

    /**
     * 不显示垂直滑动条
     */
    override fun setVerticalScrollBarEnabled(verticalScrollBarEnabled: Boolean) {
        super.setVerticalScrollBarEnabled(false)
    }

    /**
     * 不显示水平滑动条
     */
    override fun setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled: Boolean) {
        super.setHorizontalFadingEdgeEnabled(false)
    }
}