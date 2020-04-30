package com.turbo.asynclayoutinflater

import android.content.MutableContextWrapper
import android.view.LayoutInflater
import android.view.View
import com.turbo.asynclayoutinflater.exception.LayoutHelperException
import com.turbo.asynclayoutinflater.ext.checkAll
import com.turbo.asynclayoutinflater.ext.clearResultAndLatch
import com.turbo.asynclayoutinflater.model.*
import java.util.concurrent.CountDownLatch

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
object AsyncInflaterHelper {

    @Throws(LayoutHelperException::class)
    fun inflate(vararg items: InflateConfig) {
        items.forEach {
            it.takeIf {
                it.checkAll()
            }?.also { inflateConfig ->
                if (DataCenter.taskInfo[inflateConfig.tag] !== null) {
                    throw LayoutHelperException("please do not use the same tag for tasks that are still queued")
                }

                DataCenter.taskInfo[inflateConfig.tag] = TaskInfo(inflateConfig)
                DataCenter.latchMap[inflateConfig.tag] = CountDownLatch(1)
                Dispatcher.run(Task(inflateConfig.context, inflateConfig.tag))
            }
        }
    }

    @Throws(LayoutHelperException::class)
    fun fetchView(item: InflateConfig): View {
        if (item.checkAll()) {
            val tag = item.tag
            var layoutResult = DataCenter.results[tag]
            if (layoutResult !== null) {
                DataCenter.clearResultAndLatch(tag)
                return postProcessing(item, layoutResult)
            }

            val taskInfo = DataCenter.taskInfo[tag]
            if (taskInfo === null) {
                return LayoutInflater.from(item.context).inflate(item.layoutId, item.parent)
            }
            if (taskInfo.state == IdleState) {
                DataCenter.taskInfo.remove(tag)
                DataCenter.latchMap.remove(tag)
                DataCenter.scheduleMap[tag]?.cancel(false)
                return LayoutInflater.from(item.context).inflate(item.layoutId, item.parent)
            }
            if (taskInfo.state == InflatingState) {
                val countDownLatch = DataCenter.latchMap[tag]
                countDownLatch?.await()

                layoutResult = DataCenter.results[tag]
                if (layoutResult !== null) {
                    DataCenter.clearResultAndLatch(tag)
                    return postProcessing(item, layoutResult)
                }
            }
        }

        throw LayoutHelperException("please check params for InflateConfig`s tag or layoutId")
    }

    private fun postProcessing(inflateConfig: InflateConfig, layoutResult: LayoutResult): View {
        val context = layoutResult.view.context
        if (context is MutableContextWrapper) {
            context.baseContext = inflateConfig.context
        }
        return layoutResult.view
    }
}