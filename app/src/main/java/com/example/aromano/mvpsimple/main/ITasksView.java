package com.example.aromano.mvpsimple.main;

import com.example.aromano.mvpsimple.BaseView;
import com.example.aromano.mvpsimple.data.Task;

import java.util.List;

/**
 * Created by aRomano on 20/09/2016.
 */

public interface ITasksView extends BaseView<ITasksPresenter> {

    void showProgressBar(boolean show);

    void showTasks(List<Task> tasks);

    void showAddTask();

    void showEditTask(Task task);

    void showDeleteTask(Task task);

    void showLoadingTasksError();

}
