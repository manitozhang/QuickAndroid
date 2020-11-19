package com.common.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import com.blankj.utilcode.util.ConvertUtils
import com.common.R

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/216 14:28 PM
 *
 *
 * 自定义ToolBar
 */
class CommonToolbar(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var tvToolbarTitle: TextView? = null
    private var tvToolbarRight: TextView? = null
    private var tvToolbarLeft: TextView? = null
    private var rlToolbarRoot: RelativeLayout? = null
    private var ivToolbarRight: ImageView? = null
    private var flToolbarRight: FrameLayout? = null
    private var flToolbarLeft: FrameLayout? = null
    private var viewDivider: View? = null
    private var onTitleClickListener: OnTitleClickListener? = null
    private var onLeftClickListener: OnLeftClickListener? = null
    private var onRightClickListener: OnRightClickListener? = null
    private var ivToolbarLeft: ImageView? = null
    private var rlRightView: RelativeLayout? = null
    private var rlLeftView: RelativeLayout? = null
    private var rlCenterView: RelativeLayout? = null
    private var ivToolbarTitle: ImageView? = null
    private var flToolbarTitle: FrameLayout? = null
    /**
     * 初始化视图数据
     *
     * @param context:
     * @param attrs:
     */
    fun initView(context: Context, attrs: AttributeSet) {
        val inflate = LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this)
        rlToolbarRoot = inflate.findViewById(R.id.rl_toolbar_root)
        rlCenterView = inflate.findViewById(R.id.rl_center_view)
        tvToolbarTitle = inflate.findViewById(R.id.tv_toolbar_title)
        ivToolbarTitle = inflate.findViewById(R.id.iv_toolbar_title)
        flToolbarTitle = inflate.findViewById(R.id.fl_toolbar_title)
        rlLeftView = inflate.findViewById(R.id.rl_left_view)
        tvToolbarLeft = inflate.findViewById(R.id.tv_toolbar_left)
        ivToolbarLeft = inflate.findViewById(R.id.iv_toolbar_left)
        flToolbarLeft = inflate.findViewById(R.id.fl_toolbar_left)
        rlRightView = inflate.findViewById(R.id.rl_right_view)
        tvToolbarRight = inflate.findViewById(R.id.tv_toolbar_right)
        ivToolbarRight = inflate.findViewById(R.id.iv_toolbar_right)
        flToolbarRight = inflate.findViewById(R.id.fl_toolbar_right)
        viewDivider = inflate.findViewById(R.id.view_divider)
        //获取属性值
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolbar)
        //背景颜色类型
        val rootBackground = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarBackground, R.color.white)
        //标题布局类型
        val titleType = typedArray.getInteger(R.styleable.CommonToolbar_toolbarTitleType, TITLE_TYPE_TEXT)
        //标题文字
        val strTitle = typedArray.getString(R.styleable.CommonToolbar_toolbarTitle)
        //标题文字大小
        val titleSize = typedArray.getDimension(R.styleable.CommonToolbar_toolbarTitleSize, ConvertUtils.dp2px(18f).toFloat())
        //标题文字颜色
        val titleColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarTitleColor, resources.getColor(R.color.black))
        //标题图片
        val titleImg = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarTitleImg, R.drawable.ic_title)
        //标题图片宽度
        val titleImgWidth = typedArray.getDimension(R.styleable.CommonToolbar_toolbarTitleImgWidth, NONE_PROPERTY.toFloat())
        //标题图片高度
        val titleImgHeight = typedArray.getDimension(R.styleable.CommonToolbar_toolbarTitleImgHeight, NONE_PROPERTY.toFloat())
        //左边布局类型
        val leftType = typedArray.getInteger(R.styleable.CommonToolbar_toolbarLeftType, LEFT_TYPE_IMAGE)
        //左边图片
        val leftImg = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarLeftImg, R.drawable.ic_back)
        //左边图片宽度
        val leftImgWidth = typedArray.getDimension(R.styleable.CommonToolbar_toolbarLeftImgWidth, NONE_PROPERTY.toFloat())
        //左边图片高度
        val leftImgHeight = typedArray.getDimension(R.styleable.CommonToolbar_toolbarLeftImgHeight, NONE_PROPERTY.toFloat())
        //左边文字
        val leftText = typedArray.getString(R.styleable.CommonToolbar_toolbarLeftText)
        //左边文字大小
        val leftTextSize = typedArray.getDimension(R.styleable.CommonToolbar_toolbarLeftTextSize, ConvertUtils.sp2px(15f).toFloat())
        //左边文字颜色
        val leftTextColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarLeftTextColor, resources.getColor(R.color.black))
        //右边布局类型
        val rightType = typedArray.getInteger(R.styleable.CommonToolbar_toolbarRightType, RIGHT_TYPE_NONE)
        //右边图片
        val rightImg = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarRightImg, R.drawable.ic_release)
        //右边图片宽度
        val rightImgWidth = typedArray.getDimension(R.styleable.CommonToolbar_toolbarRightImgWidth, NONE_PROPERTY.toFloat())
        //右边图片高度
        val rightImgHeight = typedArray.getDimension(R.styleable.CommonToolbar_toolbarRightImgHeight, NONE_PROPERTY.toFloat())
        //右边文字
        val rightText = typedArray.getString(R.styleable.CommonToolbar_toolbarRightText)
        //右边文字大小
        val rightTextSize = typedArray.getDimension(R.styleable.CommonToolbar_toolbarRightTextSize, ConvertUtils.sp2px(15f).toFloat())
        //右边文字颜色
        val rightTextColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarRightTextColor, resources.getColor(R.color.black))
        val dividerColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarDividerColor, resources.getColor(R.color.black))
        //是否显示分割线
        val dividerVisibility = typedArray.getInteger(R.styleable.CommonToolbar_toolbarDividerVisibility, View.GONE)
        typedArray.recycle()
        setBackgroundRes(rootBackground)
        setTitle(strTitle)
        setTitleImg(titleImg)
        setTitleImgWidth(titleImgWidth)
        setTitleImgHeight(titleImgHeight)
        setTitleSize(ConvertUtils.px2sp(titleSize).toFloat())
        tvToolbarTitle?.setTextColor(titleColor)
        //设置左边布局类型
        setLeftViewType(leftType)
        //设置标题布局类型
        setLeftViewType(titleType)
        //设置右边布局类型
        setRightViewType(rightType)
        setLeftText(leftText)
        setLeftTextSize(ConvertUtils.px2sp(leftTextSize).toFloat())
        setLeftImg(leftImg)
        setLeftImgWidth(leftImgWidth)
        setLeftImgHeight(leftImgHeight)
        tvToolbarLeft?.setTextColor(leftTextColor)
        setRightText(rightText)
        setRightTextSize(ConvertUtils.px2sp(rightTextSize).toFloat())
        tvToolbarRight?.setTextColor(rightTextColor)
        setRightImg(rightImg)
        setRightImgWidth(rightImgWidth)
        setRightImgHeight(rightImgHeight)
        //分割线是否显示
        setDividerIsVisiblity(dividerVisibility)
        //分隔线颜色
        viewDivider?.setBackgroundColor(dividerColor)
        //左边View点击监听
        rlLeftView?.setOnClickListener(OnClickListener { v: View? -> if (onLeftClickListener != null) onLeftClickListener!!.onLeftClick(v) })
        //标题View点击监听
        rlCenterView?.setOnClickListener(OnClickListener { v: View? -> if (onTitleClickListener != null) onTitleClickListener!!.onTitleClick(v) })
        //右边View点击监听
        rlRightView?.setOnClickListener(OnClickListener { v: View? -> if (onRightClickListener != null) onRightClickListener!!.onRightClick(v) })
    }

    /**
     * 设置左边图片
     *
     * @param titleImg:
     */
    fun setTitleImg(titleImg: Int): CommonToolbar {
        ivToolbarTitle!!.setImageDrawable(resources.getDrawable(titleImg))
        return this
    }

    /**
     * 设置背景资源
     *
     * @param bgRes
     * @return
     */
    fun setBackgroundRes(bgRes: Int): CommonToolbar {
        rlToolbarRoot!!.setBackgroundResource(bgRes)
        return this
    }

    /**
     * 左边布局类型
     *
     * @param leftType :左边布局类型
     */
    fun setLeftViewType(leftType: Int) {
        tvToolbarLeft!!.visibility = if (leftType == LEFT_TYPE_TEXT) View.VISIBLE else View.GONE
        ivToolbarLeft!!.visibility = if (leftType == LEFT_TYPE_IMAGE) View.VISIBLE else View.GONE
        flToolbarLeft!!.visibility = if (leftType == LEFT_TYPE_VIEW) View.VISIBLE else View.GONE
    }

    /**
     * 标题布局类型
     *
     * @param titleType :左边布局类型
     */
    fun setTitleViewType(titleType: Int) {
        tvToolbarTitle!!.visibility = if (titleType == TITLE_TYPE_TEXT) View.VISIBLE else View.GONE
        ivToolbarTitle!!.visibility = if (titleType == TITLE_TYPE_IMAGE) View.VISIBLE else View.GONE
        flToolbarTitle!!.visibility = if (titleType == TITLE_TYPE_VIEW) View.VISIBLE else View.GONE
    }

    /**
     * 右边布局类型
     *
     * @param rightType :右边布局类型
     */
    fun setRightViewType(rightType: Int) {
        tvToolbarRight!!.visibility = if (rightType == RIGHT_TYPE_TEXT) View.VISIBLE else View.GONE
        ivToolbarRight!!.visibility = if (rightType == RIGHT_TYPE_IMAGE) View.VISIBLE else View.GONE
        flToolbarRight!!.visibility = if (rightType == RIGHT_TYPE_VIEW) View.VISIBLE else View.GONE
    }

    /**
     * 分割线是否显示
     *
     * @param isVisiblity:类型
     */
    fun setDividerIsVisiblity(isVisiblity: Int) {
        viewDivider!!.visibility = if (isVisiblity == VIEW_GONE) View.GONE else View.VISIBLE
    }

    /**
     * 改变分隔线颜色
     */
    fun setDividerColor(@ColorRes dividerColor: Int) {
        viewDivider!!.setBackgroundColor(resources.getColor(dividerColor))
    }

    /**
     * 设置标题
     *
     * @param title:标题
     */
    fun setTitle(title: String?): CommonToolbar {
        tvToolbarTitle!!.text = title
        return this
    }

    /**
     * 设置标题大小
     *
     * @param titleSize:标题sp大小
     */
    fun setTitleSize(titleSize: Float): CommonToolbar {
        tvToolbarTitle!!.textSize = titleSize
        return this
    }

    /**
     * 设置标题颜色
     *
     * @param titleColor:标题颜色
     */
    fun setTitleColor(titleColor: Int): CommonToolbar {
        tvToolbarTitle!!.setTextColor(resources.getColor(titleColor))
        return this
    }

    /**
     * 设置左边图片宽度
     *
     * @param titleImgWidth:
     */
    fun setTitleImgWidth(titleImgWidth: Float): CommonToolbar {
        if (titleImgWidth == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarTitle!!.layoutParams as LayoutParams
        //因为左右有给点击区域的padding所以加上两边的padding
        layoutParams.width = titleImgWidth.toInt() + ConvertUtils.dp2px(20f)
        ivToolbarTitle!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置标题图片高度
     *
     * @param rightImgHeight:
     */
    fun setTitleImgHeight(rightImgHeight: Float): CommonToolbar {
        if (rightImgHeight == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarTitle!!.layoutParams as LayoutParams
        layoutParams.height = rightImgHeight.toInt()
        ivToolbarTitle!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置右边view
     *
     * @param view:右边布局
     */
    fun setTitleView(view: View?): CommonToolbar {
        flToolbarTitle!!.removeAllViews()
        flToolbarTitle!!.addView(view)
        return this
    }

    /**
     * 获取右边布局的View
     * @return
     */
    val titleView: View?
        get() = flToolbarTitle

    /**
     * 设置左边图片
     *
     * @param leftImg:
     */
    fun setLeftImg(leftImg: Int): CommonToolbar {
        ivToolbarLeft!!.setImageDrawable(resources.getDrawable(leftImg))
        return this
    }

    /**
     * 设置左边图片宽度
     *
     * @param leftImgWidth:
     */
    fun setLeftImgWidth(leftImgWidth: Float): CommonToolbar {
        if (leftImgWidth == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarLeft!!.layoutParams as LayoutParams
        //因为左右有给点击区域的padding所以加上两边的padding
        layoutParams.width = leftImgWidth.toInt() + ConvertUtils.dp2px(20f)
        ivToolbarLeft!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置左边图片高度
     *
     * @param leftImgHeight:
     */
    fun setLeftImgHeight(leftImgHeight: Float): CommonToolbar {
        if (leftImgHeight == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarLeft!!.layoutParams as LayoutParams
        layoutParams.height = leftImgHeight.toInt()
        ivToolbarLeft!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置左边文字
     *
     * @param leftText:左边文字
     */
    fun setLeftText(leftText: String?): CommonToolbar {
        tvToolbarLeft!!.text = leftText
        return this
    }

    /**
     * 设置左边文字大小
     *
     * @param textSize:左边文字sp大小
     */
    fun setLeftTextSize(textSize: Float): CommonToolbar {
        tvToolbarLeft!!.textSize = textSize
        return this
    }

    /**
     * 设置左边文字颜色
     *
     * @param leftTextColor:左边文字
     */
    fun setLeftTextColor(leftTextColor: Int): CommonToolbar {
        tvToolbarLeft!!.setTextColor(resources.getColor(leftTextColor))
        return this
    }

    /**
     * 设置左边view
     *
     * @param view:左边布局
     */
    fun setLeftView(view: View?): CommonToolbar {
        flToolbarLeft!!.removeAllViews()
        flToolbarLeft!!.addView(view)
        return this
    }

    /**
     * 获取左边布局的View
     * @return
     */
    val leftView: View?
        get() = flToolbarRight

    /**
     * 设置右边文字
     *
     * @param rightText:右边文字
     */
    fun setRightText(rightText: String?): CommonToolbar {
        tvToolbarRight!!.text = rightText
        return this
    }

    /**
     * 设置右边文字大小
     *
     * @param textSize:右边文字sp大小
     */
    fun setRightTextSize(textSize: Float): CommonToolbar {
        tvToolbarRight!!.textSize = textSize
        return this
    }

    /**
     * 设置右边文字颜色
     *
     * @param rightTextColor:右边文字颜色
     */
    fun setRightTextColor(rightTextColor: Int): CommonToolbar {
        tvToolbarRight!!.setTextColor(resources.getColor(rightTextColor))
        return this
    }

    /**
     * 设置右边图片
     *
     * @param rightImg:
     */
    fun setRightImg(rightImg: Int): CommonToolbar {
        ivToolbarRight!!.setImageDrawable(resources.getDrawable(rightImg))
        return this
    }

    /**
     * 设置左边图片宽度
     *
     * @param rightImgWidth:
     */
    fun setRightImgWidth(rightImgWidth: Float): CommonToolbar {
        if (rightImgWidth == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarRight!!.layoutParams as LayoutParams
        //因为左右有给点击区域的padding所以加上两边的padding
        layoutParams.width = rightImgWidth.toInt() + ConvertUtils.dp2px(20f)
        ivToolbarRight!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置左边图片高度
     *
     * @param rightImgHeight:
     */
    fun setRightImgHeight(rightImgHeight: Float): CommonToolbar {
        if (rightImgHeight == NONE_PROPERTY.toFloat()) return this
        val layoutParams = ivToolbarRight!!.layoutParams as LayoutParams
        layoutParams.height = rightImgHeight.toInt()
        ivToolbarRight!!.layoutParams = layoutParams
        return this
    }

    /**
     * 设置右边view
     *
     * @param view:右边布局
     */
    fun setRightView(view: View?): CommonToolbar {
        flToolbarRight!!.removeAllViews()
        flToolbarRight!!.addView(view)
        return this
    }

    /**
     * 获取右边布局的View
     * @return
     */
    val rightView: View?
        get() = flToolbarRight

    /**
     * 左边按钮监听
     */
    interface OnLeftClickListener {
        fun onLeftClick(v: View?)
    }

    /**
     * 标题按钮监听
     */
    interface OnTitleClickListener {
        fun onTitleClick(v: View?)
    }

    /**
     * 右边按钮监听
     */
    interface OnRightClickListener {
        fun onRightClick(v: View?)
    }

    fun setOnLeftClickListener(onLeftClickListener: OnLeftClickListener?): CommonToolbar {
        this.onLeftClickListener = onLeftClickListener
        return this
    }

    fun setOnTitleClickListener(onTitleClickListener: OnTitleClickListener?): CommonToolbar {
        this.onTitleClickListener = onTitleClickListener
        return this
    }

    fun setOnRightClickListener(onRightClickListener: OnRightClickListener?): CommonToolbar {
        this.onRightClickListener = onRightClickListener
        return this
    }

    companion object {
        /**
         * 没有属性
         */
        const val NONE_PROPERTY = -999
        /**
         * 左边布局类型
         */
        const val LEFT_TYPE_NONE = -1 //无
        const val LEFT_TYPE_TEXT = 1 //文字
        const val LEFT_TYPE_IMAGE = 2 //图片
        const val LEFT_TYPE_VIEW = 3 //布局
        /**
         * 左边布局类型
         */
        const val TITLE_TYPE_NONE = -1 //无
        const val TITLE_TYPE_TEXT = 1 //文字
        const val TITLE_TYPE_IMAGE = 2 //图片
        const val TITLE_TYPE_VIEW = 3 //布局
        /**
         * 右边布局类型
         */
        const val RIGHT_TYPE_NONE = -1 //无
        const val RIGHT_TYPE_TEXT = 1 //文字
        const val RIGHT_TYPE_IMAGE = 2 //图片
        const val RIGHT_TYPE_VIEW = 3 //布局
        /**
         * 分割线是否显示
         */
        const val VIEW_GONE = 0 //隐藏
        const val VIEW_VISIBLE = 1 //显示
    }

    init {
        initView(context, attrs)
    }
}