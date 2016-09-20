package com.example.aromano.mvpsimple.data.source.remote;

import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aRomano on 20/09/2016.
 */
public class TasksRemoteDataSource implements ITasksDataSource {

    // todo implement proper remote with firebase or smt
    private Map<String, Task> cache;

    public TasksRemoteDataSource() {
        cache = new HashMap<>();
    }

    @Override
    public void loadTasks(LoadTasksCallback callback) {
        callback.onTasksLoaded(new ArrayList<>(cache.values()));
    }

    @Override
    public void getTask(String taskId, GetTaskCallback callback) {
        if (cache.get(taskId) != null) {
            callback.onTaskLoaded(cache.get(taskId));
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void alterTaskState(Task task) {

    }

    @Override
    public void invalidateData() {

    }
}
