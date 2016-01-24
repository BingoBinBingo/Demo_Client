package com.example.bingo.demo_client.viewpager;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Bingo on 15/12/26.
 */
public class DemoMultiFragPagerAdapter extends MultiFragmentPagerAdapter {

    public DemoMultiFragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    protected void initFragmentList() {
        this.mFragmentList = new ArrayList();
        //zai getCount 和getItem 的时候被调用
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        Fragment6 fragment6 = new Fragment6();
        Fragment7 fragment7 = new Fragment7();

        this.mFragmentList.add(fragment1);
        this.mFragmentList.add(fragment2);
        this.mFragmentList.add(fragment3);
        this.mFragmentList.add(fragment4);
        this.mFragmentList.add(fragment5);
        this.mFragmentList.add(fragment6);
        this.mFragmentList.add(fragment7);
    }

    @Override
    protected void initPagerTitleList() {
        this.mPageTitleList = new ArrayList();

        this.mPageTitleList.add("网易美食");
        this.mPageTitleList.add("网易运动");
        this.mPageTitleList.add("网易天气");
        this.mPageTitleList.add("网易汽车");
        this.mPageTitleList.add("网易金钱");
        this.mPageTitleList.add("网易美食");
        this.mPageTitleList.add("网易运动");
    }

}
