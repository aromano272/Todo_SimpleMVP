package com.example.aromano.mvpsimple.main;

import android.support.annotation.NonNull;

import com.example.aromano.mvpsimple.addtask.AddEditTaskActivity;
import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by aRomano on 20/09/2016.
 */

public class TasksPresenter implements ITasksPresenter {

    private final ITasksDataSource tasksRepository;

    private final WeakReference<ITasksView> tasksView;

    public TasksPresenter(@NonNull ITasksDataSource tasksRepository, @NonNull ITasksView tasksView) {
        this.tasksRepository = tasksRepository;
        this.tasksView = new WeakReference<>(tasksView);

        tasksView.setPresenter(this);
    }

    @Override
    public void addTaskClicked() {
        tasksView.get().showAddTask();
    }

    @Override
    public void editTaskClicked(Task task) {
        tasksView.get().showEditTask(task);
    }

    @Override
    public void deleteTaskClicked(Task task) {
        tasksView.get().showDeleteTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        tasksRepository.deleteTask(task);
        loadTasks(false);
    }

    @Override
    public void alterTaskState(Task task) {
        tasksRepository.alterTaskState(task);
        loadTasks(false);
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        tasksView.get().showProgressBar(true);

        if (forceUpdate) {
            tasksRepository.invalidateData();
        }

        tasksRepository.loadTasks(new ITasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                tasksView.get().showProgressBar(false);
                processTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                tasksView.get().showLoadingTasksError();
            }
        });
    }

    private void processTasks(List<Task> tasks) {
        // Show the list of tasks
        tasksView.get().showTasks(tasks);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        switch (requestCode) {
            case AddEditTaskActivity.RC_ADD_TASK:
                loadTasks(false);
                break;
        }
    }
}
