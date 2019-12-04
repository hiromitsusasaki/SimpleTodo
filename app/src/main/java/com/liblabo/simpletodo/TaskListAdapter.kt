package com.liblabo.simpletodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class TaskListAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val tasks: List<Task>):
    ArrayAdapter<Task>(context, layoutResource, tasks) {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = null
        if (convertView != null) {
            view = convertView
        } else {
            view = inflater.inflate(layoutResource, null)
        }
        val task: Task = tasks.get(position)
        val taskTitle: TextView? = view?.findViewById(R.id.task_title)
        taskTitle?.setText(task.title)
        return view!!
    }
}