package com.jkt.multilanguage;

import android.app.Application;

import java.util.Locale;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Locale _UserLocale=LocaleUtils.getUserLocale(this);
        LocaleUtils.updateLocale(this, _UserLocale);
    }

}
