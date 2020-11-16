package com.library.filedownload;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.common.base.BaseActivity;
import com.common.util.BitmapUtil;
import com.common.util.filedownload.FileDownloadHelper;
import com.common.util.filedownload.OnDownloadListener;
import com.library.R;

/**
 * 文件下载页面
 */
public class FileDownloadActivity extends BaseActivity {

    private static final String fileUrl = "https://library-collection.oss-cn-beijing.aliyuncs.com/file/file.zip";
    private int ids;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvSpeed;
    private FileDownloadHelper fileDownloadHelper;

    @Override
    public int getLayout() {
        return R.layout.activity_file_download;
    }

    @Override
    public void initViewIds() {
        progressBar = findViewById(R.id.progress_bar);
        tvProgress = findViewById(R.id.tv_progress);
        tvSpeed = findViewById(R.id.tv_speed);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 开始下载
     *
     * @param view
     */
    public void btnStart(View view) {
        startDownload();
    }

    /**
     * 开始下载
     */
    private void startDownload(){
        fileDownloadHelper = new FileDownloadHelper();
        fileDownloadHelper.downloadFile(fileUrl, BitmapUtil.getFileStoragePath(), false, new OnDownloadListener() {

            @Override
            public void onPending(int id, int soFarBytes, int totalBytes) {
            }

            @Override
            public void onProgress(int id, int speed, int soFarBytes, int totalBytes) {
                tvProgress.setText(soFarBytes + " / " + totalBytes);
                tvSpeed.setText(speed + " KB/s");
                progressBar.setMax(totalBytes);
                progressBar.setProgress(soFarBytes);
            }

            @Override
            public void onComplete(String path) {
                ToastUtils.showShort("下载完成");
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("下载异常" + e.getMessage());
            }
        });
    }

    /**
     * 暂停下载
     *
     * @param view
     */
    public void btnPause(View view) {
        fileDownloadHelper.pause();
    }
}
