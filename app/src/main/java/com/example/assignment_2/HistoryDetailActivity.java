package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
       HistoryProduct h =  getIntent().getParcelableExtra("detailedProduct");
       String details = "Product: " + h.product_Name +"\n"+ "Price: " + h.product_Price + "\n" + "Purshase Date: " + h.purshaseDate;
        TextView d = (TextView) findViewById(R.id.detail_text);
        d.setText(details);
    }
}