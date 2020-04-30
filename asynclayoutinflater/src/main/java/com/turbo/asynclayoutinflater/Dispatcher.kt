package com.turbo.asynclayoutinflater

import com.turbo.asynclayoutinflater.model.Task
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
internal object Dispatcher {
    private const val taskDelayTime = 0L

    private val threadPool: ExecutorService by lazy {
        Executors.newSingleThreadScheduledExecutor()
    }

    internal fun run(runnerTask: Task) {
        val tag = runnerTask.tag
        if (DataCenter.results.contains(tag)) {
            return
        }
        if (DataCenter.taskInfo.contains(tag)) {
            return
        }

        val schedule = (threadPool as ScheduledExecutorService).schedule(
            runnerTask,
            taskDelayTime,
            TimeUnit.SECONDS
        )

        DataCenter.scheduleMap[tag] = schedule
    }
}