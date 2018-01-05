package com.example.heartx.greendao_exercies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;

import com.example.heartx.greendao_exercies.util.adaptive.AdaptiveScreen;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdaptiveScreen.adaptive(findViewById(android.R.id.content));

        getOverflowMenu();
    }

    //force to show overflow menu in actionbar
    private void getOverflowMenu() {
        ViewConfiguration config = ViewConfiguration.get(this);
        try {
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {

            Logger.e("onPrepareOptionsMenu",e);
        }
    }

    @Override
    protected void onDestroy() {
        Logger.d("SecondActivity onDestroy");
        super.onDestroy();
    }
}
