package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.TaskData

class TaskAdapter(val taskList: MutableList<TaskData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskTitle.text = task.title
        holder.checkBox.isChecked = task.isDone

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isDone = isChecked
        }

        holder.view.setOnLongClickListener {
            // Defer the removal safely
            holder.view.post {
                if (position < taskList.size) {
                    taskList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            true
        }
    }


    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById(R.id.taskTitle)
        val checkBox: CheckBox = view.findViewById(R.id.checkBoxDone)
    }
}
