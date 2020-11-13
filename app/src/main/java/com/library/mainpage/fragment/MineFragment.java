package com.library.mainpage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.base.BaseFragment;
import com.library.R;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 * <p>
 * @Desc:
 */
public class MineFragment extends BaseFragment {

    private TextView tvDesc;

    @Override
    public int getLayout() {
        return R.layout.fragment_main ;
    }

    @Override
    public void initViewIds(View view) {
        tvDesc = view.findViewById(R.id.tv_desc);
    }

    @Override
    public void initView(View view) {
        tvDesc.setText("我是个人页");
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
