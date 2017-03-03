package com.github.wrongyuan.hometab_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void naviToBottomTab(View view){
        startActivity(new Intent(this,BottomTabActivity.class));
    }
    public void naviToViewPagerTab(View view){
        startActivity(new Intent(this,ViewPagerActivity.class));
    }
}
