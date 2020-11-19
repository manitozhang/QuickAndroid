package com.common.http.base

import android.content.Context
import android.net.ParseException
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.common.R
import com.common.http.bean.ExampleBean
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 实现Observer 来统一处理,拦截
 */
abstract class BaseObserver<T> : Observer<BaseResponse<T>> {
    private var context: Context? = null
    private val loadingDialog: AlertDialog? = null
    private var disposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    constructor() : super() {}
    /**
     * 需要弹窗,实例化此方法
     *
     * @param context
     */
    constructor(context: Context?) {
        this.context = context
        //弹窗在这里写,每次调用网络都实现这个方法
//        if (loadingDialog == null)
//            new AlertDialog.Builder(context);
//        loadingDialog.show();
    }

    override fun onNext(response: BaseResponse<T>) {
        loadingDialog?.dismiss()
        response.data?.let { onSuccess(it) }
        if (response.isSuccess) {
            response.data?.let { onSuccess(it) }
        } else {
            ToastUtils.showShort(response.msg)
        }
    }

    override fun onError(e: Throwable) {
        loadingDialog?.dismiss()
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort(R.string.error_net)
        } else if (e is HttpException
                || e is UnknownHostException
                || e is SocketException) { //服务器错误
            ToastUtils.showShort(R.string.error_server)
        } else if (e is SocketTimeoutException) { //连接超时等
            ToastUtils.showShort(R.string.error_time_out)
        } else if (e is JSONException
                || e is JsonParseException
                || e is ParseException) { //数据解析异常
            ToastUtils.showShort(R.string.error_data_parse)
        } else { //未知异常
            ToastUtils.showShort(R.string.error_unknow)
        }
    }

    override fun onComplete() {}
    /**
     * 请求成功 只返回data的数据
     *
     * @param response: 数据
     */
    abstract fun onSuccess(response: T)

    /**
     * 请求成功 需要单独处理状态码时实现该方法
     *
     * @param baseResponse: 带状态码的数据
     */
    open fun onSuccess(baseResponse: BaseResponse<T>) {}

}