package com.example.physicsformulas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TerminsActivity : AppCompatActivity(), RecyclerViewInterface{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termins)

        val db = DatabaseHelper(this)

        val termins = db.getDataNames("SELECT * FROM phy_termins")

        val recyclerView:RecyclerView = findViewById(R.id.all_names)

        val recyclerViewAdapter = RecyclerViewAdapter(this, termins, this)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@TerminsActivity, TerminActivity::class.java)

        intent.putExtra("id", (position+1).toString())
        this@TerminsActivity.startActivity(intent)
    }
}