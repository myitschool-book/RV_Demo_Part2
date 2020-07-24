package ru.samsung.itschool.rvdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ru.samsung.itschool.rvdemo.database.Task;
import ru.samsung.itschool.rvdemo.databinding.FragmentAddTaskBinding;

public class AddTaskFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private FragmentAddTaskBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false);
        setHasOptionsMenu(true);
        binding.setLifecycleOwner(this);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskViewModel.getEventTaskAdd().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                taskViewModel.eventTaskAddFinished();
                NavHostFragment.findNavController(this).navigate(R.id.action_addTaskFragment_to_tasksListFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.overflow_add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_task) {
            taskViewModel.insertTask(new Task(binding.editTaskName.getText().toString(),
                    binding.editTaskDescription.getText().toString(),
                    Task.TASK_STATUS_NOT_FINISHED,
                    binding.importanceSpinner.getSelectedItemPosition()));
            return true;
        }
        else {
            return NavigationUI.onNavDestinationSelected(item, NavHostFragment.findNavController(this)) || super.onOptionsItemSelected(item);
        }
    }

}