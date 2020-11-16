package com.library.lazyload;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.base.BaseFragment;
import com.library.R;

/**
 * @Author: https://github.com/manitozhang
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/13
 * <p>
 * @Desc:
 */
public class LazyFragment extends BaseFragment {

    private TextView tvTabName;
    private String bundleTabName = "";

    @Override
    public int getLayout() {
        return R.layout.fragment_lazy;
    }

    @Override
    public void initViewIds(View view) {
        tvTabName = view.findViewById(R.id.tv_tab_name);
    }

    @Override
    public void initArguments() {
        if (getArguments() != null)
            bundleTabName = getArguments().getString("bundle_tab");
        super.initArguments();
    }

    @Override
    public void initView(View view) {

    }

    /**
     * 在initData方法写需要懒加载的业务代码即可
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        tvTabName.setText(bundleTabName);
    }

    /**
     * 使用懒加载 必须实现  需要配合viewpager.setOffscreenPageLimit()方法,有多少个就写几
     *
     * @return
     */
    @Override
    public boolean useLazyFragment() {
        return true;
    }
}
