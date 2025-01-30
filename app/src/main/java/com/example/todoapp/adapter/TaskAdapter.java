package com.example.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, time;
        CheckBox completed;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskName);
            description = itemView.findViewById(R.id.taskDescription);
            time = itemView.findViewById(R.id.taskTime);
            completed = itemView.findViewById(R.id.cbTaskCompleted);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.name.setText(task.getName());
        if(task.getTime() != null){
            holder.time.setText(task.getTime().toString());
        }
        holder.description.setText(task.getDescription());
        holder.completed.setChecked(task.isCompleted());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
