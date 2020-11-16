package com.common.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.common.R;

/**
 * @Author: https://github.com/manitozhang
 * * @Email: 1271396448@qq.com
 * @Date: 2019/6/27 3:19 PM
 * <p>
 * 重写RecyclerView,优化一些属性
 */
public class CommonRecyclerView extends RecyclerView {

    //横向滑动速率
    private float scrollSpeedX = 2;
    //纵向滑动速率
    private float scrollSpeedY = 2;

    public CommonRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonRecyclerView);
        scrollSpeedX = typedArray.getFloat(R.styleable.CommonRecyclerView_recyclerSpeedX, 2);
        scrollSpeedY = typedArray.getFloat(R.styleable.CommonRecyclerView_recyclerSpeedY, 2);
        typedArray.recycle();
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

    /**
     * 滑动速率
     * @param velocityX: 纵向滑动速率
     * @param velocityY: 横向滑动速率
     */
    @Override
    public boolean fling(int velocityX, int velocityY) {
        return super.fling((int)(velocityX / scrollSpeedX), (int)(velocityY / scrollSpeedY));
    }
}
