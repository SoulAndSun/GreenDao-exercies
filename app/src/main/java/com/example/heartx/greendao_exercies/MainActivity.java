package com.example.heartx.greendao_exercies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heartx.greendao_exercies.adpter.CustomAdapter;
import com.example.heartx.greendao_exercies.app.App;
import com.example.heartx.greendao_exercies.entity.User;
import com.example.heartx.greendao_exercies.greendao.db.DaoSession;
import com.example.heartx.greendao_exercies.greendao.db.UserDao;
import com.example.heartx.greendao_exercies.util.test.AdaptiveScreenBeta;
import com.example.heartx.greendao_exercies.util.test.FillerCall;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;

    private CustomAdapter adapter;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //AdaptiveScreenBeta.adaptive(findViewById(android.R.id.content));

        findView();

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertOrReplace(new User(2L, "8", "1"));

        getOverflowMenu();

    }

    //force to show overflow menu in actionbar
    private void getOverflowMenu() {
        ViewConfiguration config = ViewConfiguration.get(this);
        try {
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {

            Logger.e("onPrepareOptionsMenu", e);
        }
    }

    private void findView() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            list.add("" + i);
        }
        adapter = new CustomAdapter(this, list);

        lv = (ListView) findViewById(R.id.lv);

        lv.setAdapter(adapter);

        tv = (TextView) findViewById(R.id.tv2);

        tv.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activiry, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.startAdapt: {

                AdaptiveScreenBeta.adaptive(findViewById(android.R.id.content))
                        .setFiller(new FillerCall() {
                        })
                        .start();

                break;
            }

            case R.id.cancelAdapt: {

                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                    }

                    @Override
                    public String toString() {
                        return super.toString();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer value) {
                                setContentView(R.layout.test);
                                findView();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            }

            case R.id.other: {

                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //ScreenAdaptUtil.release();
        Logger.d("MainActivity onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv2:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //finish();
                break;
        }
    }
}
