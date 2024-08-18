package com.example.shipcampusreservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookReservationActivity extends AppCompatActivity {

    private Button btnConfirmBooking, btnBackToEvents;
    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        btnConfirmBooking = findViewById(R.id.btn_confirm_booking);
        btnBackToEvents = findViewById(R.id.btn_back_to_events_from_booking);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentCalendar = Calendar.getInstance();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, day, hour, minute, 0);

                // check dateTime
                if (selectedCalendar.before(currentCalendar)) {
                    Toast.makeText(BookReservationActivity.this, "Cannot select a past date or time.", Toast.LENGTH_LONG).show();
                } else {

                    String date = day + "/" + (month + 1) + "/" + year;
                    String time = hour + ":" + String.format("%02d", minute);

                    // save into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("Reservations", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("reservation_date", date);
                    editor.putString("reservation_time", time);
                    editor.apply();

                    // confirm booking
                    Toast.makeText(BookReservationActivity.this, "Booking Confirmed for " + date + " at " + time, Toast.LENGTH_LONG).show();

                    // back to event
                    startActivity(new Intent(BookReservationActivity.this, EventsActivity.class));
                    finish();
                }
            }
        });

        // back to event
        btnBackToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BookReservationActivity.this, EventsActivity.class));
                finish();
            }
        });
    }
}