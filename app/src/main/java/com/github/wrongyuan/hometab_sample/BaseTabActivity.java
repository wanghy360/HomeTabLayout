package com.github.wrongyuan.hometab_sample;

import android.support.v7.app.AppCompatActivity;
import com.github.wrongyuan.hometab.TabItem;
import com.github.wrongyuan.hometab.HomeTabLayout;

public class BaseTabActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected HomeTabLayout tabLayout;
    protected TabItem[] tabItems;

    protected void initView() {
        tabLayout = (HomeTabLayout) findViewById(R.id.id_tab);
        TabItem home = new TabItem("Home", getResources().getDrawable(R.drawable.ico_home_checked), getResources().getDrawable(R.drawable.ico_home_unchecked));
        TabItem video = new TabItem("Video", getResources().getDrawable(R.drawable.ico_video_checked), getResources().getDrawable(R.drawable.ico_video_unchecked));
        TabItem love = new TabItem("Love", getResources().getDrawable(R.drawable.ico_love_checked), getResources().getDrawable(R.drawable.ico_love_unchecked));
        TabItem user = new TabItem("User", getResources().getDrawable(R.drawable.ico_user_checked), getResources().getDrawable(R.drawable.ico_user_unchecked));

        tabItems = new TabItem[]{home, video, love, user};
        tabLayout.setItems(tabItems);
    }
}
