package com.example.physicsformulas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TerminActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termin)

        val terminId:String = intent.getStringExtra("id") ?: "-1"

        val db = DatabaseHelper(this)
        val termin = db.getDataTermin("SELECT * FROM phy_termins\nWHERE id = $terminId;")

        val terminName = termin.name.split('-')[0].replace(" ", "")
        val formulasData = db.getDataFormulas("""SELECT * FROM formulas
             WHERE formula LIKE "%${terminName}%"; """)

        val name:TextView = findViewById(R.id.textView_defination)
        val systemSi:TextView = findViewById(R.id.system_Si)
        val decription:TextView = findViewById(R.id.decription)
        val recyclerView:RecyclerView = findViewById(R.id.formulas)
        val backButton:Button = findViewById(R.id.back_button_to_termins)

        val scrollView:ScrollView = findViewById(R.id.scrollViewTermin)

        scrollView.overScrollMode = View.OVER_SCROLL_ALWAYS// Добавляем свойство overScrollMode
        val interpolator = DecelerateInterpolator() //Создаём анимацию
        scrollView.animate().interpolator = interpolator// Устанавливаем анимацию для ScrollView

        recyclerView.isNestedScrollingEnabled = false

        val recyclerViewAdapter = RecyclerViewFormulas(this, formulasData)

        name.text = "${getString(R.string.name_termin)} ${termin.name}"
        systemSi.text = "${getString(R.string.system_Si)} ${termin.unit}"
        decription.text = "${getString(R.string.description)} ${termin.description}"

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}