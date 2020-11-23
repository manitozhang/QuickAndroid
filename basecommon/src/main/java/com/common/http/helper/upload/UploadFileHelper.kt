package com.common.http.helper.upload

import android.content.Context
import com.common.http.XRetrofit.api
import com.common.http.base.BaseObserver
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleFileBean
import com.common.http.helper.CompressHelper.Companion.getDefault
import com.common.http.helper.Mobile.commonParamsMap
import com.common.util.BitmapUtil.deleteImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/7/22 2:29 PM
 *
 *
 * 文件上传辅助类
 */
object UploadFileHelper {
    /**
     * 上传文件
     *
     * @param context:
     * @param imgPath; 图片路径
     */
    fun uploadFile(context: Context?, imgPath: String?, onUploadFileListener: OnUploadFileListener?) {
        var fileName: String? = "file_name.png"
        //压缩图片
        val uploadFile = getDefault(context!!)!!.compressToFile(File(imgPath))
        val body = MultipartBody.create(MediaType.parse("multipart/form-data"), uploadFile)
        try {
            fileName = URLEncoder.encode(uploadFile.name, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        //file 是后台定义的文件的参数名
        val part = MultipartBody.Part.createFormData("file", fileName, body)
        api?.testUploadFile(part, commonParamsMap("参数1", "参数2"))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<ExampleFileBean>(context) {
                    override fun onSuccess(response: ExampleFileBean) {
                        if (response == null) {
                            onUploadFileListener?.onUploadFileFailed("服务器返回为空")
                            return
                        }
                        //上传成功,返回在线url地址
                        onUploadFileListener?.onUploadFileSuccess(response.fileUrl)
                        //删除压缩的文件
                        deleteImage(context, uploadFile)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        //上传失败
                        onUploadFileListener?.onUploadFileFailed(e.message)
                        //删除压缩的文件
                        deleteImage(context, uploadFile)
                    }
                })
    }
}