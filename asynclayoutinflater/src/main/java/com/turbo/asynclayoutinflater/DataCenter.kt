package com.turbo.asynclayoutinflater

import com.turbo.asynclayoutinflater.model.LayoutResult
import com.turbo.asynclayoutinflater.model.TaskInfo
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ScheduledFuture

/**
 * @author leiiiooo
 * @date 2020/4/30
 */
internal object DataCenter {
    val results: ConcurrentHashMap<String, LayoutResult> by lazy {
        ConcurrentHashMap<String, LayoutResult>()
    }

    val taskInfo: ConcurrentHashMap<String, TaskInfo> by lazy {
        ConcurrentHashMap<String, TaskInfo>()
    }

    val latchMap: ConcurrentHashMap<String, CountDownLatch> by lazy {
        ConcurrentHashMap<String, CountDownLatch>()
    }

    val scheduleMap: ConcurrentHashMap<String, ScheduledFuture<*>> by lazy {
        ConcurrentHashMap<String, ScheduledFuture<*>>()
    }
}