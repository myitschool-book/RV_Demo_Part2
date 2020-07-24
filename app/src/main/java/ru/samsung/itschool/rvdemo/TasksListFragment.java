package ru.samsung.itschool.rvdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.samsung.itschool.rvdemo.database.Task;
import ru.samsung.itschool.rvdemo.databinding.FragmentTasksListBinding;


public class TasksListFragment extends Fragment {

    private TasksViewModel tasksListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTasksListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks_list, container, false);
        binding.setLifecycleOwner(this);

        tasksListViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        binding.setTasksListViewModel(tasksListViewModel);

        setHasOptionsMenu(true);
        binding.floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_tasksListFragment_to_addTaskFragment);
        });

        tasksListViewModel.tasksList.observe(getViewLifecycleOwner(), tasks -> {
            if (tasks != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Task task :
                        tasks) {
                    stringBuilder.append(task.getTaskName() + " " + task.getTaskImportance() + "\n");
                }
                binding.tasksList.setText(stringBuilder.toString());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.overflow_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            tasksListViewModel.deleteAllTask();
            return true;
        }
        else {
            return NavigationUI.onNavDestinationSelected(item, NavHostFragment.findNavController(this)) || super.onOptionsItemSelected(item);
        }
    }
}