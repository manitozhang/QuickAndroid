package com.common.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 * <p>
 * @Desc:
 */
public class CommonViewPager extends ViewPager {
    public CommonViewPager(@NonNull Context context) {
        super(context);
    }

    public CommonViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 滑动到边界时无阴影
     */
    @Override
    public void setOverScrollMode(int overScrollMode) {
        super.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    /**
     * 不显示垂直滑动条
     */
    @Override
    public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) {
        super.setVerticalScrollBarEnabled(false);
    }

    /**
     * 不显示水平滑动条
     */
    @Override
    public void setHorizontalFadingEdgeEnabled(boolean horizontalFadingEdgeEnabled) {
        super.setHorizontalFadingEdgeEnabled(false);
    }

}
