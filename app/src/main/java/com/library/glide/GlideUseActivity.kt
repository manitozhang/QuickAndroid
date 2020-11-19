package com.library.glide

import android.os.Bundle
import android.widget.ImageView
import com.common.base.BaseActivity
import com.common.util.glide.GlideUtil.loadCircleImage
import com.common.util.glide.GlideUtil.loadCornerImage
import com.common.util.glide.GlideUtil.loadImage
import com.library.R

/**
 * Glide 使用
 */
class GlideUseActivity : BaseActivity() {
    private var ivDefault: ImageView? = null
    private var ivCircle: ImageView? = null
    private var ivCorner: ImageView? = null
    override val layout: Int
        get() = R.layout.activity_glide_use

    override fun initViewIds() {
        ivDefault = findViewById(R.id.iv_default)
        ivCircle = findViewById(R.id.iv_circle)
        ivCorner = findViewById(R.id.iv_corner)
    }

    override fun initView() {
        loadImage(imgUrl, ivDefault, resources.getDrawable(R.drawable.ic_placeholder))
        loadCircleImage(imgUrl, ivCircle, resources.getDrawable(R.drawable.ic_placeholder))
        loadCornerImage(imgUrl, ivCorner, 20, resources.getDrawable(R.drawable.ic_placeholder))
    }

    override fun initData(savedInstanceState: Bundle?) {}

    companion object {
        private const val imgUrl = "https://library-collection.oss-cn-beijing.aliyuncs.com/img/glideuse.png"
    }
}