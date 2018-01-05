package com.example.heartx.greendao_exercies.app;

import android.app.Application;

import com.example.heartx.greendao_exercies.entity.User;
import com.example.heartx.greendao_exercies.greendao.db.DaoMaster;
import com.example.heartx.greendao_exercies.greendao.db.DaoSession;
import com.example.heartx.greendao_exercies.greendao.db.UserDao;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;


public class App extends Application{

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.addLogAdapter(new AndroidLogAdapter()/*{
                 @Override
                 public boolean isLoggable(int priority, String tag) {
                     return BuildConfig.DEBUG;
                 }
             }*/);

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "test2.db",null);

        daoSession = new DaoMaster(openHelper.getWritableDatabase()).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
