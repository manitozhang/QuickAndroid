package com.library.toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private boolean colorFlag = true;
    private boolean viewLineFlag = true;
    private boolean bgColorFlag = true;

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
        toolbar.setOnRightClickListener(v -> {
            ToastUtils.showShort("点击了右边View");
        });
    }

    /**
     * 左边什么也不显示
     *
     * @param view
     */
    public void btnLeftNone(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_NONE);
    }

    /**
     * 左边图片
     *
     * @param view
     */
    public void btnLeftImg(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_IMAGE);
        toolbar.setLeftImg(R.drawable.ic_back);
    }

    /**
     * 左边文字
     *
     * @param view
     */
    public void btnLeftText(View view) {
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_TEXT);
        toolbar.setLeftText("返回");
    }

    /**
     * 左边view
     *
     * @param view
     */
    public void btnLeftView(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_left_view, null);
        toolbar.setLeftView(inflate);
        toolbar.setLeftViewType(CommonToolbar.LEFT_TYPE_VIEW);
    }

    /**
     * 右边文字
     *
     * @param view
     */
    public void btnRightText(View view) {
        toolbar.setRightText("更多");
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_TEXT);
    }

    /**
     * 右边图片
     *
     * @param view
     */
    public void btnRightImg(View view) {
        toolbar.setRightImg(R.drawable.ic_release);
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_IMAGE);
    }

    /**
     * 右边View
     *
     * @param view
     */
    public void btnRightView(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.toolbar_my_right_view, null);
        toolbar.setRightView(inflate);
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_VIEW);
    }

    /**
     * 右边什么也不显示
     *
     * @param view
     */
    public void btnRightNone(View view) {
        toolbar.setRightViewType(CommonToolbar.RIGHT_TYPE_NONE);
    }

    /**
     * 改变标题颜色
     *
     * @param view
     */
    public void btnTitleColor(View view) {
        toolbar.setTitleColor(colorFlag ? R.color.colorMain : R.color.black);
        colorFlag = !colorFlag;
    }

    /**
     * 改变标题
     * @param view
     */
    public void btnTitle(View view) {
        toolbar.setTitle("标题改变了");
    }

    /**
     * 分割线显示隐藏
     * @param view
     */
    public void btnViewVisible(View view) {
        toolbar.setViewLineIsVisiblity(viewLineFlag?CommonToolbar.VIEW_GONE:CommonToolbar.VIEW_VISIBLE);
        viewLineFlag = !viewLineFlag;
    }

    /**
     * 改变背景颜色
     * @param view
     */
    public void btnbgColor(View view) {
        toolbar.setBgColor(getResources().getColor(bgColorFlag?R.color.colorMain:R.color.white));
        bgColorFlag = !bgColorFlag;
    }
}
