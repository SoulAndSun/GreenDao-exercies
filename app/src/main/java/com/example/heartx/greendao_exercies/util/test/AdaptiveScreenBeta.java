package com.example.heartx.greendao_exercies.util.test;

import android.view.View;
import android.view.ViewGroup;

/**
 * 重新设计
 * Created by HeartX on 2017/12/20.
 */

public class AdaptiveScreenBeta {

    private View view;

    private FillerCall fillerCall;

    AdaptiveScreenBeta(View view) {

        this.view = view;
    }

    public static AdaptiveScreenBeta adaptive(View view) {

        if (view == null) {
            throw new NullPointerException("view is null");
        }

        return new AdaptiveScreenBeta(view);
    }

    public AdaptiveScreenBeta setFiller(FillerCall call){

        fillerCall = call;
        return this;
    }

    public void start(){

        if (view.getId() == android.R.id.content && fillerCall != null) {

            FillerCreator.create(((ViewGroup) view).getChildAt(0).getContext());
        }

        ViewSizeChangeFactory.create(view, fillerCall).change(view);
    }
}
