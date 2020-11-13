package com.library.lazyload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.view.View;

import com.common.base.BaseActivity;
import com.common.weight.CommonViewPager;
import com.google.android.material.tabs.TabLayout;
import com.library.R;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT;

/**
 * 懒加载的Activity
 */
public class LazyLoadActivity extends BaseActivity {


    private String[] tabArray = new String[]{"推荐", "关注", "娱乐", "国内", "军事", "财经"};
    private TabLayout tabLayout;
    private CommonViewPager viewPager;

    @Override
    public int getLayout() {
        return R.layout.activity_lazy_load;
    }

    @Override
    public void initViewIds() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initView() {
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabArray.length);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabArray[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //传递数据
            LazyFragment lazyFragment = new LazyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("bundle_tab", tabArray[position]);
            lazyFragment.setArguments(bundle);
            return lazyFragment;
        }

        @Override
        public int getCount() {
            return tabArray.length;
        }
    }

}
