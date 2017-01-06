package com.huangj.myapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21 11:29
 * 功能描述:
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    List<String> tabList;

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabList = tabList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
