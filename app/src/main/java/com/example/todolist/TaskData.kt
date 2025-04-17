package com.example.todolist

class TaskData(string: String, bool: Boolean) {
    var title: String = "Task"
    var isDone: Boolean = false
    init {
        title = string
        isDone = bool
    }

}