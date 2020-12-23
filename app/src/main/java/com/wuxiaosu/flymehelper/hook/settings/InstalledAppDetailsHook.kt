package com.wuxiaosu.flymehelper.hook.settings

import android.content.pm.PackageInfo
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * Created by su on 2020/12/17.
 */
class InstalledAppDetailsHook {

    companion object {
        fun hook(classLoader: ClassLoader?) {
            val clz = XposedHelpers.findClass("com.meizu.settings.applications.InstalledAppDetails", classLoader)

            XposedHelpers.findAndHookMethod(clz,
                    "setAppLabelAndIcon", PackageInfo::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)

                    val pkgInfo = param!!.args[0]
                    val mAppVersion = XposedHelpers.getObjectField(param.thisObject, "mAppVersion")

                    XposedHelpers.callMethod(mAppVersion, "setText",
                            "版本 " + XposedHelpers.getObjectField(pkgInfo, "versionName")
                                    + "\n" + XposedHelpers.getObjectField(pkgInfo, "packageName"))
                }
            })
        }
    }
}