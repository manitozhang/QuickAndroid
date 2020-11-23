package com.common.http.helper.filedownload

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/8/16 6:05 PM
 *
 *
 * 下载监听回调
 */
interface OnDownloadListener {
    fun onPending(id: Int, soFarBytes: Int, totalBytes: Int)
    fun onProgress(id: Int, speed: Int, soFarBytes: Int, totalBytes: Int)
    fun onComplete(path: String?)
    fun onError(e: Throwable?)
}