package com.turbo.asynclayoutinflater.model

import androidx.annotation.NonNull

/**
 * @author leiiiooo
 * @date 2020/4/29
 */

internal class TaskInfo(
    @NonNull var config: InflateConfig,
    @NonNull internal var state: State = IdleState
) {
}