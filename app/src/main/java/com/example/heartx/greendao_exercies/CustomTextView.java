package com.example.heartx.greendao_exercies;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HeartX on 2017/12/21.
 */

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);

            int anInt = typedArray.getInt(R.styleable.CustomView_cName, 65536);

            Logger.d(anInt);

            typedArray.recycle();

            List<String> list = Collections.unmodifiableList(new ArrayList<String>());
        }

    }
}
