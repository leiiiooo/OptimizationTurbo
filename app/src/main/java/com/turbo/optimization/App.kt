package com.turbo.optimization

import android.app.Application
import android.content.Context
import com.turbo.asynclayoutinflater.AsyncInflaterHelper
import com.turbo.asynclayoutinflater.model.InflateConfig

/**
 * @author leiiiooo
 * @date 2020/4/30
 */
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        AsyncInflaterHelper.inflate(InflateConfig(this, "main", R.layout.layout_center_image))
    }
}