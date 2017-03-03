package com.github.wrongyuan.hometab_sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.github.wrongyuan.hometab.HomeTabLayout;

public class ViewPagerActivity extends BaseTabActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.checkItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabCheckedListner(new HomeTabLayout.OnTabCheckedListner() {
            @Override
            public void onCheckedChanged(int currPos, int lastPos) {
                Log.d(TAG,"currPos-->"+currPos+"  lastPos-->"+lastPos);
                if (currPos != lastPos)
                    viewPager.setCurrentItem(currPos);
            }
        });

    }
    class TabFragmentAdapter extends FragmentStatePagerAdapter{

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TabFragment.newInstance(TAG,tabItems[position].getText().toString());
        }

        @Override
        public int getCount() {
            return tabItems.length;
        }
    }
}
