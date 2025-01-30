package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddTodoActivity extends AppCompatActivity {
    private EditText nameEditText, descriptionEditText;
    private Button dateButton;

    private void UISetup() {
        nameEditText = findViewById(R.id.AddTODONameEditText);
        descriptionEditText = findViewById(R.id.AddTODODescriptionEditText);

        dateButton = findViewById(R.id.AddTODODateButton);
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
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

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

        Intent intent = getIntent();
        if(intent != null) {
            int day = intent.getIntExtra("day", -1);
            int month = intent.getIntExtra("month", -1);
            int year = intent.getIntExtra("year", -1);

            if(isValidFutureDate(day, month, year)) {
                dateButton.setText(day + "/" + month + "/" + year);
            }else {
                dateButton.setText("Untimed");
            }
        }
    }

    public void onDateButtonClick(View dateButton) {

    }

    public void onTimeButtonClick(View timeButton) {

    }

    public void onCreateButtonClick(View createButton) {

    }
}