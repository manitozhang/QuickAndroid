package com.library.glide

import android.os.Bundle
import android.widget.ImageView
import com.common.base.BaseActivity
import com.common.util.glide.GlideUtil.loadCircleImage
import com.common.util.glide.GlideUtil.loadCornerImage
import com.common.util.glide.GlideUtil.loadImage
import com.library.R
import kotlinx.android.synthetic.main.activity_glide_use.*

/**
 * Glide 使用
 */
class GlideUseActivity : BaseActivity() {
    override val layout: Int
        get() = R.layout.activity_glide_use

    override fun initViewIds() {
    }

    override fun initView() {
        loadImage(imgUrl, iv_default, resources.getDrawable(R.drawable.ic_placeholder))
        loadCircleImage(imgUrl, iv_circle, resources.getDrawable(R.drawable.ic_placeholder))
        loadCornerImage(imgUrl, iv_corner, 20, resources.getDrawable(R.drawable.ic_placeholder))
    }

    override fun initData(savedInstanceState: Bundle?) {}

    companion object {
        private const val imgUrl = "https://library-collection.oss-cn-beijing.aliyuncs.com/img/glideuse.png"
    }
}