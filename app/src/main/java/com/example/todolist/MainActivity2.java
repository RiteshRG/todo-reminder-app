package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todolist.databinding.ActivityMain2Binding;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;

    private String selectedDate;
    private String selectedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Objects.requireNonNull(getSupportActionBar()).hide();

        final Calendar calendar =Calendar.getInstance();

        binding.dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get((Calendar.DAY_OF_MONTH));

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this,(dateView, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;

                    binding.dateBtn.setText(selectedDate);
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        binding.timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity2.this, (timeView, selectedHour, selectedMinute) -> {
                    selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                    binding.timeBtn.setText(selectedTime);
                },hour,minute, true);
                timePickerDialog.show();
            }
        });

        binding.viewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.AddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("title",binding.title.getText().toString());
                intent.putExtra("description",binding.description.getText().toString());
                intent.putExtra("date",binding.dateBtn.getText().toString());
                intent.putExtra("time",binding.timeBtn.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}