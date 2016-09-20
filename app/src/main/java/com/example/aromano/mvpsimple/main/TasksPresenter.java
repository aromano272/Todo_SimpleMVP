package com.example.aromano.mvpsimple.main;

import android.support.annotation.NonNull;

import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;

import java.util.List;

/**
 * Created by aRomano on 20/09/2016.
 */

public class TasksPresenter implements ITasksPresenter {

    private final ITasksDataSource tasksRepository;

    private final ITasksView tasksView;

    private boolean isFirstLoad = true;

    public TasksPresenter(@NonNull ITasksDataSource tasksRepository, @NonNull ITasksView tasksView) {
        this.tasksRepository = tasksRepository;
        this.tasksView = tasksView;

        tasksView.setPresenter(this);
    }

    @Override
    public void addTaskClicked() {
        tasksView.showAddTask();
    }

    @Override
    public void editTaskClicked(Task task) {
        tasksView.showEditTask(task);
    }

    @Override
    public void deleteTaskClicked(Task task) {
        tasksView.showDeleteTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        tasksRepository.deleteTask(task);
        refreshTasks(false);
    }

    @Override
    public void alterTaskState(Task task) {
        tasksRepository.alterTaskState(task);
        refreshTasks(false);
    }

    @Override
    public void refreshTasks(boolean forceUpdate) {
        tasksView.showProgressBar(true);

        if (forceUpdate) {
            tasksRepository.invalidateData();
        }

        tasksRepository.loadTasks(new ITasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                tasksView.showProgressBar(false);
                processTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                tasksView.showLoadingTasksError();
            }
        });
    }

    private void processTasks(List<Task> tasks) {
        // Show the list of tasks
        tasksView.showTasks(tasks);
    }

    @Override
    public void start() {

    }
}
