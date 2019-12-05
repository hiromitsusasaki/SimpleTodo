package com.liblabo.simpletodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    val gson: Gson = Gson()
    var task: Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val textTitle = findViewById<TextView>(R.id.txt_title)
        val textDescription = findViewById<TextView>(R.id.txt_description)
        val btnEdit = findViewById<Button>(R.id.btn_edit)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        if (intent.hasExtra("TASK")) {
            task = gson.fromJson(intent.getStringExtra("TASK"), Task::class.java)
            textTitle.setText(task?.title)
            textDescription.setText(task?.description)
            btnEdit.setOnClickListener(this)
            btnDelete.setOnClickListener(this)
            btnEdit.setTag("EDIT")
            btnDelete.setTag("DELETE")
        }
    }

    override fun onClick(v: View?) {
        when (v?.getTag()) {
            "EDIT" -> {
                val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("TASK", gson.toJson(task))
            }
            startActivity(intent)
            }
            "DELETE" -> {

            }
        }
    }
}
