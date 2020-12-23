package com.wuxiaosu.flymehelper

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.wuxiaosu.flymehelper.hook.settings.InstalledAppDetailsHook
import com.wuxiaosu.flymehelper.hook.settings.SettingsActivityHook
import com.wuxiaosu.flymehelper.hook.weather.MultiTypeAdapterHook
import com.wuxiaosu.flymehelper.hook.weather.ViewBinderForSuggestsHook
import com.wuxiaosu.flymehelper.hook.weather.WebViewHook
import com.wuxiaosu.flymehelper.util.Constants
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by su on 2020/10/12.
 */
class Main : IXposedHookLoadPackage {
    companion object {
        const val TAG = "flymehelper"
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        val packageName = lpparam!!.packageName

        if (Constants.HOOK_PKGS.contains(packageName)) {
            val classLoader = lpparam.classLoader

            when (packageName) {
                Constants.SETTINGS_PKG -> {
                    InstalledAppDetailsHook.hook(classLoader)
                    SettingsActivityHook.hook(classLoader)
                }
                Constants.WEATHER_PKG -> {
                    ViewBinderForSuggestsHook.hook(classLoader)
                    WebViewHook.hook(classLoader)
                    MultiTypeAdapterHook.hook(classLoader)
                }
                else -> {
                }
            }

            if (BuildConfig.DEBUG) {
                Logger.addLogAdapter(
                    AndroidLogAdapter(
                        PrettyFormatStrategy.newBuilder()
                            .showThreadInfo(false)
                            .methodCount(0)
                            .methodOffset(7)
                            .tag(TAG)
                            .build()
                    )
                )
            }
        }
    }
}
