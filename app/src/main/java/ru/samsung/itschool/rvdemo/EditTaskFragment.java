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

import ru.samsung.itschool.rvdemo.databinding.FragmentEditTaskBinding;

public class EditTaskFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private FragmentEditTaskBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_task, container, false);
        setHasOptionsMenu(true);
        binding.setLifecycleOwner(this);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getTask(5);
        binding.setTaskViewModel(taskViewModel);

        taskViewModel.task.observe(getViewLifecycleOwner(), task -> {
            if (task != null) {
                binding.importanceSpinner.setSelection(task.getTaskImportance());
            }
        });

        taskViewModel.getEventTaskUpdate().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                taskViewModel.eventTaskUpdateFinished();
                NavHostFragment.findNavController(this).navigate(R.id.action_editTaskFragment_to_tasksListFragment);
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.overflow_edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_task) {
            taskViewModel.task.getValue().setTaskName(binding.editTaskName.getText().toString());
            taskViewModel.task.getValue().setTaskDiscription(binding.editTaskDescription.getText().toString());
            taskViewModel.task.getValue().setTaskImportance(binding.importanceSpinner.getSelectedItemPosition());
            taskViewModel.updateTask();
            return true;
        }
        else {
            return NavigationUI.onNavDestinationSelected(item, NavHostFragment.findNavController(this)) || super.onOptionsItemSelected(item);
        }
    }

}