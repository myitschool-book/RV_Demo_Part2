package ru.samsung.itschool.rvdemo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import ru.samsung.itschool.rvdemo.database.Task;

public class BindingUtils {

    @BindingAdapter("taskImpImage")
    public static void setImpImage(ImageView imageView, Task task) {
        int resImp;
        switch (task.getTaskImportance()) {
            case Task.TASK_IMPORTANCE_HIGH: resImp = R.drawable.ic_baseline_looks_one_60; break;
            case Task.TASK_IMPORTANCE_LOW: resImp = R.drawable.ic_baseline_looks_3_60; break;
            default: resImp = R.drawable.ic_baseline_looks_two_60;
        }
        imageView.setImageResource(resImp);
    }

    @BindingAdapter("taskStatusImage")
    public static void setsatusImage(ImageView imageView, Task task) {
        int resSt;
        switch (task.getTaskStatus()) {
            case Task.TASK_STATUS_FINISHED: resSt = R.drawable.ic_baseline_check_box_60; break;
            default: resSt = R.drawable.ic_baseline_check_box_outline_blank_60;
        }
        imageView.setImageResource(resSt);
    }
}
