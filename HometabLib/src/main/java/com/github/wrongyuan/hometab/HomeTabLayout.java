package com.github.wrongyuan.hometab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by wrongyuan on 2017/3/2
 */

public class HomeTabLayout extends LinearLayout {
    private int checkedTextColor;
    private int uncheckedTextColor;
    private int uncheckedBackgroundColor;
    private int checkedBackgroundColor;
    private int textIconMargin;
    private int textSize;
    private int iconWidth;
    private int iconHeight;
    private int checkedPosition;
    private TabItem[] tabItems;

    public HomeTabLayout(Context context) {
        super(context);
        init(context, null);
    }

    public HomeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HomeTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HomeTabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        checkedTextColor = typedArray.getColor(R.styleable.TabView_tab_checkedTextColor, Color.rgb(252, 88, 17));
        uncheckedTextColor = typedArray.getColor(R.styleable.TabView_tab_uncheckedTextColor, Color.rgb(129, 130, 149));
        uncheckedBackgroundColor = typedArray.getColor(R.styleable.TabView_tab_uncheckedBackgroundColor, Color.rgb(255, 255, 255));
        checkedBackgroundColor = typedArray.getColor(R.styleable.TabView_tab_checkedBackgroundColor, Color.rgb(240, 240, 240));
        textIconMargin = typedArray.getDimensionPixelSize(R.styleable.TabView_tab_iconTextMargin, dp2px(context, 2));
        textSize = typedArray.getDimensionPixelSize(R.styleable.TabView_tab_textSize, sp2px(context, 14));
        iconWidth = typedArray.getDimensionPixelSize(R.styleable.TabView_tab_imageWidth, dp2px(context, 30));
        iconHeight = typedArray.getDimensionPixelSize(R.styleable.TabView_tab_imageHeight, dp2px(context, 30));

        typedArray.recycle();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return dp2px(getContext(), 100);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return iconHeight + textIconMargin + textSize;
    }

    public void checkItem(int position){
        refreshView(position,checkedPosition);
    }

    public void setItems(TabItem... items) {
        setItems(0, items);
    }

    public void setItems(int defaultCheckedPos, TabItem... items) {
        if (items == null)
            throw new NullPointerException("Tab items shouldn't be null.");
        if (items.length == 0)
            throw new IllegalArgumentException("The number of tab items must be more than 0.");
        checkedPosition = defaultCheckedPos;
        this.tabItems = items;
        initLayout();
    }

    private void initLayout() {
        for (int i = 0; i < tabItems.length; i++) {
            final int currPos = i;
            final TabItem tabItem = tabItems[currPos];
            final LinearLayout tabChild = new LinearLayout(getContext());
            tabChild.setGravity(Gravity.CENTER);
            tabChild.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams tabChildParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            tabChildParams.gravity = Gravity.CENTER_VERTICAL;
            tabChild.setLayoutParams(tabChildParams);


            final ImageView imageview = new ImageView(getContext());
            LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(iconWidth, iconHeight);
            imageview.setLayoutParams(ivParams);

            tabChild.addView(imageview);

            final TextView textview = new TextView(getContext());
            textview.setText(tabItem.getText());
            textview.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            LinearLayout.LayoutParams textviewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textviewParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            textviewParams.topMargin = textIconMargin;
            textview.setLayoutParams(textviewParams);
            tabChild.addView(textview);

            if (checkedPosition == currPos) {
                imageview.setImageDrawable(tabItem.getSelectedDrawable());
                textview.setTextColor(checkedTextColor);
                tabChild.setBackgroundColor(checkedBackgroundColor);
            } else {
                imageview.setImageDrawable(tabItem.getUnselectedDrawable());
                textview.setTextColor(uncheckedTextColor);
                tabChild.setBackgroundColor(uncheckedBackgroundColor);
            }

            addView(tabChild);

            tabChild.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int lastPos = checkedPosition;
                    refreshView(currPos, lastPos);

                    if (onTabCheckedListner != null) {
                        onTabCheckedListner.onCheckedChanged(currPos, lastPos);
                    }

                }
            });
        }
    }

    private void refreshView(int position, int lastPosition) {
        if (position != lastPosition) {
            freshViewByPosition(position, true);
            freshViewByPosition(lastPosition, false);
            checkedPosition = position;
        }
    }

    private void freshViewByPosition(int position, boolean check) {
        if (check) {
            LinearLayout tabChild = (LinearLayout) getChildAt(position);
            ImageView iv = (ImageView) tabChild.getChildAt(0);
            TextView tv = (TextView) tabChild.getChildAt(1);

            iv.setImageDrawable(tabItems[position].getSelectedDrawable());
            tv.setTextColor(checkedTextColor);
            tabChild.setBackgroundColor(checkedBackgroundColor);
        } else {
            LinearLayout tabCheckedChild = (LinearLayout) getChildAt(position);
            ImageView checkedIv = (ImageView) tabCheckedChild.getChildAt(0);
            TextView checkedTv = (TextView) tabCheckedChild.getChildAt(1);

            checkedIv.setImageDrawable(tabItems[position].getUnselectedDrawable());
            checkedTv.setTextColor(uncheckedTextColor);
            tabCheckedChild.setBackgroundColor(uncheckedBackgroundColor);
        }
    }

    private int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    private int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public interface OnTabCheckedListner {
        void onCheckedChanged(int currPos, int lastPos);
    }

    private OnTabCheckedListner onTabCheckedListner;

    public void setOnTabCheckedListner(OnTabCheckedListner onTabCheckedListner) {
        this.onTabCheckedListner = onTabCheckedListner;
    }
}
