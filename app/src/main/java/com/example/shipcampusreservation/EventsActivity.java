package com.example.shipcampusreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EventsActivity extends AppCompatActivity {

    private Button btnBookEvent1, btnBookEvent2, btnManageBooking, btnBackToLogin;
    private TextView tvEvent1Title, tvEvent1Description, tvEvent2Title, tvEvent2Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        btnBookEvent1 = findViewById(R.id.btn_book_event1);
        btnBookEvent2 = findViewById(R.id.btn_book_event2);
        btnManageBooking = findViewById(R.id.btn_manage_booking);
        btnBackToLogin = findViewById(R.id.btn_back_to_login);

        tvEvent1Title = findViewById(R.id.tv_event1_title);
        tvEvent1Description = findViewById(R.id.tv_event1_description);
        tvEvent2Title = findViewById(R.id.tv_event2_title);
        tvEvent2Description = findViewById(R.id.tv_event2_description);

        // event detail
        tvEvent1Title.setText("Ship Campus Tour\nDate: August 20, 2024\nTime: 10:00 AM - 12:00 PM");
        tvEvent1Description.setText("Explore the fascinating Ship Campus with guided tours. Learn about the campus's history and enjoy interactive exhibits.");

        tvEvent2Title.setText("Ship Technology Workshop\nDate: August 22, 2024\nTime: 2:00 PM - 4:00 PM");
        tvEvent2Description.setText("Join our workshop on the latest advancements in ship technology. Hands-on sessions and expert talks await you.");

        btnBookEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // book reservation
                startActivity(new Intent(EventsActivity.this, BookReservationActivity.class));
            }
        });

        btnBookEvent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // book reservation
                startActivity(new Intent(EventsActivity.this, BookReservationActivity.class));
            }
        });

        btnManageBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // manage reservation
                startActivity(new Intent(EventsActivity.this, ManageBookingActivity.class));
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logout
                startActivity(new Intent(EventsActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}