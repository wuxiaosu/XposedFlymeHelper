package com.wuxiaosu.flymehelper.hook.settings

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.util.List

/**
 * Created by su on 2020/12/17.
 */
class SettingsActivityHook {


    companion object {
        val removeList = arrayListOf("数字健康", "Aicy", "实验室")

        fun hook(classLoader: ClassLoader?) {
            val clz = XposedHelpers.findClass("com.android.settings.SettingsActivity", classLoader)
            XposedHelpers.findAndHookMethod(clz,
                    "onResume", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)

                    val mDashboardFeatureProvider = XposedHelpers.getObjectField(param!!.thisObject, "mDashboardFeatureProvider")

                    val list = XposedHelpers.callMethod(mDashboardFeatureProvider, "getAllCategories") as List<Object>

                    for (i in 0 until list.size) {
                        val item = list.get(i)

                        val mTiles = XposedHelpers.getObjectField(item, "mTiles") as ArrayList<Object>
                        val newTiles = java.util.ArrayList<Object>()

                        for (j in 0 until mTiles.size) {
                            val title = mTiles.get(j)
                            if (!removeList.contains(
                                            XposedHelpers.getObjectField(title, "title"))) {
                                newTiles.add(title)
                            }
                        }
                        XposedHelpers.setObjectField(item, "mTiles", newTiles)
                    }
                }
            })
        }
    }
}