package com.example.bingo.demo_client.viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bingo.demo_client.R;

import java.util.ArrayList;

/**
 * Created by Bingo on 15/12/25.
 */
public class MainViewPager extends Activity{
    public static final String TAG = MainViewPager.class.getCanonicalName();
    public static final String TITLE = "MainViewPager";
    public static final String ACTION_NAME = "com.bingo.demo.mainViewPager_action";
    private ViewPager mViewPager;
    private PagerTabStrip mStrip;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    private ArrayList<String> titleContainer = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);
        initView();
        initModel();
    }

    private void initView() {
        mViewPager = (ViewPager)findViewById(R.id.myViewPager);
        mStrip = (PagerTabStrip)findViewById(R.id.myPagerTabStrip);
        //去掉strip的下划线
        mStrip.setDrawFullUnderline(false);
        mStrip.setBackgroundColor(getResources().getColor(R.color.pink_color));
        mStrip.setTabIndicatorColor(Color.BLACK);
        mStrip.setTextSpacing(200);

        titleContainer.add("网易美食");
        titleContainer.add("网易运动");
        titleContainer.add("网易天气");

        View view1 = LayoutInflater.from(this).inflate(R.layout.tab1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tab2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.tab3, null);

        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
    }

    private void initModel() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainter.size();
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager)container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });
    }
}
