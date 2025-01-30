package com.example.todoapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddTodoActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private EditText nameEditText, descriptionEditText;
    private Button dateButton;

    private int selectedDay = -1, selectedMonth = -1, selectedYear = -1;

    private void UISetup() {
        nameEditText = findViewById(R.id.AddTODONameEditText);
        descriptionEditText = findViewById(R.id.AddTODODescriptionEditText);

        dateButton = findViewById(R.id.AddTODODateButton);
    }

    private void setDate(int day, int month, int year) {
        if(isValidFutureDate(day, month, year)) {
            dateButton.setText(day + "/" + month + "/" + year);
            selectedDay = day;
            selectedMonth = month;
            selectedYear = year;
        }else {
            dateButton.setText("Untimed");
            selectedDay = -1;
            selectedMonth = -1;
            selectedYear = -1;
        }
    }

    private boolean isLeapYear(int year) {
        // A leap year is defined as a year that is either divisible by 4 and not by 100 or divisible by 400
        if(year % 400 == 0) {
            return true;
        }
        if(year % 4 == 0 && year % 100 != 0) {
            return true;
        }

        return false;
    }

    private boolean isValidFutureDate(int day, int month, int year) {
        // Get the current date
        int currentDay = getCurrentDay();
        int currentMonth = getCurrentMonth();
        int currentYear = getCurrentYear();

        // Validate that the date is in the future
        if(year < currentYear) {
            return false;
        } else if(year == currentYear) {
            if(month < currentMonth) {
                return false;
            } else if(month == currentMonth) {
                if(day < currentDay) {
                    return false;
                }
            }
        }

        // Validate months
        if(month < 1 || month > 12) {
            return false;
        }

        if(day < 1) {
            return false;
        }

        // Checking the months from Jan to Jul since the 30/31 day rule flips afterwards
        if(month <= 7) {
            // February depends on whether the year is a leap year
            if(month == 2) {
                if(isLeapYear(year)) {
                    if(day > 29) {
                        return false;
                    }
                } else {
                    if(day > 28) {
                        return false;
                    }
                }
            } else {
                if(month % 2 == 1) {
                    if(day > 31) {
                        return false;
                    }
                }else if(day > 30) {
                    return false;
                }
            }
        } else {
            if(month % 2 == 0) {
                if(day > 31) {
                    return false;
                }
            }else if(day > 30) {
                return false;
            }
        }

        return true;
    }

    private int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1; // Java implements the month from 0 to 11
    }

    private int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_todo);
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

    }

    public void onCreateButtonClick(View createButton) {
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // TODO: Transition to the main activity sending the needed data for the new TODO
    }
}