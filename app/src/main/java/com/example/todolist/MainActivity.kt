package com.example.todolist

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.EditText
import Adapters.TaskAdapter
import androidx.appcompat.app.AlertDialog
import com.example.todolist.TaskData

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var taskAdapter: TaskAdapter
    val taskList = mutableListOf<TaskData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and Adapter
        recyclerView = findViewById(R.id.todolistRecycleView)
        taskAdapter = TaskAdapter(taskList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        // Add initial tasks
        taskList.add(TaskData("Task1", false))
        taskList.add(TaskData("Task2", false))
        taskList.add(TaskData("Task3", false))
        taskList.add(TaskData("Task4", false))
        taskList.add(TaskData("Task5", false))

        // Add task button (Floating Action Button)
        val fab: FloatingActionButton = findViewById(R.id.fabAdd)
        fab.setOnClickListener {
            // Create the dialog to add a new task
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
            val editTextTask: EditText = dialogView.findViewById(R.id.editTextTask)

            val builder = AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(dialogView)
                .setPositiveButton("Add") { dialog, _ ->
                    val taskName = editTextTask.text.toString()
                    if (taskName.isNotEmpty()) {
                        val newTask = TaskData(taskName, false)
                        taskList.add(newTask)
                        taskAdapter.notifyItemInserted(taskList.size - 1)
                    } else {
                        Toast.makeText(this, "Task name cannot be empty!", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }

            builder.create().show()
        }
    }
}
