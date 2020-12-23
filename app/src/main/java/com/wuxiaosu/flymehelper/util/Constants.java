package com.wuxiaosu.flymehelper.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by su on 2020/11/5.
 */

public class Constants {

    public static final String SETTINGS_PKG = "com.android.settings";
    public static final String WEATHER_PKG = "com.meizu.flyme.weather";

    public static final List<String> HOOK_PKGS = new ArrayList<>();

    static {
        HOOK_PKGS.add(SETTINGS_PKG);
        HOOK_PKGS.add(WEATHER_PKG);
    }
}
