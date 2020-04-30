package com.turbo.asynclayoutinflater.model

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
internal sealed class State
internal object IdleState : State()
internal object InflatingState : State()
internal object CancelState : State()