package com.liblabo.simpletodo

import com.google.gson.Gson
import okhttp3.*

class TaskClient(callback: Callback) {
    companion object {
        const val HOST = "http://localhost:3000"
        const val URL: String = HOST + "/api/v1/tasks/"
    }
    val client: OkHttpClient = OkHttpClient()
    val gson: Gson = Gson()
    val JSON: MediaType = MediaType.get("application/json; charset=utf-8")
    val callback: Callback = callback
    fun list() {
        val request = Request.Builder()
            .url(URL).get().tag("LIST").build()
        client.newCall(request).enqueue(callback)
    }

    fun create(task: Task) {
        val requestBody: RequestBody = RequestBody.create(JSON, gson.toJson(task))
        val request = Request.Builder()
            .url(URL + task.id).post(requestBody).tag("CREATE").build()
        client.newCall(request).enqueue(callback)
    }
    fun update(task: Task) {
        val requestBody: RequestBody = RequestBody.create(JSON, gson.toJson(task))
        val request = Request.Builder()
            .url(URL + task.id).patch(requestBody).tag("UPDATE").build()
        client.newCall(request).enqueue(callback)
    }

    fun delete(task: Task) {
        val requestBody: RequestBody = RequestBody.create(JSON, gson.toJson(task))
        val request = Request.Builder()
            .url(URL + task.id).delete(requestBody).tag("DELETE").build()
        client.newCall(request).enqueue(callback)
    }
}