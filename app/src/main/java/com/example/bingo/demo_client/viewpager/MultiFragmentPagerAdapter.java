package com.example.bingo.demo_client.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Bingo on 15/12/26.
 */
public abstract class MultiFragmentPagerAdapter extends FragmentPagerAdapter {
    protected List<Fragment> mFragmentList;
    protected List<String> mPageTitleList;

    public MultiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragmentList == null) {
            this.initFragmentList();
        }
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(mFragmentList == null) {
            this.initFragmentList();
        }
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    protected abstract void initFragmentList();
    protected abstract void initPagerTitleList();

    @Override
    public CharSequence getPageTitle(int position) {
        if(mPageTitleList == null) {
            this.initPagerTitleList();
        }
        return mPageTitleList.get(position);
    }
}
