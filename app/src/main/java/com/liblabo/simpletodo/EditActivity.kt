package com.liblabo.simpletodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class EditActivity : AppCompatActivity(), Callback, View.OnClickListener {
    val gson: Gson = Gson()
    var task: Task = Task()
    var editTitle: EditText? = null
    var editDescription: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        editTitle = findViewById(R.id.edit_txt_title)
        editDescription = findViewById(R.id.edit_txt_description)
        val btnSave = findViewById<Button>(R.id.btn_save)
        btnSave.setTag("SAVE")
        if (intent.hasExtra("TASK")) {
            task = gson.fromJson(intent.getStringExtra("TASK"), Task::class.java)
            editTitle?.setText(task.title)
            editDescription?.setText(task.description)
            btnSave.setText("Update")
            btnSave.setOnClickListener(this)
            btnSave.setTag("UPDATE")
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT)
    }

    override fun onResponse(call: Call, response: Response) {
        when (call.request().tag()) {
            "CREATE" -> Toast.makeText(this, "Saved task", Toast.LENGTH_SHORT)
            "UPDATE" -> Toast.makeText(this, "Updated task", Toast.LENGTH_SHORT)
        }
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        task.title = editTitle?.text.toString()
        task.description = editDescription?.text.toString()
        when (v?.getTag()) {
            "SAVE" -> TaskClient(this).create(task)
            "UPDATE" -> TaskClient(this).update(task)
        }
    }
}