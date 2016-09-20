package com.example.aromano.mvpsimple.main;

import com.example.aromano.mvpsimple.BasePresenter;
import com.example.aromano.mvpsimple.data.Task;

/**
 * Created by aRomano on 20/09/2016.
 */

public interface ITasksPresenter extends BasePresenter {

    void addTaskClicked();

    void editTaskClicked(Task task);

    void deleteTaskClicked(Task task);

    void alterTaskState(Task task);

    void deleteTask(Task task);

    void refreshTasks(boolean forceUpdate);

}
