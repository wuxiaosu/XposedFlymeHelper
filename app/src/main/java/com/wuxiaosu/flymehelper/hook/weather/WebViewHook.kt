package com.wuxiaosu.flymehelper.hook.weather

import android.webkit.WebView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers


/**
 * Created by su on 2020/12/22.
 */
class WebViewHook {

    companion object {
        fun hook(classLoader: ClassLoader?) {
            // 15 天天气预报界面广告
            XposedHelpers.findAndHookMethod(WebView::class.java, "loadUrl",
                    String::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)

                    var url = param!!.args[0] as String

                    if (url.contains("adv=1")) {
                        url = url.replace("adv=1", "adv=0")
                    }

                    param.args[0] = url

                    if (url.startsWith("javascript:mzAdCallback")) {
                        param.result = null
                    }
                }
            })
        }
    }
}