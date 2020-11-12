package com.library.http;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.common.http.XRetrofit;
import com.common.http.base.BaseObserver;
import com.common.http.base.BaseResponse;
import com.common.http.base.RxJavaHelper;
import com.common.http.bean.ExampleBean;
import com.common.http.helper.Mobile;
import com.common.http.helper.RequestBodyHelper;
import com.common.http.helper.upload.OnUploadFileListener;
import com.common.http.helper.upload.UploadFileHelper;
import com.common.library.utilcode.constant.PermissionConstants;
import com.common.library.utilcode.util.ClipboardUtils;
import com.common.library.utilcode.util.LogUtils;
import com.common.library.utilcode.util.PermissionUtils;
import com.common.library.utilcode.util.ToastUtils;
import com.common.util.GetPathFromUri;
import com.common.util.ParamUtil;
import com.library.R;

/**
 * 网络相关操作的类
 */
public class HttpSampleActivity extends BaseActivity {

    private EditText etPassword;
    private EditText etUsername;
    private TextView tvResultData;

    @Override
    public int getLayout() {
        return R.layout.activity_http_sample;
    }

    @Override
    public void initViewIds() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvResultData = findViewById(R.id.tv_result_data);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * get请求
     *
     * @param view
     */
    public void btnGet(View view) {
        XRetrofit.getApi()
                .testGet(Mobile.commonParamsMap(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword)))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<ExampleBean>() {
                    @Override
                    public void onSuccess(ExampleBean response) {
                        renderResultView("data数据为:\n" + response.toString());
                    }
                });
    }

    /**
     * post请求
     *
     * @param view
     */
    public void btnPost(View view) {
        XRetrofit.getApi()
                .testPost(Mobile.commonParamsMap(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword)))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object response) {
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        super.onSuccess(baseResponse);
                        renderResultView("data无数据,总数据为:\n" + baseResponse.toString());
                    }
                });
    }

    /**
     * put请求
     *
     * @param view
     */
    public void btnPut(View view) {
        XRetrofit.getApi()
                .testPut(Mobile.commonParamsMap(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword)))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object response) {
                    }

                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        super.onSuccess(baseResponse);
                        renderResultView("后台返回错误状态码:\n" + baseResponse.toString());
                    }
                });
    }

    /**
     * delete请求
     *
     * @param view
     */
    public void btnDelete(View view) {
        XRetrofit.getApi()
                .testDelete(Mobile.commonParamsMap(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword)))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<ExampleBean>() {
                    @Override
                    public void onSuccess(ExampleBean response) {
                    }

                    @Override
                    public void onSuccess(BaseResponse<ExampleBean> baseResponse) {
                        super.onSuccess(baseResponse);
                        renderResultView("数据为:\n" + baseResponse.toString());
                    }
                });
    }

    /**
     * 提交json格式数据
     *
     * @param view
     */
    public void btnPostJson(View view) {
        XRetrofit.getApi()
                .testPostJson(RequestBodyHelper.getRequestBody
                        (Mobile.commonParamsMap(ParamUtil.getEditStr(etUsername), ParamUtil.getEditStr(etPassword))))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<ExampleBean>() {
                    @Override
                    public void onSuccess(ExampleBean response) {
                        renderResultView("data数据为:\n" + response.toString());
                    }
                });
    }

    /**
     * 上传文件
     *
     * @param view
     */
    public void btnUploadFile(View view) {
        if (PermissionUtils.isGranted(PermissionConstants.STORAGE)) {
            //有权限
            openAlbum();
        } else {
            //没有权限
            PermissionUtils permission = PermissionUtils.permission(PermissionConstants.STORAGE);
            permission.request();
            permission.callback(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    //同意
                    openAlbum();
                }

                @Override
                public void onDenied() {
                    ToastUtils.showShort("请开启文件读写权限");
                }
            });
        }
    }


    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String photoPath;
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            photoPath = GetPathFromUri.getRealPathFromUri(this, data.getData());
            uploadFile(photoPath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 文件上传
     * @param photoPath
     */
    private void uploadFile(String photoPath) {
        UploadFileHelper.uploadFile(this, photoPath, new OnUploadFileListener() {
            @Override
            public void onUploadFileSuccess(String imgUrl) {
                renderResultView("文件上传成功:\n" + imgUrl + "\n\n\n已复制到剪切板");
                ClipboardUtils.copyText(imgUrl);
            }

            @Override
            public void onUploadFileFailed(String errorMsg) {
                renderResultView("文件上传失败:\n" + errorMsg);
            }
        });
    }

    /**
     * 渲染返回数据View
     *
     * @param str
     */
    private void renderResultView(String str) {
        tvResultData.setText(str);
    }
}
