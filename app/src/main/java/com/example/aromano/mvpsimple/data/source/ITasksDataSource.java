package com.example.aromano.mvpsimple.data.source;

import com.example.aromano.mvpsimple.data.Task;

import java.util.List;

/**
 * Created by aRomano on 20/09/2016.
 */

public interface ITasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();

    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();

    }

    void loadTasks(LoadTasksCallback callback);

    void getTask(String taskId, GetTaskCallback callback);

    void saveTask(Task task);

    void deleteTask(Task task);

    void alterTaskState(Task task);

    void invalidateData();

}
