package com.turbo.asynclayoutinflater.ext

import com.turbo.asynclayoutinflater.DataCenter

/**
 * @author leiiiooo
 * @date 2020/4/30
 */
internal fun DataCenter.removeTaskInfo(tag: String) {
    taskInfo.remove(tag)
}

internal fun DataCenter.clearResultAndLatch(tag: String) {
    results.remove(tag)
    latchMap.remove(tag)
}