package com.example.scrolldemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.scrolldemo.customer.CustomerScrollerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val map = mapOf(
        "自定义滚动" to CustomerScrollerActivity::class.java
        , "浮动View" to FloatViewActivity::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = map.keys.toList()
        listView_main.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView_main.setOnItemClickListener { parent, view, position, id ->
            startActivity(Intent(this, map[list[position]]))
        }
    }
}
