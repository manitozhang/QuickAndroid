package com.common.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.R;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/216 14:28 PM
 * <p>
 * 自定义ToolBar
 */
public class CommonToolbar extends RelativeLayout {

    /**
     * 左边布局类型
     */
    public static final int LEFT_TYPE_NONE = -1;//无
    public static final int LEFT_TYPE_TEXT = 1;//文字
    public static final int LEFT_TYPE_IMAGE = 2;//图片
    public static final int LEFT_TYPE_VIEW = 3;//布局

    /**
     * 右边布局类型
     */
    public static final int RIGHT_TYPE_NONE = -1;//无
    public static final int RIGHT_TYPE_TEXT = 1;//文字
    public static final int RIGHT_TYPE_IMAGE = 2;//图片
    public static final int RIGHT_TYPE_VIEW = 3;//布局

    /**
     * 分割线是否显示
     */
    public static final int VIEW_GONE = 0;//隐藏
    public static final int VIEW_VISIBLE = 1;//显示




    private TextView tvToolbarTitle;
    private TextView tvToolbarRight;
    private TextView tvToolbarLeft;
    private RelativeLayout rlToolbarRoot;
    private ImageView ivToolbarRight;
    private FrameLayout flToolbarRight;
    private FrameLayout flToolbarLeft;
    private View viewLine;
    private OnLeftClickListener onLeftClickListener = null;
    private OnRightClickListener onRightClickListener = null;
    private ImageView ivToolbarLeft;
    private RelativeLayout rlRightView;
    private RelativeLayout rlLeftView;

    public CommonToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    /**
     * 初始化视图数据
     *
     * @param context:
     * @param attrs:
     */
    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this);
        rlToolbarRoot = inflate.findViewById(R.id.rl_toolbar_root);
        tvToolbarTitle = inflate.findViewById(R.id.tv_toolbar_title);

        rlLeftView = inflate.findViewById(R.id.rl_left_view);
        tvToolbarLeft = inflate.findViewById(R.id.tv_toolbar_left);
        ivToolbarLeft = inflate.findViewById(R.id.iv_toolbar_left);
        flToolbarLeft = inflate.findViewById(R.id.fl_toolbar_left);


        rlRightView = inflate.findViewById(R.id.rl_right_view);
        tvToolbarRight = inflate.findViewById(R.id.tv_toolbar_right);
        ivToolbarRight = inflate.findViewById(R.id.iv_toolbar_right);
        flToolbarRight = inflate.findViewById(R.id.fl_toolbar_right);
        viewLine = inflate.findViewById(R.id.view_line);
        //获取属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolbar);
        //背景颜色类型
        int rootBackground = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarBgColor, R.color.white);
        //标题文字
        String strTitle = typedArray.getString(R.styleable.CommonToolbar_toolbarTitle);
        //标题文字颜色
        int titleColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarTitleColor, getResources().getColor(R.color.black));
        //左边图片
        int leftImg = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarLeftImg, R.drawable.ic_back);
        //左边文字
        String leftText = typedArray.getString(R.styleable.CommonToolbar_toolbarLeftText);
        //左边文字颜色
        int leftTextColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarLeftTextColor, getResources().getColor(R.color.black));
        //右边图片
        int rightImg = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarRightImg, R.drawable.ic_release);
        //右边文字
        String rightText = typedArray.getString(R.styleable.CommonToolbar_toolbarRightText);
        //右边文字颜色
        int rightTextColor = typedArray.getColor(R.styleable.CommonToolbar_toolbarRightTextColor, getResources().getColor(R.color.black));
        //右边布局类型
        int rightType = typedArray.getInteger(R.styleable.CommonToolbar_toolbarRightType, RIGHT_TYPE_NONE);
        //左边布局类型
        int LeftType = typedArray.getInteger(R.styleable.CommonToolbar_toolbarLeftType, LEFT_TYPE_IMAGE);
        //是否显示分割线
        int viewLineIsVisiblity = typedArray.getInteger(R.styleable.CommonToolbar_viewLineIsVisiblity, GONE);
        typedArray.recycle();


        setTitle(strTitle);
        tvToolbarTitle.setTextColor(titleColor);

        //设置左边布局类型
        setLeftViewType(LeftType);
        //设置右边布局类型
        setRightViewType(rightType);

        setLeftText(leftText);
        setLeftImg(leftImg);
        setLeftTextColor(leftTextColor);

        setRightText(rightText);
        setRightTextColor(rightTextColor);
        setRightImg(rightImg);
        //分割线是否显示
        setViewLineIsVisiblity(viewLineIsVisiblity);
        //左边View点击监听
        rlLeftView.setOnClickListener(v -> {
            if (onLeftClickListener != null)
                onLeftClickListener.onLeftClick(v);
        });
        //右边View点击监听
        rlRightView.setOnClickListener(v -> {
            if (onRightClickListener != null)
                onRightClickListener.onRightClick(v);
        });
    }

    /**
     * 设置背景颜色
     * @param bgColor
     * @return
     */
    public CommonToolbar setBgColor(int bgColor){
        rlToolbarRoot.setBackgroundColor(bgColor);
        return this;
    }

    /**
     * 左边布局类型
     *
     * @param leftType :左边布局类型
     */
    public void setLeftViewType(int leftType) {
        tvToolbarLeft.setVisibility(leftType == LEFT_TYPE_TEXT ? VISIBLE : GONE);
        ivToolbarLeft.setVisibility(leftType == LEFT_TYPE_IMAGE ? VISIBLE : GONE);
        flToolbarLeft.setVisibility(leftType == LEFT_TYPE_VIEW ? VISIBLE : GONE);
    }

    /**
     * 右边布局类型
     *
     * @param rightType :右边布局类型
     */
    public void setRightViewType(int rightType) {
        tvToolbarRight.setVisibility(rightType == RIGHT_TYPE_TEXT ? VISIBLE : GONE);
        ivToolbarRight.setVisibility(rightType == RIGHT_TYPE_IMAGE ? VISIBLE : GONE);
        flToolbarRight.setVisibility(rightType == RIGHT_TYPE_VIEW ? VISIBLE : GONE);
    }

    /**
     * 分割线是否显示
     *
     * @param isVisiblity:类型
     */
    public void setViewLineIsVisiblity(int isVisiblity) {
        viewLine.setVisibility(isVisiblity == VIEW_GONE ? View.GONE : View.VISIBLE);
    }

    /**
     * 设置标题
     *
     * @param title:标题
     */
    public CommonToolbar setTitle(String title) {
        tvToolbarTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @param titleColor:标题颜色
     */
    public CommonToolbar setTitleColor(int titleColor) {
        tvToolbarTitle.setTextColor(getResources().getColor(titleColor));
        return this;
    }

    /**
     * 设置左边图片
     *
     * @param leftImg:
     */
    public CommonToolbar setLeftImg(int leftImg) {
        ivToolbarLeft.setImageDrawable(getResources().getDrawable(leftImg));
        return this;
    }

    /**
     * 设置左边文字
     *
     * @param leftText:左边文字
     */
    public CommonToolbar setLeftText(String leftText) {
        tvToolbarLeft.setText(leftText);
        return this;
    }

    /**
     * 设置左边文字颜色
     *
     * @param leftTextColor:左边文字
     */
    public CommonToolbar setLeftTextColor(int leftTextColor) {
        tvToolbarLeft.setTextColor(leftTextColor);
        return this;
    }

    /**
     * 设置左边view
     *
     * @param view:左边布局
     */
    public CommonToolbar setLeftView(View view) {
        flToolbarLeft.removeAllViews();
        flToolbarLeft.addView(view);
        return this;
    }

    public View getLeftView() {
        return flToolbarRight;
    }

    /**
     * 设置右边文字
     *
     * @param rightText:右边文字
     */
    public CommonToolbar setRightText(String rightText) {
        tvToolbarRight.setText(rightText);
        return this;
    }

    /**
     * 设置右边文字颜色
     *
     * @param rightTextColor:右边文字颜色
     */
    public CommonToolbar setRightTextColor(int rightTextColor) {
        tvToolbarRight.setTextColor(rightTextColor);
        return this;
    }

    /**
     * 设置右边图片
     *
     * @param rightImg:
     */
    public CommonToolbar setRightImg(int rightImg) {
        ivToolbarRight.setImageDrawable(getResources().getDrawable(rightImg));
        return this;
    }

    /**
     * 设置右边view
     *
     * @param view:右边布局
     */
    public CommonToolbar setRightView(View view) {
        flToolbarRight.removeAllViews();
        flToolbarRight.addView(view);
        return this;
    }

    public View getRightView() {
        return flToolbarRight;
    }

    /**
     * 左边按钮监听
     */
    public interface OnLeftClickListener {
        void onLeftClick(View v);
    }

    /**
     * 右边按钮监听
     */
    public interface OnRightClickListener {
        void onRightClick(View v);
    }

    public CommonToolbar setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
        return this;
    }

    public CommonToolbar setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
        return this;
    }
}
