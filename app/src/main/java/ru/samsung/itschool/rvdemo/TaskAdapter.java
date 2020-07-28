package ru.samsung.itschool.rvdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import ru.samsung.itschool.rvdemo.database.Task;
import ru.samsung.itschool.rvdemo.databinding.RvListItemBinding;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ItemViewHolder> {

    private ItemClickListener itemClickListener;

    protected TaskAdapter(@NonNull TaskDiffCallback diffCallback, ItemClickListener itemClickListener) {
        super(diffCallback);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (getCurrentList() == null) {
            holder.binding.taskNametextView.setText("No tasks");
        }
        else {
            Task task = getItem(position);
            holder.bind(task, itemClickListener);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private RvListItemBinding binding;
        private ItemClickListener itemClickListener;

        private ItemViewHolder(@NonNull RvListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static ItemViewHolder from(@NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RvListItemBinding binding = RvListItemBinding.inflate(layoutInflater, parent, false);
            return new ItemViewHolder(binding);
        }

        public void bind(Task task, ItemClickListener itemClickListener) {
            binding.setTask(task);
            binding.setClickListener(itemClickListener);
            binding.executePendingBindings();
        }

    }
}
