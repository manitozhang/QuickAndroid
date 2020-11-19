package com.common.util.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/16
 *
 *
 * @Desc: Glide工具类  GlideApp爆红需要执行开发工具 Build 的Make Project
 */
object GlideUtil {
    /**
     * 加载默认图
     *
     * @param url:         图片地址
     * @param targetView:  目标View
     * @param placeHolder: 占位图
     */
    @JvmStatic
    fun loadImage(url: String?, targetView: ImageView?, placeHolder: Drawable?) {
        GlideApp.with(Utils.getApp())
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .centerCrop()
                .into(targetView!!)
    }

    /**
     * 加载圆图
     *
     * @param url:         图片地址
     * @param targetView:  目标View
     * @param placeHolder: 占位图
     */
    @JvmStatic
    fun loadCircleImage(url: String?, targetView: ImageView?, placeHolder: Drawable?) {
        GlideApp.with(Utils.getApp())
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .circleCrop()
                .into(targetView!!)
    }

    /**
     * 加载圆角图片
     *
     * @param url:         图片地址
     * @param targetView:  目标View
     * @param placeHolder: 占位图
     */
    @JvmStatic
    fun loadCornerImage(url: String?, targetView: ImageView?, radius: Int, placeHolder: Drawable?) {
        GlideApp.with(Utils.getApp())
                .asBitmap()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
                .placeholder(placeHolder)
                .error(placeHolder)
                .load(url)
                .into(targetView!!)
    }
}