package com.library.glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.common.base.BaseActivity;
import com.common.util.glide.GlideUtil;
import com.library.R;

/**
 * Glide 使用
 */
public class GlideUseActivity extends BaseActivity {

    private static final String imgUrl = "https://library-collection.oss-cn-beijing.aliyuncs.com/img/glideuse.png";

    private ImageView ivDefault;
    private ImageView ivCircle;
    private ImageView ivCorner;

    @Override
    public int getLayout() {
        return R.layout.activity_glide_use;
    }

    @Override
    public void initViewIds() {
        ivDefault = findViewById(R.id.iv_default);
        ivCircle = findViewById(R.id.iv_circle);
        ivCorner = findViewById(R.id.iv_corner);
    }

    @Override
    public void initView() {
        GlideUtil.loadImage(imgUrl, ivDefault, getResources().getDrawable(R.drawable.ic_placeholder));
        GlideUtil.loadCircleImage(imgUrl, ivCircle, getResources().getDrawable(R.drawable.ic_placeholder));
        GlideUtil.loadCornerImage(imgUrl, ivCorner, 20, getResources().getDrawable(R.drawable.ic_placeholder));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
