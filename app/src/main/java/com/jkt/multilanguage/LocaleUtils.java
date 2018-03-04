package com.jkt.multilanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import java.util.Locale;

public class LocaleUtils {
    /**
     * 中文
     */
    public static final Locale LOCALE_CHINESE = Locale.CHINESE;
    /**
     * 英文
     */
    public static final Locale LOCALE_ENGLISH = Locale.ENGLISH;
    /**
     * 俄文
     */
    public static final Locale LOCALE_RUSSIAN = new Locale("ru");
    /**
     * 保存SharedPreferences的文件名
     */
    private static final String LOCALE_FILE = "LOCALE_FILE";
    /**
     * 保存Locale的key
     */
    private static final String LOCALE_KEY = "LOCALE_KEY";

    /**
     * 获取用户设置的Locale
     * @param context Context
     * @return Locale
     */
    public static Locale getUserLocale(Context context) {
        SharedPreferences spLocale = context.getSharedPreferences(LOCALE_FILE, Context.MODE_PRIVATE);
        String localeJson = spLocale.getString(LOCALE_KEY, "");
        return jsonToLocale(localeJson);
    }
    /**
     * 获取当前的Locale
     * @param context Context
     * @return Locale
     */
    public static Locale getCurrentLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale;
    }
    /**
     * 保存用户设置的Locale
     * @param pContext Context
     * @param pUserLocale Locale
     */
    public static void saveUserLocale(Context pContext, Locale pUserLocale) {
        SharedPreferences spLocal=pContext.getSharedPreferences(LOCALE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=spLocal.edit();
        String json = localeToJson(pUserLocale);
        edit.putString(LOCALE_KEY, json);
        edit.apply();
    }
    /**
     * Locale转成json
     * @param userLocale UserLocale
     * @return json String
     */
    private static String localeToJson(Locale userLocale) {
        Gson gson = new Gson();
        return gson.toJson(userLocale);
    }
    /**
     * json转成Locale
     * @param localeJson LocaleJson
     * @return Locale
     */
    private static Locale jsonToLocale(String localeJson) {
        Gson gson = new Gson();
        return gson.fromJson(localeJson, Locale.class);
    }
    /**
     * 更新Locale
     * @param context Context
     * @param locale New User Locale
     */
    public static void updateLocale(Context context, Locale locale) {
        if (needUpdateLocale(context, locale)) {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale =locale;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, displayMetrics);
            saveUserLocale(context, locale);
        }
    }
    /**
     * 判断需不需要更新
     * @param pContext Context
     * @param newUserLocale New User Locale
     * @return true / false
     */
    public static boolean needUpdateLocale(Context pContext, Locale newUserLocale) {
        return newUserLocale != null && !getCurrentLocale(pContext).equals(newUserLocale);
    }
}
