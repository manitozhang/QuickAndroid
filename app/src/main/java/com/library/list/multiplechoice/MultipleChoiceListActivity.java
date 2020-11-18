package com.library.list.multiplechoice;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 多选列表
 * 类似于列表收藏,点赞,关注都属于多选
 */
public class MultipleChoiceListActivity extends BaseActivity {

    private CommonRecyclerView rvMultiple;
    private MultipleChoiceListAdapter multipleChoiceListAdapter;
    private final int page = 1;
    private final int pageNum = 20;

    @Override
    public int getLayout() {
        return R.layout.activity_multiple_choice_list;
    }

    @Override
    public void initViewIds() {
        rvMultiple = findViewById(R.id.rv_multiple_choice);
    }

    @Override
    public void initView() {
        initMultipleRecycler();
    }

    private void initMultipleRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMultiple.setLayoutManager(layoutManager);
        multipleChoiceListAdapter = new MultipleChoiceListAdapter(Collections.emptyList());
        rvMultiple.setAdapter(multipleChoiceListAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getList(page, pageNum);
    }

    @Override
    public void initListener() {
        super.initListener();
        multipleChoiceListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ExampleListBean bean = multipleChoiceListAdapter.getData().get(position);
            bean.setChecked(!bean.isChecked());
            multipleChoiceListAdapter.notifyItemChanged(position);
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
                        multipleChoiceListAdapter.setNewData(response);
                    }
                });
    }

    /**
     * 获取选中下标
     *
     * @param view
     */
    public void btnGetMutliplePos(View view) {
        List<Integer> selectPosList = new ArrayList<>();
        for (int i = 0; i < multipleChoiceListAdapter.getData().size(); i++) {
            if (multipleChoiceListAdapter.getData().get(i).isChecked())
                selectPosList.add(i);
        }
        ToastUtils.showShort("选中的下标为---" + selectPosList.toString());
    }

    /**
     * 选中全部
     *
     * @param view
     */
    public void btnChooseAll(View view) {
        //遍历数据
        for (ExampleListBean data :
                multipleChoiceListAdapter.getData()) {
            //全部设置为选中
            data.setChecked(true);
        }
        multipleChoiceListAdapter.notifyDataSetChanged();
    }

    /**
     * 全不选
     *
     * @param view
     */
    public void btnUnChooseAll(View view) {
        //遍历数据
        for (ExampleListBean data :
                multipleChoiceListAdapter.getData()) {
            //全部设置为非选中
            data.setChecked(false);
        }
        multipleChoiceListAdapter.notifyDataSetChanged();
    }

    /**
     * 反选
     *
     * @param view
     */
    public void btnChooseReverse(View view) {
        //遍历数据
        for (ExampleListBean data :
                multipleChoiceListAdapter.getData()) {
            //将状态改为反状态
            data.setChecked(!data.isChecked());
        }
        multipleChoiceListAdapter.notifyDataSetChanged();
    }
}
