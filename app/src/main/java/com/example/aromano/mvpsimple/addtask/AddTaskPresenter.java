package com.example.aromano.mvpsimple.addtask;

import android.support.annotation.NonNull;

import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;
import com.example.aromano.mvpsimple.main.ITasksView;

/**
 * Created by aRomano on 21/09/2016.
 */

public class AddTaskPresenter implements IAddTaskPresenter {

    private final ITasksDataSource tasksRepository;

    private final IAddTaskView addTaskView;

    public AddTaskPresenter(@NonNull ITasksDataSource tasksRepository, @NonNull IAddTaskView addTaskView) {
        this.tasksRepository = tasksRepository;
        this.addTaskView = addTaskView;

        addTaskView.setPresenter(this);
    }

    @Override
    public void confirmClicked() {
        String title = addTaskView.getTaskTitle();
        String description = addTaskView.getTaskDescription();

        if (title.isEmpty()) {
            addTaskView.showMissingTitleError();
            return;
        }

        Task task = new Task(title, description);
        tasksRepository.saveTask(task);
    }

    @Override
    public void start() {

    }
}
