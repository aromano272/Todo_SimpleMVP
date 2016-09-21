package com.example.aromano.mvpsimple.addtask;

import com.example.aromano.mvpsimple.BaseView;
import com.example.aromano.mvpsimple.data.Task;

/**
 * Created by aRomano on 20/09/2016.
 */

public interface IAddEditTaskView extends BaseView<IAddEditTaskPresenter> {

    String getTaskTitle();

    String getTaskDescription();

    void showMissingTitleError();

    void returnToTasksList();

    void populateData(Task task);

}
