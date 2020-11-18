package com.library.list.singlechoice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.common.base.BaseActivity;
import com.common.http.XRetrofit;
import com.common.http.base.BaseObserver;
import com.common.http.base.RxJavaHelper;
import com.common.http.bean.ExampleListBean;
import com.common.http.helper.Mobile;
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter;
import com.common.weight.CommonRecyclerView;
import com.library.R;
import com.library.list.refreshload.RefreshLoadListAdapter;

import java.util.Collections;
import java.util.List;

/**
 * 单选列表Activity
 */
public class SingleChoiceListActivity extends BaseActivity {

    private CommonRecyclerView rvSingleChoice;
    private SingleChoiceListAdapter singleChoiceListAdapter;
    private final int page = 1;
    private final int pageNum = 20;

    @Override
    public int getLayout() {
        return R.layout.activity_single_choice_list;
    }

    @Override
    public void initViewIds() {
        rvSingleChoice = findViewById(R.id.rv_single_choice);
    }

    @Override
    public void initView() {
        initSingleChoiceRecycler();
    }

    private void initSingleChoiceRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvSingleChoice.setLayoutManager(layoutManager);
        singleChoiceListAdapter = new SingleChoiceListAdapter(Collections.emptyList());
        rvSingleChoice.setAdapter(singleChoiceListAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getList(page, pageNum);
    }

    @Override
    public void initListener() {
        super.initListener();
        singleChoiceListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_select) {
                //点击按钮,将当前点击的下标传进去,刷新适配器
                singleChoiceListAdapter.setSelPos(position);
                singleChoiceListAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取集合
     *
     * @param page:    页数
     * @param pageNum: 条目数
     */
    private void getList(int page, int pageNum) {
        XRetrofit.getApi()
                .getList(Mobile.getList(page, pageNum))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<List<ExampleListBean>>() {
                    @Override
                    public void onSuccess(List<ExampleListBean> response) {
                        if (response == null)
                            return;
                        singleChoiceListAdapter.setNewData(response);
                    }
                });
    }

    /**
     * 获取选中的下标
     *
     * @param view
     */
    public void btnGetSingleData(View view) {
        if (singleChoiceListAdapter.getSelPos() == -1) {
            ToastUtils.showShort("你还未选中数据");
            return;
        }
        ToastUtils.showShort("选中的标题是---"
                + singleChoiceListAdapter.getData()
                .get(singleChoiceListAdapter.getSelPos()).getTitle());
    }
}
