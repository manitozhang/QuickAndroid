package com.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.common.base.BaseActivity;
import com.library.filedownload.FileDownloadActivity;
import com.library.glide.GlideUseActivity;
import com.library.http.HttpSampleActivity;
import com.library.lazyload.LazyLoadActivity;
import com.library.list.multiplechoice.MultipleChoiceListActivity;
import com.library.list.refreshload.RefreshLoadListActivity;
import com.library.list.singlechoice.SingleChoiceListActivity;
import com.library.login.LoginActivity;
import com.library.mainpage.MainPageActivity;
import com.library.toolbar.CommonToolbarActivity;

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

    /**
     * 封装的标题栏
     * @param view
     */
    public void btnCommonToolbar(View view) {
        startActivity(new Intent(this, CommonToolbarActivity.class));
    }

    /**
     * Glide操作
     * @param view
     */
    public void btnGlideClip(View view) {
        startActivity(new Intent(this, GlideUseActivity.class));
    }

    /**
     * 文件下载
     * @param view
     */
    public void btnDownload(View view) {
        startActivity(new Intent(this, FileDownloadActivity.class));
    }

    /**
     * 列表刷新加载
     * @param view
     */
    public void btnListRefresh(View view) {
        startActivity(new Intent(this , RefreshLoadListActivity.class));
    }

    /**
     * 列表单选
     * @param view
     */
    public void btnListSingleChoice(View view) {
        startActivity(new Intent(this , SingleChoiceListActivity.class));
    }

    /**
     * 列表多选
     * @param view
     */
    public void btnListMultipleChoice(View view) {
        startActivity(new Intent(this , MultipleChoiceListActivity.class));
    }

    /**
     * Mvp示例
     * @param view
     */
    public void btnMvp(View view) {
        startActivity(new Intent(this , LoginActivity.class));
    }
}
