package com.common.http.helper.filedownload

import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/8/16 5:17 PM
 *
 *
 * 文件下载辅助类
 */
class FileDownloadHelper {
    private var baseDownloadTask: BaseDownloadTask? = null
    /**
     * 下载文件
     *
     * @param downloadUrl:        下载地址
     * @param savePath:           保存路径
     * @param isForceReDownload:  是否强制重新下载
     * @param onDownloadListener: 监听
     */
    fun downloadFile(downloadUrl: String, savePath: String, isForceReDownload: Boolean, onDownloadListener: OnDownloadListener) {
        if (PermissionUtils.isGranted(PermissionConstants.STORAGE)) { //有权限
            startDownload(downloadUrl, savePath, isForceReDownload, onDownloadListener)
        } else { //没有权限
            val permission = PermissionUtils.permission(PermissionConstants.STORAGE)
            permission.request()
            permission.callback(object : PermissionUtils.SimpleCallback {
                override fun onGranted() { //同意
                    startDownload(downloadUrl, savePath, isForceReDownload, onDownloadListener)
                }

                override fun onDenied() {
                    ToastUtils.showShort("请开启文件读写权限")
                }
            })
        }
    }

    private fun startDownload(downloadUrl: String, savePath: String, isForceReDownload: Boolean, onDownloadListener: OnDownloadListener) {
        val split = downloadUrl.split("\\.").toTypedArray()
        val fileName = "downloadFile." + split[split.size - 1]
        val filePath = savePath + fileName
        baseDownloadTask = FileDownloader.getImpl().create(downloadUrl)
                .setPath(filePath)
                .setCallbackProgressTimes(300)
                .setForceReDownload(isForceReDownload)
                .setMinIntervalUpdateSpeed(400)
                .setListener(object : FileDownloadListener() {
                    override fun pending(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {}
                    override fun progress(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {
                        LogUtils.e("speed---" + task.speed)
                        onDownloadListener.onProgress(task.id, task.speed, soFarBytes, totalBytes)
                    }

                    override fun completed(task: BaseDownloadTask) {
                        onDownloadListener.onComplete(filePath)
                    }

                    override fun paused(task: BaseDownloadTask, soFarBytes: Int, totalBytes: Int) {}
                    override fun error(task: BaseDownloadTask, e: Throwable) {
                        onDownloadListener.onError(e)
                    }

                    override fun warn(task: BaseDownloadTask) {}
                })
        baseDownloadTask?.start()
    }

    /**
     * 暂停下载
     */
    fun pause() {
        baseDownloadTask!!.pause()
    }
}