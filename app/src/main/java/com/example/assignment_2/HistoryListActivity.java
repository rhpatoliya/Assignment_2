package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HistoryProduct> listOfHistory;
    TextView hintMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        recyclerView = (RecyclerView) findViewById(R.id.history_list);
        hintMsg = (TextView) findViewById(R.id.nohistoryText);

        if ((this.getIntent().hasExtra("historyList"))) {
            listOfHistory = this.getIntent().getExtras().getParcelableArrayList("historyList");
        }
        if (listOfHistory.size() == 0) {
            hintMsg.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CustomHistoryAdapter(this, listOfHistory,
                    new CustomHistoryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(HistoryProduct item) {
                            Intent todetail = new Intent(getApplicationContext(), HistoryDetailActivity.class);
                            todetail.putExtra("detailedProduct", item);
                            startActivity(todetail);
                        }
                    }
            ));
            // recyclerView.setOnClickListener(new AdapterView.OnItemClickListener());
        }
    }
}