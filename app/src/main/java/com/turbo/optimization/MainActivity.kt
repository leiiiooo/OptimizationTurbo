package com.turbo.optimization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.turbo.asynclayoutinflater.AsyncInflaterHelper
import com.turbo.asynclayoutinflater.model.InflateConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var demoView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show.setOnClickListener {
            container.addView(demoView)
        }
    }

    override fun onResume() {
        super.onResume()

        show.postDelayed({
            demoView =
                AsyncInflaterHelper.fetchView(
                    InflateConfig(
                        this,
                        "main",
                        R.layout.layout_center_image
                    )
                )

        }, 2000)
    }
}
