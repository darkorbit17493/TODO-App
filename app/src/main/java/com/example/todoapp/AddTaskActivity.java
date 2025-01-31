package com.example.todoapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapp.model.utility.Formatter;
import com.example.todoapp.model.utility.TimeUtility;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private EditText nameEditText, descriptionEditText;
    private Button dateButton;
    private Button timeButton;

    private int selectedDay = -1, selectedMonth = -1, selectedYear = -1;
    private int selectedHour = -1, selectedMinute = -1;

    private void UISetup() {
        nameEditText = findViewById(R.id.AddTaskNameEditText);
        descriptionEditText = findViewById(R.id.AddTaskDescriptionEditText);

        dateButton = findViewById(R.id.AddTaskDateButton);
        timeButton = findViewById(R.id.AddTaskTimeButton);
    }

    private void setDate(int day, int month, int year) {
        if(TimeUtility.isNotPastDate(day, month, year)) {
            dateButton.setText(Formatter.getFormattedDate(day, month, year));
            selectedDay = day;
            selectedMonth = month;
            selectedYear = year;
        } else {
            clearDate();
        }
    }

    private void clearDate() {
        dateButton.setText(getResources().getString(R.string.untimed_indicator));
        selectedDay = -1;
        selectedMonth = -1;
        selectedYear = -1;
    }

    private void setTime(int hour, int minute) {
        if(TimeUtility.isFutureTime(hour, minute, selectedDay, selectedMonth, selectedYear)) {
            timeButton.setText(Formatter.getFormattedTime(hour, minute));
            selectedHour = hour;
            selectedMinute = minute;
        } else {
            clearTime();
        }
    }

    private void clearTime() {
        timeButton.setText(getResources().getString(R.string.untimed_indicator));
        selectedHour = -1;
        selectedMinute = -1;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /// EVENTS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UISetup();

        // Get the date passed by the previous activity that called this one (can be -1 for no date)
        Intent intent = getIntent();
        if(intent != null) {
            int day = intent.getIntExtra("day", -1);
            int month = intent.getIntExtra("month", -1);
            int year = intent.getIntExtra("year", -1);

            setDate(day, month, year);
        }
    }

    public void onDateButtonClick(View dateButton) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                // Java defines months from 0 to 11
                setDate(day, month + 1, year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    public void onTimeButtonClick(View timeButton) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                setTime(hour, minute);
            }
        };

        int defaultTargetHour = TimeUtility.getCurrentHour() + 1;
        if(defaultTargetHour > 23) {
            defaultTargetHour = 23;
        }
        int defaultTargetMinute = TimeUtility.getCurrentMinute();
        timePickerDialog = new TimePickerDialog(this, timeSetListener, defaultTargetHour, defaultTargetMinute,true);
        timePickerDialog.show();
    }

    public void onClearDateButtonClick(View clearButton) {
        clearDate();
        clearTime();
    }

    public void onClearTimeButtonClick(View clearButton) {
        clearTime();
    }

    public void onCreateButtonClick(View createButton) {
        // TODO: Save a Task locally in a file in JSON format
        goToMainActivity();
    }

    public void onBackButtonClick(View backButton) {
        goToMainActivity();
    }
}