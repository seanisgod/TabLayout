package com.example.administrator.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private String[] titles = new String[]{"首页", "好友", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initView();
    }

    private void findView() {
        tableLayout = (TabLayout) findViewById(R.id.tableLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initView() {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragmentPagerAdapter(fragmentManager, fragments, titles));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        tableLayout.setMinimumWidth(500);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        //如果标签比较多，比如新闻可以使用这种模式
        //tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tableLayout.addTab(tableLayout.newTab().setText(titles[0]));
        tableLayout.addTab(tableLayout.newTab().setText(titles[1]));
        tableLayout.addTab(tableLayout.newTab().setText(titles[2]));
        tableLayout.setupWithViewPager(viewPager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        private String[] titles;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            viewPager.setCurrentItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
