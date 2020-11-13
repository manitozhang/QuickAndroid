package com.library.mainpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.common.library.eventbus.EventBus;
import com.common.util.SpUtil;
import com.library.R;
import com.library.mainpage.fragment.HomeFragment;
import com.library.mainpage.fragment.MineFragment;
import com.library.mainpage.fragment.NewsFragment;
import com.library.testmvp.LoginActivity;

/**
 * 首页页面
 */
public class MainPageActivity extends BaseActivity {

    private int[] idArrays = new int[]{R.id.tv_home, R.id.tv_news, R.id.tv_mine};

    private FragmentTransaction beginTransaction;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private MineFragment mineFragment;
    private TextView tvHome;
    private TextView tvNews;
    private TextView tvMine;

    @Override
    public int getLayout() {
        return R.layout.activity_main_page;
    }

    @Override
    public void initViewIds() {
        tvHome = findViewById(R.id.tv_home);
        tvNews = findViewById(R.id.tv_news);
        tvMine = findViewById(R.id.tv_mine);
    }

    @Override
    public void initView() {
        setFragment(0);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initListener() {
        super.initListener();
        tvHome.setOnClickListener(v -> {
            setFragment(0);
        });
        tvNews.setOnClickListener(v -> {
            setFragment(1);
        });
        tvMine.setOnClickListener(v -> {
            setFragment(2);
        });
    }

    /**
     * 设置Fragment
     * 其他地方可发送通知来执行该方法改变tab选中
     * @param index: 下标
     */
    private void setFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        hideFragment();
        switch (index) {
            case 0:
                //首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    beginTransaction.add(R.id.fl_layout, homeFragment);
                } else {
                    beginTransaction.show(homeFragment);
                }
                break;
            case 1:
                //发现
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    beginTransaction.add(R.id.fl_layout, newsFragment);
                } else {
                    beginTransaction.show(newsFragment);
                }
                break;
            case 2:
                //我的
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    beginTransaction.add(R.id.fl_layout, mineFragment);
                } else {
                    beginTransaction.show(mineFragment);
                }
                break;
        }
        beginTransaction.commitAllowingStateLoss();
        refreshBottomStatus(index);
    }

    /**
     * 改变底部图片和文字的状态
     *
     * @param position: 下标
     */
    private void refreshBottomStatus(int position) {
        for (int i = 0; i < idArrays.length; i++) {
            findViewById(idArrays[i]).setSelected(position == i);
        }
    }

    /**
     * 隐藏所有Fragment
     */
    private void hideFragment() {
        if (homeFragment != null)
            beginTransaction.hide(homeFragment);
        if (newsFragment != null)
            beginTransaction.hide(newsFragment);
        if (mineFragment != null)
            beginTransaction.hide(mineFragment);
    }

}
