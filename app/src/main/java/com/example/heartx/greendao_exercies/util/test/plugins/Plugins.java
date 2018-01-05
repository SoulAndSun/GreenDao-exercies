package com.example.heartx.greendao_exercies.util.test.plugins;

import com.example.heartx.greendao_exercies.util.test.AdaptiveScreenBeta;

/**
 * 重新设计
 * Created by HeartX on 2017/12/20.
 */

public class Plugins {

    static Check<AdaptiveScreenBeta, AdaptiveScreenBeta> check;

    public static AdaptiveScreenBeta onAssembly(AdaptiveScreenBeta a){

        Check<AdaptiveScreenBeta, AdaptiveScreenBeta> c = check;

        if (c != null) {
            return check(c, a);
        }

        return a;
    }

    static <T, R> R check(Check<T, R> c, T t) {

        try {
            return c.check(t);
        } catch (Throwable e) {
            if (e instanceof Error) {
                throw (Error)e;
            }
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }
            throw new RuntimeException(e);
        }
    }
}
