package com.wuxiaosu.flymehelper.hook.weather

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers


/**
 * Created by su on 2020/12/22.
 */
class MultiTypeAdapterHook {

    companion object {

        fun hook(classLoader: ClassLoader?) {

            val clz = XposedHelpers.findClass(
                    "multitype.MultiTypeAdapter",
                    classLoader)

            XposedHelpers.findAndHookMethod(clz,
                    "setItems", List::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)

                    if ("com.meizu.flyme.weather.modules.home.page.view.WeatherInfoAdapter"
                                    .equals(param!!.thisObject.javaClass.name)) {
                        val arrayList = param!!.args[0] as ArrayList<Object>
                        val newList = java.util.ArrayList<Object>()

                        for (i in 0 until arrayList.size) {
                            val data = arrayList.get(i)

                            // 主界面移除中间视频按钮
                            if (!"com.meizu.flyme.weather.modules.home.page.view.forecast.bean.ArticleEntityBean"
                                            .equals(data.javaClass.name)) {
                                newList.add(data)
                            }
                        }
                        param.args[0] = newList
                    } else {
                        val arrayList = param.args[0] as ArrayList<Object>
                        val newList = java.util.ArrayList<Object>()

                        for (i in 0 until arrayList.size) {
                            val data = arrayList.get(i)

                            // 指数界面移除推荐
                            if (!"com.meizu.flyme.weather.modules.warn.detail.bean.CategoryForNewsFlow"
                                            .equals(data.javaClass.name) && !"com.meizu.flyme.weather.modules.index.bean.NewsFlowBean"
                                            .equals(data.javaClass.name)) {
                                newList.add(data)
                            }
                        }
                        param.args[0] = newList
                    }
                }
            })
        }
    }
}