package com.example.heartx.greendao_exercies.util.test;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * 重新设计
 * Created by HeartX on 2017/12/20.
 */

class ViewSizeChangeFactory {

    private final static String SCALED = "SCALED";

    private final static int DEFAULT_SCREEN_WIDTH = 1080;
    private final static int DEFAULT_SCREEN_HEIGHT = 1920;

    private static float widthScale;
    private static float heightScale;

    private static float currentWidth;
    private static float currentHeight;

    private static boolean isLandscape = false;

    private ViewGroup contentView;

    private FillerCall fillerCall;

    private ViewSizeChangeFactory(View view) {
        if (isContentView(view)) {
            this.contentView = (ViewGroup)view;
        }
    }

    private ViewSizeChangeFactory(View view, FillerCall fillerCall) {
        if (isContentView(view)) {
            this.contentView = (ViewGroup)view;
        }
        this.fillerCall = fillerCall;
    }

    static ViewSizeChangeFactory create(View view, FillerCall fillerCall){
        return new ViewSizeChangeFactory(view, fillerCall);
    }

    boolean change(View view) {
        isUntrueResolution(view);

        if (view instanceof ViewGroup) {

            changeViewGroup((ViewGroup) view);
        } else {

            changeView(view);
        }
        return true;
    }

    private void changeViewGroup(ViewGroup view) {

        int childCount = view.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View child = view.getChildAt(i);

            if (child instanceof ViewGroup) {

                changeViewGroup((ViewGroup) child);
            } else {

                changeView(child);
            }
        }
        changeView(view);
    }

    private void changeView(View view) {
        if (isUnscaledSize(view) && !isContentView(view)) {
            //Logger.d(((TextView)child).getText());
            resetPadding(view);
            resetParams(view);
            //resetParams(child, null);
            view.setTag(91295463, SCALED);
        }
    }

    private void resetPadding(View child) {

        int paddingLeft = (int) (child.getPaddingLeft() * widthScale);
        int paddingTop = (int) (child.getPaddingTop() * heightScale);
        int paddingRight = (int) (child.getPaddingRight() * widthScale);
        int paddingBottom = (int) (child.getPaddingBottom() * heightScale);

        child.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    private void resetParams(View child) {

        ViewGroup.LayoutParams lParams = child.getLayoutParams();

        if (lParams != null) {

            if (isPad(child) && isLandscape && isRootLayout(child) && fillerCall != null) {

                lParams.width = (int) currentHeight;

            } else if (lParams.width > 0) {
                lParams.width = (int) (lParams.width * widthScale);
            }
            if (lParams.height > 0) {
                lParams.height = (int) (lParams.height * heightScale);
            }

            if (lParams instanceof ViewGroup.MarginLayoutParams) {
                //Logger.d("is MarginLayoutParams");
                ViewGroup.MarginLayoutParams mParams = (ViewGroup.MarginLayoutParams) lParams;

                int leftMargin = (int) (mParams.leftMargin * widthScale);
                int topMargin = (int) (mParams.topMargin * heightScale);
                int rightMargin = (int) (mParams.rightMargin * widthScale);
                int bottomMargin = (int) (mParams.bottomMargin * heightScale);

                mParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            }
        }

        child.setLayoutParams(lParams);
    }

    private void resetParams(View child, Object redundant) {

        ViewGroup.LayoutParams lParams = child.getLayoutParams();

        if (lParams != null) {

            if (isPad(child) && isLandscape && isRootLayout(child)) {

                lParams.width = (int) currentHeight;

            } else if (lParams.width > 0) {
                lParams.width = (int) (lParams.width * widthScale);
            }
            if (lParams.height > 0) {
                lParams.height = (int) (lParams.height * heightScale);
            }

            if (lParams instanceof ViewGroup.MarginLayoutParams) {
                //Logger.d("is MarginLayoutParams");
                ViewGroup.MarginLayoutParams mParams = (ViewGroup.MarginLayoutParams) lParams;

                float l = (float) mParams.leftMargin / DEFAULT_SCREEN_WIDTH;
                float t = (float) mParams.topMargin / DEFAULT_SCREEN_HEIGHT;
                float r = (float) mParams.rightMargin / DEFAULT_SCREEN_WIDTH;
                float b = (float) mParams.bottomMargin / DEFAULT_SCREEN_HEIGHT;

                int leftMargin = (int) (currentWidth * l);
                int topMargin = (int) (currentHeight * t);
                int rightMargin = (int) (currentWidth * r);
                int bottomMargin = (int) (currentHeight * b);

                mParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            }
        }

        child.setLayoutParams(lParams);
    }

    private float countRatio(int defaultSize) {
        return countRatio(contentView, defaultSize);
    }

    private float countRatio(View view, int defaultSize) {

        float pixels;

        switch (defaultSize) {
            case DEFAULT_SCREEN_WIDTH: {
                pixels = view.getResources().getDisplayMetrics().widthPixels;
                break;
            }

            case DEFAULT_SCREEN_HEIGHT: {
                pixels = view.getResources().getDisplayMetrics().heightPixels;
                break;
            }

            default: {
                return 0;
            }
        }

        return pixels / defaultSize;
    }

    private int getTitleBarHeight(Context c) {
        ActionBar supportActionBar = null;
        if (c instanceof AppCompatActivity) {
            supportActionBar = ((AppCompatActivity) c).getSupportActionBar();
        }
        return supportActionBar == null ? -1 : supportActionBar.getHeight();
    }

    private int getStatusBarHeight(Context c) {
        int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? c.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    private boolean isUntrueResolution(View view) {

        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();

        currentWidth = displayMetrics.widthPixels;
        currentHeight = displayMetrics.heightPixels;

        widthScale = currentWidth / DEFAULT_SCREEN_WIDTH;
        heightScale = currentHeight / DEFAULT_SCREEN_HEIGHT;

        boolean untrue = currentWidth != DEFAULT_SCREEN_WIDTH || currentHeight != DEFAULT_SCREEN_HEIGHT;

        isLandscape = view.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape/*currentWidth > currentHeight*/) {
            //isLandscape = true;
            widthScale = currentHeight / DEFAULT_SCREEN_WIDTH;
            heightScale = currentHeight / DEFAULT_SCREEN_HEIGHT;
        }

        return untrue;
    }

    private boolean isPad(View view) {

        return (view.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private boolean isUnscaledSize(View view) {

        return view.getTag(91295463) != SCALED;
    }

    private boolean isRootLayout(View view) {

        return getRootLayout(view) == view;
    }

    private View getRootLayout(View view) {

        if (isContentView(view)) {

            return ((ViewGroup) view).getChildAt(0);
        } else{

            Activity activity = (Activity) view.getContext();

            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);

            return contentView.getChildAt(0);
        }
    }

    private boolean isContentView(View view) {

        return view.getId() == android.R.id.content;
    }

    public void setFillerCall(FillerCall fillerCall) {
        this.fillerCall = fillerCall;
    }
}
