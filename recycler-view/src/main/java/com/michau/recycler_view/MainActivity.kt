package com.michau.recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList:MutableList<Todo> = mutableListOf(
            Todo("make somthing", false),
            Todo("make somthing next", false),
            Todo("to do", false),
            Todo("get cristal", false),
            Todo("make pottery", true),
            Todo("make somthing", false),
            Todo("make somthing again", false),
            Todo("do nothing", true)
        )

        var adapter = TodoAdapter(todoList)
        rvTodo.adapter = adapter
        // layoutManager ustawia sposob wyświetlania recycler view (lista siatka itp)
        rvTodo.layoutManager=LinearLayoutManager(this)


        btnAdd.setOnClickListener{
            var todo = Todo(etNewTodoLabel.text.toString(), false)
            todoList.add(todo)
            //robi updeta widoku
            adapter.notifyItemInserted(todoList.size-1)
        }
    }
}
