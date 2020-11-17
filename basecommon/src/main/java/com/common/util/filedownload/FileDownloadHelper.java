package com.common.util.filedownload;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/8/16 5:17 PM
 * <p>
 * 文件下载辅助类
 */
public class FileDownloadHelper {

    private BaseDownloadTask baseDownloadTask;

    /**
     * 下载文件
     *
     * @param downloadUrl:        下载地址
     * @param savePath:           保存路径
     * @param isForceReDownload:  是否强制重新下载
     * @param onDownloadListener: 监听
     */
    public void downloadFile(String downloadUrl, String savePath, boolean isForceReDownload, OnDownloadListener onDownloadListener) {
        if (PermissionUtils.isGranted(PermissionConstants.STORAGE)) {
            //有权限
            startDownload(downloadUrl, savePath, isForceReDownload, onDownloadListener);
        } else {
            //没有权限
            PermissionUtils permission = PermissionUtils.permission(PermissionConstants.STORAGE);
            permission.request();
            permission.callback(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    //同意
                    startDownload(downloadUrl, savePath, isForceReDownload, onDownloadListener);
                }

                @Override
                public void onDenied() {
                    ToastUtils.showShort("请开启文件读写权限");
                }
            });
        }
    }


    private void startDownload(String downloadUrl, String savePath, boolean isForceReDownload, OnDownloadListener onDownloadListener) {
        String[] split = downloadUrl.split("\\.");
        String fileName = "downloadFile." + split[split.length - 1];
        String filePath = savePath + fileName;
        baseDownloadTask = FileDownloader.getImpl().create(downloadUrl)
                .setPath(filePath)
                .setCallbackProgressTimes(300)
                .setForceReDownload(isForceReDownload)
                .setMinIntervalUpdateSpeed(400)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.e("speed---" + task.getSpeed());
                        onDownloadListener.onProgress(task.getId(), task.getSpeed(), soFarBytes, totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        onDownloadListener.onComplete(filePath);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        onDownloadListener.onError(e);
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                });
        baseDownloadTask.start();
    }

    /**
     * 暂停下载
     */
    public void pause() {
        baseDownloadTask.pause();
    }

}
