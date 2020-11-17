package com.library.toolbar;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.common.base.BaseActivity;
import com.common.weight.CommonToolbar;
import com.library.R;

/**
 * 公共标题栏
 */
public class CommonToolbarActivity extends BaseActivity {

    private CommonToolbar toolbar;
    private boolean titleColorFlag = true;
    private boolean dividerFlag = true;
    private boolean dividerColorFlag = true;
    private boolean bgColorFlag = true;
    private boolean titleSizeFlag = true;
    private boolean leftColorFlag = true;
    private boolean rightColorFlag = true;
    private boolean rightSizeFlag = true;
    private boolean leftSizeFlag = true;
    private boolean leftImgWidthHeightFlag = true;
    private boolean rightImgWidthHeightFlag = true;
    private boolean titleImgWidthHeightFlag = true;

    @Override
    public int getLayout() {
        return R.layout.activity_common_toolbar;
    }

    @Override
    public void initViewIds() {
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        super.initListener();
        toolbar.setOnLeftClickListener(v -> {
            ToastUtils.showShort("点击了左边View");
        });
        toolbar.setOnTitleClickListener(v -> {
            ToastUtils.showShort("点击了标题View");
        });
        toolbar.setOnRightClickListener(v -> {
            ToastUtils.showShort("点击了右边View");
        });
    }

    /**
     * 左边什么也不显示
     *
     * @param view:
     */
    public void btnLeftNone(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_NONE);
    }

    /**
     * 左边图片
     *
     * @param view:
     */
    public void btnLeftImg(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_IMAGE);
        toolbar.setLeftImg(R.drawable.ic_back);
    }

    /**
     * 左边文字
     *
     * @param view:
     */
    public void btnLeftText(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_TEXT);
        toolbar.setLeftText("返回");
    }

    /**
     * 左边view
     *
     * @param view:
     */
    public void btnLeftView(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_left_view, null);
        toolbar.setLeftView(inflate);
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_VIEW);
    }

    /**
     * 右边文字
     *
     * @param view:
     */
    public void btnRightText(View view) {
        toolbar.setRightText("更多");
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_TEXT);
    }

    /**
     * 右边图片
     *
     * @param view:
     */
    public void btnRightImg(View view) {
        toolbar.setRightImg(R.drawable.ic_release);
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_IMAGE);
    }

    /**
     * 右边View
     *
     * @param view:
     */
    public void btnRightView(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_right_view, null);
        toolbar.setRightView(inflate);
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_VIEW);
    }

    /**
     * 右边什么也不显示
     *
     * @param view:
     */
    public void btnRightNone(View view) {
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_NONE);
    }

    /**
     * 改变标题颜色
     *
     * @param view:
     */
    public void btnTitleColor(View view) {
        toolbar.setTitleColor(titleColorFlag ? R.color.colorMain : R.color.black);
        titleColorFlag = !titleColorFlag;
    }

    /**
     * 分割线显示隐藏
     *
     * @param view:
     */
    public void btnDividerVisiblity(View view) {
        toolbar.setDividerIsVisiblity(dividerFlag ? CommonToolbar.VIEW_GONE : CommonToolbar.VIEW_VISIBLE);
        dividerFlag = !dividerFlag;
    }

    /**
     * 分割线颜色
     *
     * @param view:
     */
    public void btnDividerColor(View view) {
        toolbar.setDividerColor(dividerColorFlag ? R.color.colorPrimary : R.color.black);
        dividerColorFlag = !dividerColorFlag;
    }

    /**
     * 改变背景颜色
     *
     * @param view:
     */
    public void btnbgColor(View view) {
        toolbar.setBackgroundRes(bgColorFlag ? R.color.colorMain : R.color.white);
        bgColorFlag = !bgColorFlag;
    }

    /**
     * 改变文字大小
     *
     * @param view:
     */
    public void btnTitleSize(View view) {
        toolbar.setTitleSize(titleSizeFlag ? 15 : 18);
        titleSizeFlag = !titleSizeFlag;
    }

    /**
     * 左边文字大小
     *
     * @param view:
     */
    public void btnLeftTextSize(View view) {
        toolbar.setLeftTextSize(leftSizeFlag ? 18 : 15);
        leftSizeFlag = !leftSizeFlag;
    }

    /**
     * 左边文字颜色
     *
     * @param view:
     */
    public void btnLeftTextColor(View view) {
        toolbar.setLeftTextColor(leftColorFlag ? R.color.colorMain : R.color.black);
        leftColorFlag = !leftColorFlag;
    }

    /**
     * 左边图片大小
     *
     * @param view:
     */
    public void btnLeftImgWidthHeight(View view) {
        toolbar.setLeftImgWidth(leftImgWidthHeightFlag ? 50 : 30);
        toolbar.setLeftImgHeight(leftImgWidthHeightFlag ? 50 : 30);
        leftImgWidthHeightFlag = !leftImgWidthHeightFlag;
    }

    /**
     * 右边文字大小
     *
     * @param view:
     */
    public void btnRightTextSize(View view) {
        toolbar.setRightTextSize(rightSizeFlag ? 18 : 15);
        rightSizeFlag = !rightSizeFlag;
    }

    /**
     * 右边文字颜色
     *
     * @param view:
     */
    public void btnRightTextColor(View view) {
        toolbar.setRightTextColor(rightColorFlag ? R.color.colorMain : R.color.black);
        rightColorFlag = !rightColorFlag;
    }

    /**
     * 右边图片大小
     *
     * @param view:
     */
    public void btnRightImgWidthHeight(View view) {
        toolbar.setRightImgWidth(rightImgWidthHeightFlag ? 50 : 30);
        toolbar.setRightImgHeight(rightImgWidthHeightFlag ? 50 : 30);
        rightImgWidthHeightFlag = !rightImgWidthHeightFlag;
    }

    /**
     * 标题什么都没有
     * @param view
     */
    public void btnTitleNone(View view) {
        toolbar.setTitleViewType(CommonToolbar.TITLE_TYPE_NONE);
    }

    /**
     * 标题是图片
     * @param view
     */
    public void btnTitleImg(View view) {
        toolbar.setTitleViewType(CommonToolbar.TITLE_TYPE_IMAGE);
    }

    /**
     * 标题是文字
     * @param view
     */
    public void btnTitleText(View view) {
        toolbar.setTitle("标题");
        toolbar.setTitleViewType(CommonToolbar.TITLE_TYPE_TEXT);
    }

    /**
     * 标题是布局
     * @param view
     */
    public void btnTitleView(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_title_view, null);
        toolbar.setTitleView(inflate);
        toolbar.setTitleViewType(CommonToolbar.TITLE_TYPE_VIEW);
    }

    /**
     * 设置标题图片大小
     * @param view
     */
    public void btnTitleImgWidthHeight(View view) {
        toolbar.setTitleImgWidth(titleImgWidthHeightFlag ? 50 : 30);
        toolbar.setTitleImgHeight(titleImgWidthHeightFlag ? 50 : 30);
        titleImgWidthHeightFlag = !titleImgWidthHeightFlag;
    }
}
