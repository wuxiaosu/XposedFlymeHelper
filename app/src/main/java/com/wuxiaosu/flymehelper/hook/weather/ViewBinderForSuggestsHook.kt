package com.wuxiaosu.flymehelper.hook.weather

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * Created by su on 2020/12/19.
 */
class ViewBinderForSuggestsHook {
    companion object {

        val removeList = arrayListOf("日历", "今日热点", "魅族短视频")

        fun hook(classLoader: ClassLoader?) {
            val clz = XposedHelpers.findClass(
                    "com.meizu.flyme.weather.modules.home.page.view.suggest.ViewBinderForSuggests${'$'}ViewHolder",
                    classLoader)

            XposedHelpers.findAndHookMethod(clz,
                    "setSuggestData", ArrayList::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)

                    val arrayList = param!!.args[0] as ArrayList<Object>
                    val newList = java.util.ArrayList<Object>()

                    for (i in 0 until arrayList.size) {
                        val data = arrayList.get(i)

                        if (!removeList.contains(XposedHelpers.getObjectField(data, "name"))) {
                            newList.add(data)
                        }
                    }

                    param.args[0] = newList
                }
            })
        }
    }
}