package com.library.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.common.base.BaseActivity
import com.common.weight.CommonToolbar
import com.common.weight.CommonToolbar.OnRightClickListener
import com.common.weight.CommonToolbar.OnTitleClickListener
import com.library.R
import kotlinx.android.synthetic.main.activity_common_toolbar.*

/**
 * 公共标题栏
 */
class CommonToolbarActivity : BaseActivity() {
    private var titleColorFlag = true
    private var dividerFlag = true
    private var dividerColorFlag = true
    private var bgColorFlag = true
    private var titleSizeFlag = true
    private var leftColorFlag = true
    private var rightColorFlag = true
    private var rightSizeFlag = true
    private var leftSizeFlag = true
    private var leftImgWidthHeightFlag = true
    private var rightImgWidthHeightFlag = true
    private var titleImgWidthHeightFlag = true
    override val layout: Int
        get() = R.layout.activity_common_toolbar

    override fun initViewIds() {
    }

    override fun initView() {}
    override fun initData(savedInstanceState: Bundle?) {}
    override fun initListener() {
        super.initListener()
        toolbar.setOnLeftClickListener(object : CommonToolbar.OnLeftClickListener {
            override fun onLeftClick(v: View?) {
                ToastUtils.showShort("点击了左边View")
            }
        })
        toolbar!!.setOnTitleClickListener(object : OnTitleClickListener {
            override fun onTitleClick(v: View?) {
                ToastUtils.showShort("点击了标题View")
            }
        })
        toolbar!!.setOnRightClickListener(object : OnRightClickListener {
            override fun onRightClick(v: View?) {
                ToastUtils.showShort("点击了右边View")
            }

        })
    }

    /**
     * 左边什么也不显示
     *
     * @param view:
     */
    fun btnLeftNone(view: View?) {
        toolbar!!.setLeftViewType(CommonToolbar.LEFT_TYPE_NONE)
    }

    /**
     * 左边图片
     *
     * @param view:
     */
    fun btnLeftImg(view: View?) {
        toolbar!!.setLeftViewType(CommonToolbar.LEFT_TYPE_IMAGE)
        toolbar!!.setLeftImg(R.drawable.ic_back)
    }

    /**
     * 左边文字
     *
     * @param view:
     */
    fun btnLeftText(view: View?) {
        toolbar!!.setLeftViewType(CommonToolbar.LEFT_TYPE_TEXT)
        toolbar!!.setLeftText("返回")
    }

    /**
     * 左边view
     *
     * @param view:
     */
    fun btnLeftView(view: View?) {
        val inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_left_view, null)
        toolbar!!.setLeftView(inflate)
        toolbar!!.setLeftViewType(CommonToolbar.LEFT_TYPE_VIEW)
    }

    /**
     * 右边文字
     *
     * @param view:
     */
    fun btnRightText(view: View?) {
        toolbar!!.setRightText("更多")
        toolbar!!.setRightViewType(CommonToolbar.RIGHT_TYPE_TEXT)
    }

    /**
     * 右边图片
     *
     * @param view:
     */
    fun btnRightImg(view: View?) {
        toolbar!!.setRightImg(R.drawable.ic_release)
        toolbar!!.setRightViewType(CommonToolbar.RIGHT_TYPE_IMAGE)
    }

    /**
     * 右边View
     *
     * @param view:
     */
    fun btnRightView(view: View?) {
        val inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_right_view, null)
        toolbar!!.setRightView(inflate)
        toolbar!!.setRightViewType(CommonToolbar.RIGHT_TYPE_VIEW)
    }

    /**
     * 右边什么也不显示
     *
     * @param view:
     */
    fun btnRightNone(view: View?) {
        toolbar!!.setRightViewType(CommonToolbar.RIGHT_TYPE_NONE)
    }

    /**
     * 改变标题颜色
     *
     * @param view:
     */
    fun btnTitleColor(view: View?) {
        toolbar!!.setTitleColor(if (titleColorFlag) R.color.colorMain else R.color.black)
        titleColorFlag = !titleColorFlag
    }

    /**
     * 分割线显示隐藏
     *
     * @param view:
     */
    fun btnDividerVisiblity(view: View?) {
        toolbar!!.setDividerIsVisiblity(if (dividerFlag) CommonToolbar.VIEW_GONE else CommonToolbar.VIEW_VISIBLE)
        dividerFlag = !dividerFlag
    }

    /**
     * 分割线颜色
     *
     * @param view:
     */
    fun btnDividerColor(view: View?) {
        toolbar!!.setDividerColor(if (dividerColorFlag) R.color.colorPrimary else R.color.black)
        dividerColorFlag = !dividerColorFlag
    }

    /**
     * 改变背景颜色
     *
     * @param view:
     */
    fun btnbgColor(view: View?) {
        toolbar!!.setBackgroundRes(if (bgColorFlag) R.color.colorMain else R.color.white)
        bgColorFlag = !bgColorFlag
    }

    /**
     * 改变文字大小
     *
     * @param view:
     */
    fun btnTitleSize(view: View?) {
        toolbar!!.setTitleSize(if (titleSizeFlag) 15f else 18f)
        titleSizeFlag = !titleSizeFlag
    }

    /**
     * 左边文字大小
     *
     * @param view:
     */
    fun btnLeftTextSize(view: View?) {
        toolbar!!.setLeftTextSize(if (leftSizeFlag) 18f else 15f)
        leftSizeFlag = !leftSizeFlag
    }

    /**
     * 左边文字颜色
     *
     * @param view:
     */
    fun btnLeftTextColor(view: View?) {
        toolbar!!.setLeftTextColor(if (leftColorFlag) R.color.colorMain else R.color.black)
        leftColorFlag = !leftColorFlag
    }

    /**
     * 左边图片大小
     *
     * @param view:
     */
    fun btnLeftImgWidthHeight(view: View?) {
        toolbar!!.setLeftImgWidth(if (leftImgWidthHeightFlag) 50f else 30f)
        toolbar!!.setLeftImgHeight(if (leftImgWidthHeightFlag) 50f else 30f)
        leftImgWidthHeightFlag = !leftImgWidthHeightFlag
    }

    /**
     * 右边文字大小
     *
     * @param view:
     */
    fun btnRightTextSize(view: View?) {
        toolbar!!.setRightTextSize(if (rightSizeFlag) 18f else 15f)
        rightSizeFlag = !rightSizeFlag
    }

    /**
     * 右边文字颜色
     *
     * @param view:
     */
    fun btnRightTextColor(view: View?) {
        toolbar!!.setRightTextColor(if (rightColorFlag) R.color.colorMain else R.color.black)
        rightColorFlag = !rightColorFlag
    }

    /**
     * 右边图片大小
     *
     * @param view:
     */
    fun btnRightImgWidthHeight(view: View?) {
        toolbar!!.setRightImgWidth(if (rightImgWidthHeightFlag) 50f else 30f)
        toolbar!!.setRightImgHeight(if (rightImgWidthHeightFlag) 50f else 30f)
        rightImgWidthHeightFlag = !rightImgWidthHeightFlag
    }

    /**
     * 标题什么都没有
     * @param view
     */
    fun btnTitleNone(view: View?) {
        toolbar!!.setTitleViewType(CommonToolbar.TITLE_TYPE_NONE)
    }

    /**
     * 标题是图片
     * @param view
     */
    fun btnTitleImg(view: View?) {
        toolbar!!.setTitleViewType(CommonToolbar.TITLE_TYPE_IMAGE)
    }

    /**
     * 标题是文字
     * @param view
     */
    fun btnTitleText(view: View?) {
        toolbar!!.setTitle("标题")
        toolbar!!.setTitleViewType(CommonToolbar.TITLE_TYPE_TEXT)
    }

    /**
     * 标题是布局
     * @param view
     */
    fun btnTitleView(view: View?) {
        val inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_title_view, null)
        toolbar!!.setTitleView(inflate)
        toolbar!!.setTitleViewType(CommonToolbar.TITLE_TYPE_VIEW)
    }

    /**
     * 设置标题图片大小
     * @param view
     */
    fun btnTitleImgWidthHeight(view: View?) {
        toolbar!!.setTitleImgWidth(if (titleImgWidthHeightFlag) 50f else 30f)
        toolbar!!.setTitleImgHeight(if (titleImgWidthHeightFlag) 50f else 30f)
        titleImgWidthHeightFlag = !titleImgWidthHeightFlag
    }
}