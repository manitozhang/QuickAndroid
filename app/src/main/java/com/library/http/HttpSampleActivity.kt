package com.library.http

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ClipboardUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.common.base.BaseActivity
import com.common.http.XRetrofit.api
import com.common.http.base.BaseObserver
import com.common.http.base.BaseResponse
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleBean
import com.common.http.helper.Mobile.commonParamsMap
import com.common.http.helper.RequestBodyHelper.getRequestBody
import com.common.http.helper.upload.OnUploadFileListener
import com.common.http.helper.upload.UploadFileHelper
import com.common.util.GetPathFromUri.getRealPathFromUri
import com.common.util.ParamUtil.getEditStr
import com.library.R

/**
 * 网络相关操作的类
 */
class HttpSampleActivity : BaseActivity() {
    private var etPassword: EditText? = null
    private var etUsername: EditText? = null
    private var tvResultData: TextView? = null
    override val layout: Int
        get() = R.layout.activity_http_sample

    override fun initViewIds() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        tvResultData = findViewById(R.id.tv_result_data)
    }

    override fun initView() {}
    override fun initData(savedInstanceState: Bundle?) {}
    /**
     * get请求
     *
     * @param view
     */
    fun btnGet(view: View?) {
        api!!.testGet(commonParamsMap(getEditStr(etUsername!!), getEditStr(etPassword!!)))
                ?.compose(observeOnMainThread())
                ?.subscribe({ list ->
                    renderResultView("data数据为:\n$list")
                })
    }

    /**
     * post请求
     *
     * @param view
     */
    fun btnPost(view: View?) {
        api!!.testPost(commonParamsMap(getEditStr(etUsername!!), getEditStr(etPassword!!)))
                ?.compose(observeOnMainThread())
                ?.subscribe({ response ->
                    renderResultView("data无数据,总数据为:\n$response")
                })
    }

    /**
     * put请求
     *
     * @param view
     */
    fun btnPut(view: View?) {
        api!!.testPut(commonParamsMap(getEditStr(etUsername!!), getEditStr(etPassword!!)))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<Any>() {

                    override fun onSuccess(response: Any) {

                    }

                    override fun onSuccess(baseResponse: BaseResponse<Any>) {
                        super.onSuccess(baseResponse)
                        renderResultView("后台返回错误状态码:\n$baseResponse")
                    }
                })
    }

    /**
     * delete请求
     *
     * @param view
     */
    fun btnDelete(view: View?) {
        api?.testDelete(commonParamsMap(getEditStr(etUsername!!), getEditStr(etPassword!!)))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<ExampleBean>() {

                    override fun onSuccess(response: ExampleBean) {
                    }

                    override fun onSuccess(baseResponse: BaseResponse<ExampleBean>) {
                        super.onSuccess(baseResponse)
                        renderResultView("数据为:\n" + baseResponse.toString())
                    }
                })
    }

    /**
     * 提交json格式数据
     *
     * @param view
     */
    fun btnPostJson(view: View?) {
        api?.testPostJson(getRequestBody(commonParamsMap(getEditStr(etUsername!!), getEditStr(etPassword!!))))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<ExampleBean>() {
                    override fun onSuccess(response: ExampleBean) {
                        renderResultView("data数据为:\n$response")
                    }
                })
    }

    /**
     * 上传文件
     *
     * @param view
     */
    fun btnUploadFile(view: View?) {
        if (PermissionUtils.isGranted(PermissionConstants.STORAGE)) { //有权限
            openAlbum()
        } else { //没有权限
            val permission = PermissionUtils.permission(PermissionConstants.STORAGE)
            permission.request()
            permission.callback(object : PermissionUtils.SimpleCallback {
                override fun onGranted() { //同意
                    openAlbum()
                }

                override fun onDenied() {
                    ToastUtils.showShort("请开启文件读写权限")
                }
            })
        }
    }

    /**
     * 打开相册
     */
    private fun openAlbum() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.type = "image/*"
        try {
            startActivityForResult(intent, 101)
        } catch (e: Exception) {
            ToastUtils.showShort("该设备不支持相册功能")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val photoPath: String?
        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null) {
            photoPath = getRealPathFromUri(this, data.data!!)
            uploadFile(photoPath)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * 文件上传
     * @param photoPath
     */
    private fun uploadFile(photoPath: String?) {
        UploadFileHelper.uploadFile(this, photoPath, object : OnUploadFileListener {
            override fun onUploadFileSuccess(imgUrl: String?) {
                renderResultView("文件上传成功:\n$imgUrl\n\n\n已复制到剪切板")
                ClipboardUtils.copyText(imgUrl)
            }

            override fun onUploadFileFailed(errorMsg: String?) {
                renderResultView("文件上传失败:\n$errorMsg")
            }
        })
    }

    /**
     * 渲染返回数据View
     *
     * @param str
     */
    private fun renderResultView(str: String) {
        tvResultData!!.text = str
    }
}