package com.common.http.helper.upload

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:34 PM
 *
 *
 * 文件上传结果监听
 */
interface OnUploadFileListener {
    //上传成功
    fun onUploadFileSuccess(imgUrl: String?)

    //上传失败
    fun onUploadFileFailed(errorMsg: String?)
}