package com.example.physicsformulas

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.formulas)

        button.setOnClickListener {
            val intent:Intent = Intent(this@MainActivity, TerminsActivity::class.java)
            this@MainActivity.startActivity(intent)
        }
    }
}
