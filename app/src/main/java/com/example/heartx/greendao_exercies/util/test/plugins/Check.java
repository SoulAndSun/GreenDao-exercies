package com.example.heartx.greendao_exercies.util.test.plugins;

/**
 * 重新设计
 * Created by HeartX on 2017/12/20.
 */

public interface Check<T, R> {

    R check(T t) throws Exception;
}
