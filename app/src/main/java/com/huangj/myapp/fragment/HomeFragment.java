package com.huangj.myapp.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangj.myapp.HomeViewPagerAdapter;
import com.huangj.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TabLayout mTabLayout;
    ViewPager viewPager;
    FragmentManager manager;
    String[] titles = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    List<String> listTab = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) view.findViewById(R.id.home_tablayout);

        manager = getChildFragmentManager();
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < titles.length; i++) {
            listTab.add(titles[i]);
            fragmentList.add(new HomeOneFragment());
        }
        viewPager.setAdapter(new HomeViewPagerAdapter(manager, fragmentList, listTab));
        mTabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
