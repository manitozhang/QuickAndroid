package com.library;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.common.base.BaseActivity;
import com.library.http.HttpSampleActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewIds() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 网络请求相关
     *
     * @param view
     */
    public void btnHttp(View view) {
        startActivity(new Intent(this, HttpSampleActivity.class));
    }
}
