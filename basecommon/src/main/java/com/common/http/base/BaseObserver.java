
package com.common.http.base;


import android.content.Context;
import android.net.ParseException;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.common.R;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: 实现Observer 来统一处理,拦截
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private Context context;
    private AlertDialog loadingDialog;
    private Disposable disposable;


    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    public BaseObserver() {
        super();
    }

    /**
     * 需要弹窗,实例化此方法
     *
     * @param context
     */
    public BaseObserver(Context context) {
        this.context = context;
        //弹窗在这里写,每次调用网络都实现这个方法
//        if (loadingDialog == null)
//            new AlertDialog.Builder(context);
//        loadingDialog.show();
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (loadingDialog != null)
            loadingDialog.dismiss();
        onSuccess(response.getData());
        if (response.isSuccess()) {
            onSuccess(response);
        } else {
            ToastUtils.showShort(response.getMsg());
        }
    }


    @Override
    public void onError(Throwable e) {
        if (loadingDialog != null)
            loadingDialog.dismiss();
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort(R.string.error_net);
        } else if (e instanceof HttpException
                || e instanceof UnknownHostException
                || e instanceof SocketException) {//服务器错误
            ToastUtils.showShort(R.string.error_server);
        } else if (e instanceof SocketTimeoutException) {//连接超时等
            ToastUtils.showShort(R.string.error_time_out);
        } else if (e instanceof JsonSyntaxException
                || e instanceof JSONException
                || e instanceof ParseException) {//数据解析异常
            ToastUtils.showShort(R.string.error_data_parse);
        } else {//未知异常
            ToastUtils.showShort(R.string.error_unknow);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功 只返回data的数据
     *
     * @param response: 数据
     */
    public abstract void onSuccess(T response);

    /**
     * 请求成功 需要单独处理状态码时实现该方法
     *
     * @param baseResponse: 带状态码的数据
     */
    public void onSuccess(BaseResponse<T> baseResponse) {

    }
}