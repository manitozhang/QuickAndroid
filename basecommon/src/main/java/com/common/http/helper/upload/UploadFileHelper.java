package com.common.http.helper.upload;

import android.content.Context;

import com.common.http.XRetrofit;
import com.common.http.base.BaseObserver;
import com.common.http.base.RxJavaHelper;
import com.common.http.bean.ExampleFileBean;
import com.common.http.helper.CompressHelper;
import com.common.http.helper.Mobile;
import com.common.util.BitmapUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:29 PM
 * <p>
 * 文件上传辅助类
 */
public class UploadFileHelper {

    /**
     * 上传文件
     *
     * @param context:
     * @param imgPath; 图片路径
     */
    public static void uploadFile(Context context, String imgPath, OnUploadFileListener onUploadFileListener) {
        String fileName = "file_name.png";
        //压缩图片
        File uploadFile = CompressHelper.getDefault(context).compressToFile(new File(imgPath));
        RequestBody body = MultipartBody.create(MediaType.parse("multipart/form-data"), uploadFile);
        try {
            fileName = URLEncoder.encode(uploadFile.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //file 是后台定义的文件的参数名
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", fileName, body);
        XRetrofit.getApi()
                .testUploadFile(part, Mobile.commonParamsMap("参数1", "参数2"))
                .compose(new RxJavaHelper().observeOnMainThread())
                .subscribe(new BaseObserver<ExampleFileBean>(context) {
                    @Override
                    public void onSuccess(ExampleFileBean response) {
                        if (response == null) {
                            if (onUploadFileListener != null)
                                onUploadFileListener.onUploadFileFailed("服务器返回为空");
                            return;
                        }
                      //上传成功,返回在线url地址
                        if (onUploadFileListener != null)
                            onUploadFileListener.onUploadFileSuccess(response.getFileUrl());
                        //删除压缩的文件
                        BitmapUtil.deleteImage(context, uploadFile);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        //上传失败
                        if (onUploadFileListener != null)
                            onUploadFileListener.onUploadFileFailed(e.getMessage());
                        //删除压缩的文件
                        BitmapUtil.deleteImage(context, uploadFile);
                    }
                });
    }

}
