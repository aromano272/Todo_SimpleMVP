package com.example.aromano.mvpsimple.addtask;

import android.support.annotation.NonNull;

import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;

import java.lang.ref.WeakReference;

/**
 * Created by aRomano on 21/09/2016.
 */

public class AddEditTaskPresenter implements IAddEditTaskPresenter {

    private Task task;

    private final ITasksDataSource tasksRepository;

    private final WeakReference<IAddEditTaskView> addTaskView;

    public AddEditTaskPresenter(Task task, @NonNull ITasksDataSource tasksRepository, @NonNull IAddEditTaskView addTaskView) {
        this.tasksRepository = tasksRepository;
        this.addTaskView = new WeakReference<>(addTaskView);

        addTaskView.setPresenter(this);

        if ((this.task = task) != null) {
            addTaskView.populateData(task);
        }
    }

    @Override
    public void confirmClicked() {
        Task newTask = sanitizeInput();

        if (newTask == null) {
            return;
        }
        // if its an edit
        if (task != null) {
            tasksRepository.deleteTask(task);
        }
        tasksRepository.saveTask(newTask);
        addTaskView.get().returnToTasksList();
    }

    private Task sanitizeInput() {
        String title = addTaskView.get().getTaskTitle();
        String description = addTaskView.get().getTaskDescription();

        if (title.isEmpty()) {
            addTaskView.get().showMissingTitleError();
            return null;
        }

        return new Task(title, description);
    }

    @Override
    public void start() {

    }
}
