package ru.samsung.itschool.rvdemo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.samsung.itschool.rvdemo.database.Task;

public class TaskDiffCallback extends DiffUtil.ItemCallback<Task> {
    @Override
    public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        return oldItem.equals(newItem);
    }
}
