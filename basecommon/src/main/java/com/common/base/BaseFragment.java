package com.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private View convertView;

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    @Override
    @Subscribe
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useEventBus()) {//如果使用EventBus,请将此方法返回true
            EventBus.getDefault().register(this);
        }
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        convertView = inflater.inflate(getLayout(), container, false);
        if (useLazyFragment()) {
            initViewIds(convertView);
            attachView();
            initArguments();
            try {//捕获一下异常
                initCatch();
            } catch (Exception ignored) {
            }
            initView(convertView);
            isInitView = true;
            lazyLoadData(savedInstanceState);
            initListener();
        }
        return convertView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //不使用懒加载的话
        if (!useLazyFragment()) {
            initViewIds(view);
            attachView();
            initArguments();
            try {//捕获一下异常
                initCatch();
            } catch (Exception ignored) {
            }
            initView(view);
            initData(savedInstanceState);
            initListener();
        }
    }

    public void attachView(){};


    /**
     * 初始化布局
     *
     * @return R.layout_game_detail_outs_more_image_progress_bar.xx
     */
    public abstract int getLayout();

    /**
     * 初始化控件id
     */
    public abstract void initViewIds(View view);

    /**
     * 初始化接收activity的传值
     */
    public void initArguments() {

    }

    /**
     * 初始化缓存的数据
     */
    public void initCatch() {

    }

    /**
     * 初始化视图
     */
    public abstract void initView(View view);

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化事件监听
     */
    public void initListener() {

    }

    /**
     * 处理懒加载的数据逻辑
     *
     * @param savedInstanceState
     */
    private void lazyLoadData(Bundle savedInstanceState) {
        if (!isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        initData(savedInstanceState);
        isFirstLoad = false;
    }

    /**
     * 是否使用eventbus
     * 默认不使用注册
     *
     * @return: false
     */
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //使用懒加载的话
        if (useLazyFragment()) {
            if (isVisibleToUser) {
                isVisible = true;
                lazyLoadData(null);
            } else {
                isVisible = false;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 是否使用懒加载,默认false
     * 使用懒加载 必须实现  需要配合viewpager.setOffscreenPageLimit(fragment.size)方法,有多少个就写几个
     *
     * @return
     */
    public boolean useLazyFragment() {
        return false;
    }

}
