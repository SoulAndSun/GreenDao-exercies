package com.example.heartx.greendao_exercies.util.test;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heartx.greendao_exercies.R;

/**
 * 重新设计
 * Created by HeartX on 2017/12/20.
 */

class FillerCreator {

    private Context context;

    private FillerCreator(Context context) {
        this.context = context;
    }

    static void create(Context context) {// TODO: 2017/12/20 只是create所以不应该执行添加
        FillerCreator filler = new FillerCreator(context);

        ViewGroup contentView = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        LinearLayout linearLayout = filler.newLinearLayout();
        RelativeLayout relativeLayout = filler.newRelativeLayout();

        relativeLayout.addView(filler.newTextView());
        linearLayout.addView(relativeLayout);
        contentView.addView(linearLayout);
    }

    private FrameLayout newFrameLayout() {

        FrameLayout frameLayout = new FrameLayout(context);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);

        frameLayout.setBackgroundColor(0x99336699);

        frameLayout.setLayoutParams(layoutParams);

        return frameLayout;
    }

    private RelativeLayout newRelativeLayout() {

        RelativeLayout relativeLayout = new RelativeLayout(context);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);

        relativeLayout.setBackgroundColor(0xff336699);

        relativeLayout.setLayoutParams(layoutParams);

        return relativeLayout;
    }

    private LinearLayout newLinearLayout() {

        LinearLayout linearLayout = new LinearLayout(context);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);

        linearLayout.setBackgroundColor(0x99336699);

        linearLayout.setLayoutParams(layoutParams);

        linearLayout.setX(context.getResources().getDisplayMetrics().heightPixels);// TODO: 2017/12/20

        return linearLayout;
    }

    private TextView newTextView() {

        TextView textView = new TextView(context);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        //ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(layoutParams);
        //margin.leftMargin = 800;
        //margin.topMargin = 800;

        textView.setLayoutParams(layoutParams);

        textView.setText(R.string.title);

        textView.setBackgroundColor(0x20f5f904);
        //button.setGravity(Gravity.CENTER);
        //button.setX(200);

        return textView;
    }
}
