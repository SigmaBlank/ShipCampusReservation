package com.example.shipcampusreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ManageBookingActivity extends AppCompatActivity {

    private TextView tvBookingDetails;
    private Button btnEditBooking, btnCancelBooking, btnBackToEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_booking);

        tvBookingDetails = findViewById(R.id.tv_booking_details);
        btnEditBooking = findViewById(R.id.btn_edit_booking);
        btnCancelBooking = findViewById(R.id.btn_cancel_booking);
        btnBackToEvents = findViewById(R.id.btn_back_to_events_from_manage);

        // get booking from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Reservations", MODE_PRIVATE);
        String reservationDate = sharedPreferences.getString("reservation_date", "");
        String reservationTime = sharedPreferences.getString("reservation_time", "");

        // show booking detail
        if (!reservationDate.isEmpty() && !reservationTime.isEmpty()) {
            String bookingDetails = "You have a reservation on " + reservationDate + " at " + reservationTime + ".\n\n" +
                    "Details:\n" +
                    "Date: " + reservationDate + "\n" +
                    "Time: " + reservationTime + "\n\n" +
                    "Feel free to edit or cancel your booking below.";
            tvBookingDetails.setText(bookingDetails);
        } else {
            tvBookingDetails.setText("No booking found");
        }

        btnEditBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // manage booking
                startActivity(new Intent(ManageBookingActivity.this, BookReservationActivity.class));
                finish();
            }
        });

        btnCancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pop up box
                new AlertDialog.Builder(ManageBookingActivity.this)
                        .setTitle("Cancel Booking")
                        .setMessage("Are you sure you want to cancel your booking?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // cancel booking
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("reservation_date");
                                editor.remove("reservation_time");
                                editor.apply();

                                Toast.makeText(ManageBookingActivity.this, "Booking Canceled", Toast.LENGTH_SHORT).show();
                                tvBookingDetails.setText("No booking found");
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        btnBackToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to event
                startActivity(new Intent(ManageBookingActivity.this, EventsActivity.class));
                finish();
            }
        });
    }
}