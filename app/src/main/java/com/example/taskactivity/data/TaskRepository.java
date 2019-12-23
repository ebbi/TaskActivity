package com.example.taskactivity.data;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TaskRepository {

    private static TaskRepository sTodoRepository;

    private ArrayList<Task> mTasks = new ArrayList<>();
    private Task mTask;

    public static TaskRepository getInstance() {
        if (sTodoRepository == null) {
            sTodoRepository = new TaskRepository();
        }
        return sTodoRepository;
    }

    private TaskRepository(){
        initTestData();
    }

    private void initTestData() {

        for (int i=0; i < 3; i++){
            Task task = new Task(i,"Test title " + i,"Test description " + i,
                    "To be set!");
            mTasks.add(task);
        }
    }

    public Task getNextTask(Task task) {

        int currentTaskId = task.getId();
        int nextTaskId = currentTaskId;

        if (currentTaskId < mTasks.size() - 1){
            nextTaskId += 1;
        } else  if (currentTaskId > mTasks.size() - 1){
            nextTaskId = 0;
        } else {
            Log.d(TAG, "getNextTask: currentTaskId out of bound!");
            nextTaskId = 0;
        }

        return mTasks.get(nextTaskId);

    }

    public boolean isLast(Task mTask) {
        return mTask.getId() == mTasks.size() - 1;
    }

    public Task getFirstTask() {
        return mTasks.get(0);
    }
}
