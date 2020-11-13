package com.library;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ConvertUtils;
import com.common.base.BaseActivity;
import com.common.util.BitmapUtil;
import com.library.http.HttpSampleActivity;
import com.library.lazyload.LazyLoadActivity;
import com.library.mainpage.MainPageActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewIds() {
        Button btnView = findViewById(R.id.btnView);
        ConvertUtils.view2Bitmap(btnView);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 首页框架
     * @param view
     */
    public void btnMainPage(View view){
        startActivity(new Intent(this, MainPageActivity.class));
    }

    /**
     * 网络请求相关
     *
     * @param view
     */
    public void btnHttp(View view) {
        startActivity(new Intent(this, HttpSampleActivity.class));
    }

    /**
     * 懒加载
     * @param view
     */
    public void btnLazyLoad(View view) {
        startActivity(new Intent(this, LazyLoadActivity.class));
    }

    public void btnCommonToolbar(View view) {
    }

    public void btnGlideClip(View view) {
    }

    public void btnDownload(View view) {
    }
}
