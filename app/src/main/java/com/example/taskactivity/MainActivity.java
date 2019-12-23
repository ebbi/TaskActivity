package com.example.taskactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskactivity.data.Task;
import com.example.taskactivity.data.TaskRepository;

public class MainActivity extends AppCompatActivity {

    private Task mTask = new Task(0, "title", "description", "status");
    private TaskRepository sTaskRepository = TaskRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTask = sTaskRepository.getFirstTask();
        updateUI();

        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ( sTaskRepository.isLast(mTask)) {

                    Toast toast = Toast.makeText(
                            getApplicationContext(),
                            "Hurray! end of tasks, time to plant trees and eat marmite!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {
                    mTask = sTaskRepository.getNextTask(mTask);
                    updateUI();
                }
            }
        });

    }

    private void updateUI() {

        TextView textViewTaskTitle = (TextView) findViewById(R.id.textViewTaskTitle);
        textViewTaskTitle.setText(mTask.getTitle());

        TextView textViewTaskDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewTaskDescription.setText(mTask.getDescription());

        TextView textViewTaskStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewTaskStatus.setText(mTask.getStatus());
    }

}
