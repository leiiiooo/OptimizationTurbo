package com.turbo.asynclayoutinflater.ext

import com.turbo.asynclayoutinflater.model.InflateConfig

/**
 * @author leiiiooo
 * @date 2020/4/30
 */

internal fun InflateConfig.checkAll(): Boolean {
    return !tag.isBlank() && layoutId > 0
}