package com.liblabo.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() , Callback {
    val taskList: ArrayList<Task> = ArrayList()
    val gson: Gson = Gson()
    val handler = Handler()
    var adapter: TaskListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var taskListView: ListView = findViewById(R.id.list_task)
        adapter = TaskListAdapter(this, R.layout.task_list_item, taskList)
        taskListView.adapter = adapter
        TaskClient(this).list()
    }

    override fun onFailure(call: Call, e: IOException) {
        Log.d(this.javaClass.name, e.message)
    }

    override fun onResponse(call: Call, response: Response) {
        val jsonString = response.body()?.string()
        Log.d(this.javaClass.name, jsonString)
        handler.post {
            if (call.request().tag()!!.equals("LIST")) {
                val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
                Log.d(this.javaClass.name, jsonObject.toString())
                val jsonArray = jsonObject.getAsJsonArray("data")
                Log.d(this.javaClass.name, jsonArray.toString())
                val tasks = gson.fromJson<Array<Task>>(jsonArray, Array<Task>::class.java)
                Log.d(this.javaClass.name, tasks.toString())
                taskList.clear()
                taskList.addAll(tasks)
                adapter?.notifyDataSetChanged()
            }
        }
    }
}
