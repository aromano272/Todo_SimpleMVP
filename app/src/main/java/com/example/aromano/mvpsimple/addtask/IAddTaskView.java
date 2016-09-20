package com.example.aromano.mvpsimple.addtask;

import com.example.aromano.mvpsimple.BaseView;
import com.example.aromano.mvpsimple.data.Task;
import com.example.aromano.mvpsimple.main.ITasksPresenter;

import java.util.List;

/**
 * Created by aRomano on 20/09/2016.
 */

public interface IAddTaskView extends BaseView<IAddTaskPresenter> {

    String getTaskTitle();

    String getTaskDescription();

    void showMissingTitleError();

}
