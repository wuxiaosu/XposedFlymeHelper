package com.wuxiaosu.flymehelper;

import com.wuxiaosu.flymehelper.util.Constants;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by su on 2020/11/5.
 */

public class HookLogic implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (Constants.HOOK_PKGS.contains(lpparam.packageName)) {
            /*Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)
                    .methodCount(0)
                    .methodOffset(7)
                    .tag(Main.TAG)
                    .build()));*/

            ClassLoader classLoader = lpparam.classLoader;

//            InstalledAppDetailsHook.Companion.hook(classLoader);
//            SettingsActivityHook.Companion.hook(classLoader);
//            ViewBinderForSuggestsHook.Companion.hook(classLoader);
//            WebViewHook.Companion.hook(classLoader);
//            MultiTypeAdapterHook.Companion.hook(classLoader);
        }
    }
}