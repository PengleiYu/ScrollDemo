package com.example.scrolldemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_floating_view.*

/**
 * @author penglei on 2019/3/1
 */
class FloatViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_view)

        container_floatingView.img = img_floatingView
    }
}