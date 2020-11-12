package com.common.http.helper.upload;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:34 PM
 * <p>
 * 文件上传结果监听
 */

public interface OnUploadFileListener {
    //上传成功
    void onUploadFileSuccess(String imgUrl);

    //上传失败
    void onUploadFileFailed(String errorMsg);
}