package com.liblabo.simpletodo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class EditActivity : AppCompatActivity(), Callback, View.OnClickListener {
    val gson: Gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val editTitle = findViewById<EditText>(R.id.edit_txt_title)
        val editDescription = findViewById<EditText>(R.id.edit_txt_description)
        val btnSave = findViewById<Button>(R.id.btn_save)
        if (intent.hasExtra("TASK")) {
            val task = gson.fromJson(intent.getStringExtra("TASK"), Task::class.java)
            editTitle.setText(task.title)
            editDescription.setText(task.description)
            btnSave.setText("Update")
            btnSave.setOnClickListener(this)
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call, response: Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {

    }
}