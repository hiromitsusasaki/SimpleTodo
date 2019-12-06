package com.liblabo.simpletodo

import okhttp3.*

class TaskClient(val callback: Callback) {

    val client: OkHttpClient = OkHttpClient()
    val HOST = "http:/10.0.2.2:3000"
    val URL = HOST + "/api/v1/tasks/"

    fun list() {
        val request = Request.Builder()
            .url(URL).get().tag("LIST").build()
        client.newCall(request).enqueue(callback)
    }

    fun create(task: Task) {
        val requestBody = createRequestBody(task)
        val request = Request.Builder().addHeader("Content-Type", "application/json")
            .url(URL).post(requestBody).tag("CREATE").build()
        client.newCall(request).enqueue(callback)
    }
    fun update(task: Task) {
        val requestBody = createRequestBody(task)
        val request = Request.Builder()
            .url(URL + task.id).patch(requestBody).tag("UPDATE").build()
        client.newCall(request).enqueue(callback)
    }

    fun delete(task: Task) {
        val requestBody = createRequestBody(task)
        val request = Request.Builder()
            .url(URL + task.id).delete(requestBody).tag("DELETE").build()
        client.newCall(request).enqueue(callback)
    }

    private fun createRequestBody(task: Task):RequestBody {
        return FormBody.Builder()
            .add("task[id]", task.id.toString())
            .add("task[title]", task.title)
            .add("task[description]", task.description)
            .build()
    }
}