package com.common.util.filedownload;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/8/16 6:05 PM
 * <p>
 * 下载监听回调
 */
public interface OnDownloadListener {

    void onPending(int id, int soFarBytes, int totalBytes);

    void onProgress(int id, int speed, int soFarBytes, int totalBytes);

    void onComplete(String path);

    void onError(Throwable e);

}
