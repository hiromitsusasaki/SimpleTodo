package com.liblabo.simpletodo

import okhttp3.OkHttpClient

class TaskClient {
    val client: OkHttpClient = OkHttpClient()

    fun list() : Array<Task>? {
        // get task list
        return null;
    }

    fun create(task: Task) {
        // create new task
    }

    fun update(task: Task) {
        // update task
    }

    fun delete(task: Task) {
        // delete task
    }
}