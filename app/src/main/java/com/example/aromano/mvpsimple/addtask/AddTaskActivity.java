package com.example.aromano.mvpsimple.addtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.aromano.mvpsimple.R;
import com.example.aromano.mvpsimple.data.source.ITasksDataSource;
import com.example.aromano.mvpsimple.data.source.TasksRepository;
import com.example.aromano.mvpsimple.data.source.local.TasksLocalDataSource;
import com.example.aromano.mvpsimple.data.source.remote.TasksRemoteDataSource;

public class AddTaskActivity extends AppCompatActivity implements IAddTaskView {
    private IAddTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initializeView();

        ITasksDataSource tasksRepository = TasksRepository.getInstance(new TasksRemoteDataSource(), TasksLocalDataSource.getInstance(getApplicationContext()));

        new AddTaskPresenter(tasksRepository, this);
    }

    private void initializeView() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findViewById(R.id.fab_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.confirmClicked();
            }
        });
    }

    @Override
    public String getTaskTitle() {
        return ((EditText) findViewById(R.id.et_title)).getText().toString();
    }

    @Override
    public String getTaskDescription() {
        return ((EditText) findViewById(R.id.et_description)).getText().toString();
    }

    @Override
    public void showMissingTitleError() {
        ((EditText) findViewById(R.id.et_title)).setError("Field is required");
    }

    @Override
    public void setPresenter(IAddTaskPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
