package com.michau.recycler_view

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_todo_item.view.*

class TodoAdapter(var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //zapisuje pola z layoutu do zmiennych
        var todoLabel = holder.itemView.tvTodoLabel
        var isDone = holder.itemView.cbIsDone

        //zapisuje wartości w polach
        todoLabel.text = todos[position].title
        isDone.isChecked = todos[position].isDone
    }

}