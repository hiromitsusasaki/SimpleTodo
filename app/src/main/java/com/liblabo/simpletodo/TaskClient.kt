package com.liblabo.simpletodo

import okhttp3.*
import java.io.IOException

class TaskClient {
    companion object {
        const val LIST_URL: String = ""
        const val CREATE_URL: String = ""
        const val UPDATE_URL: String = ""
        const val DELETE_URL: String = ""
    }
    val client: OkHttpClient = OkHttpClient()
    val JSON: MediaType = MediaType.get("application/json; charset=utf-8")

    fun list() {
        val request = Request.Builder()
            .url(LIST_URL).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun create(task: Task) {
        // create new task }
    }
    fun update(task: Task) {
        // update task
    }

    fun delete(task: Task) {
        // delete task
    }
}