package com.myworld.android.beanmaking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 小可爱 on 2018/5/14.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<String>list = new ArrayList<>();
    private ArrayList<Fragment>fragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
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
        return list.get(position);
    }

    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        list.add(title);
    }
}
