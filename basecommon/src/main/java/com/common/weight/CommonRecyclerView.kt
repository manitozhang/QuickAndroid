package com.common.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.common.R

/**
 * @Author: 张鹏飞
 * * @Email: 1271396448@qq.com
 * @Date: 2019/6/27 3:19 PM
 *
 *
 * 重写RecyclerView,优化一些属性
 */
class CommonRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    //横向滑动速率
    private var scrollSpeedX = 2f
    //纵向滑动速率
    private var scrollSpeedY = 2f

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

    /**
     * 滑动速率
     * @param velocityX: 纵向滑动速率
     * @param velocityY: 横向滑动速率
     */
    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        return super.fling((velocityX / scrollSpeedX).toInt(), (velocityY / scrollSpeedY).toInt())
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonRecyclerView)
        scrollSpeedX = typedArray.getFloat(R.styleable.CommonRecyclerView_recyclerSpeedX, 2f)
        scrollSpeedY = typedArray.getFloat(R.styleable.CommonRecyclerView_recyclerSpeedY, 2f)
        typedArray.recycle()
    }
}