package com.turbo.asynclayoutinflater.model

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
class InflateConfig(
    @NonNull val context: Context,
    @NonNull val tag: String,
    @LayoutRes val layoutId: Int,
    @Nullable val parent: ViewGroup? = null
) {
}

