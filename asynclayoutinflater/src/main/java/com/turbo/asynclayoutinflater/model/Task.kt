package com.turbo.asynclayoutinflater.model

import android.content.Context
import com.turbo.asynclayoutinflater.DataCenter
import com.turbo.asynclayoutinflater.Inflater.BasicInflater
import com.turbo.asynclayoutinflater.ext.removeTaskInfo

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
internal class Task(private val context: Context, val tag: String) : Runnable {
    override fun run() {
        val taskInfo = DataCenter.taskInfo[tag]
        taskInfo?.also {
            DataCenter.scheduleMap.remove(tag)
            if (taskInfo.state == CancelState) {
                DataCenter.removeTaskInfo(tag)
                return
            }

            if (taskInfo.state == InflatingState) {
                return
            }

            taskInfo.state = InflatingState

            try {
                DataCenter.results[tag] =
                    LayoutResult(
                        BasicInflater(context).inflate(
                            taskInfo.config.layoutId,
                            taskInfo.config.parent,
                            false
                        )
                    )
                DataCenter.removeTaskInfo(tag)
            } catch (throwable: Throwable) {
                DataCenter.removeTaskInfo(tag)
            } finally {
                val countDownLatch = DataCenter.latchMap[tag]
                countDownLatch ?: return
                countDownLatch.countDown()
            }
        }
    }
}