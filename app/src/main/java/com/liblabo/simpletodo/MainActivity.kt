package com.liblabo.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ListView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() , Callback {
    val taskList: ArrayList<Task> = ArrayList()
    val gson: Gson = Gson()
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskListView: ListView = findViewById(R.id.list_task)
        taskListView.adapter = TaskListAdapter(this, R.layout.task_list_item, taskList)
        TaskClient(this).list()
    }

    override fun onFailure(call: Call, e: IOException) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call, response: Response) {
        handler.post {
            if (call.request().tag()!!.equals("LIST")) {
                val tasks = gson.fromJson<Array<Task>>(response.body().toString(), Array<Task>::class.java)
                taskList.clear()
                taskList.addAll(tasks)
            }
        }
    }
}
