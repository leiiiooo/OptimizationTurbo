package com.turbo.asynclayoutinflater.Inflater

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View

/**
 * @author leiiiooo
 * @date 2020/4/29
 */
internal class BasicInflater internal constructor(context: Context?) :
    LayoutInflater(context) {
    override fun cloneInContext(newContext: Context?): LayoutInflater {
        return BasicInflater(newContext)
    }

    @Throws(ClassNotFoundException::class)
    override fun onCreateView(
        name: String?,
        attrs: AttributeSet?
    ): View {
        for (prefix in sClassPrefixList) {
            try {
                val view = createView(name, prefix, attrs)
                if (view != null) {
                    return view
                }
            } catch (e: ClassNotFoundException) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }
        return super.onCreateView(name, attrs)
    }

    companion object {
        private val sClassPrefixList = arrayOf(
            "android.widget.",
            "android.webkit.",
            "android.app."
        )
    }
}