package com.suda.openpayshortcut.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author guhaibo
 * @date 2019/6/17
 */
public class SharePreferenceUtil {

    private static SharePreferenceUtil sharePreferenceUtil;
    private SharedPreferences sharedPreferences;

    public SharePreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("Pay", Context.MODE_PRIVATE);
    }

    public static SharePreferenceUtil getSharePreferenceUtil(Context context) {
        if (sharePreferenceUtil == null) {
            synchronized (SharePreferenceUtil.class) {
                if (sharePreferenceUtil == null) {
                    sharePreferenceUtil = new SharePreferenceUtil(context);
                }
            }
        }
        return sharePreferenceUtil;
    }

    public <T> T getValue(String key, T defaultValue) {
        if (defaultValue instanceof String) {
            return (T) sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Float) {
            return (T) new Float(sharedPreferences.getFloat(key, (Float) defaultValue));
        } else if (defaultValue instanceof Integer) {
            return (T) new Integer(sharedPreferences.getInt(key, (Integer) defaultValue));
        } else if (defaultValue instanceof Long) {
            return (T) new Long(sharedPreferences.getLong(key, (Long) defaultValue));
        } else if (defaultValue instanceof Boolean) {
            return (T) new Boolean(sharedPreferences.getBoolean(key, (Boolean) defaultValue));
        }
        return null;
    }


    public void putValue(String key, Object defaultValue) {
        if (defaultValue instanceof String) {
            sharedPreferences.edit().putString(key, (String) defaultValue).commit();
        } else if (defaultValue instanceof Float) {
            sharedPreferences.edit().putFloat(key, (Float) defaultValue).commit();
        } else if (defaultValue instanceof Integer) {
            sharedPreferences.edit().putInt(key, (Integer) defaultValue).commit();
        } else if (defaultValue instanceof Long) {
            sharedPreferences.edit().putLong(key, (Long) defaultValue).commit();
        } else if (defaultValue instanceof Boolean) {
            sharedPreferences.edit().putBoolean(key, (Boolean) defaultValue).commit();
        }
    }
}
