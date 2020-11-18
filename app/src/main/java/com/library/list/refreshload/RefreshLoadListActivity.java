package com.library.list.refreshload;

import androidx.annotation.NonNull;
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
import com.common.weight.CommonSmartRefreshLayout;
import com.library.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.Collections;
import java.util.List;

/**
 * 刷新加载集合
 *
 * @Link: https://github.com/scwang90/SmartRefreshLayout
 */
public class RefreshLoadListActivity extends BaseActivity {

    private int page = 1;
    /**
     * 每次固定请求10条
     */
    private final int PAGE_NUM = 10;

    private CommonSmartRefreshLayout refreshLayout;
    private CommonRecyclerView rvRefresh;
    private RefreshLoadListAdapter refreshLoadListAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_refresh_load_list;
    }

    @Override
    public void initViewIds() {
        refreshLayout = findViewById(R.id.refresh_layout);
        rvRefresh = findViewById(R.id.rv_refresh);
    }

    @Override
    public void initView() {
        //到底部是否自动加载更多,不显示上拉的效果.默认为true
        refreshLayout.setEnableAutoLoadMore(false);
        initRefreshRecycler();
    }

    private void initRefreshRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvRefresh.setLayoutManager(layoutManager);
        refreshLoadListAdapter = new RefreshLoadListAdapter(Collections.emptyList());
        rvRefresh.setAdapter(refreshLoadListAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getList(page);
    }

    @Override
    public void initListener() {
        super.initListener();
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            //刷新,页数是1
            page = 1;
            getList(page);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            //加载,页数往上+1
            page++;
            getList(page);
            refreshLayout.finishLoadMore();
        });
        refreshLoadListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ToastUtils.showShort("点击条目下标---" + position);
        });
        refreshLoadListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.btn_click_me) {
                ToastUtils.showShort("点击按钮下标---" + position);
            }
        });
    }

    /**
     * 获取集合
     *
     * @param page: 页数
     */
    private void getList(int page) {
        XRetrofit.getApi()
                .getList(Mobile.getList(page, PAGE_NUM))
                .compose(RxJavaHelper.observeOnMainThread())
                .subscribe(new BaseObserver<List<ExampleListBean>>() {
                    @Override
                    public void onSuccess(List<ExampleListBean> response) {
                        if (response == null)
                            return;
                        if (page == 1) {
                            //page==1,为刷新,直接作为新数据set进来
                            refreshLoadListAdapter.setNewData(response);
                        } else {
                            //不为空就往后加
                            refreshLoadListAdapter.addData(response);
                        }
                    }
                });
    }

}
