package com.github.wrongyuan.hometab_sample;

import android.os.Bundle;
import android.util.Log;

import com.github.wrongyuan.hometab.HomeTabLayout;

public class BottomTabActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        showFragment(0);
        tabLayout.setOnTabCheckedListner(new HomeTabLayout.OnTabCheckedListner() {
            @Override
            public void onCheckedChanged(int currPos, int lastPos) {
                Log.d(TAG,"currPos-->"+currPos+"  lastPos-->"+lastPos);
                if (currPos != lastPos)
                    showFragment(currPos);
            }
        });
    }

    protected void showFragment(int pos) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_container, TabFragment.newInstance(TAG, tabItems[pos].getText().toString()))
                .commitAllowingStateLoss();
    }
}
