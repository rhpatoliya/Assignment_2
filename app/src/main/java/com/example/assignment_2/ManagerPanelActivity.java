package com.example.assignment_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerPanelActivity extends AppCompatActivity {

    ArrayList<HistoryProduct> listOfHistory ;
    ArrayList<Product> stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);
        Button history = (Button) findViewById(R.id.historyID);

        if ((this.getIntent().hasExtra("historyList"))){
            listOfHistory = this.getIntent().getExtras().getParcelableArrayList("historyList");
            stock = this.getIntent().getExtras().getParcelableArrayList("stock");
        }
//        final ActivityResultLauncher<Intent> restockActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK )  {
//                            // There are no request codes
//                            Intent data = result.getData();
//                            Bundle extra = data.getExtras();
//                            stock =  extra.getParcelableArrayList("stock");
//
//                            Intent returnIntent = new Intent();
//                            returnIntent.putParcelableArrayListExtra("stock",stock);
//                            setResult(Activity.RESULT_OK,returnIntent);
//
//
//                        }
//                    }
//                });



       // listOfHistory  = intent.getParcelableArrayListExtra("historyList");
       // listOfHistory = (ArrayList<Parcelable>) Arrays.asList(list);
       //listOfHistory = (HistoryProduct[])list;
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHistory = new Intent(getApplicationContext(),HistoryListActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("historyList", listOfHistory);
//                toHistory.putExtras(bundle);
                startActivity(toHistory);
            }
        });
        Button restock = (Button) findViewById(R.id.restockID);
        restock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRestock = new Intent(getApplicationContext(),RestockActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("stock",stock);
//                toRestock.putExtras(bundle);
                startActivity(toRestock);
               // restockActivityResultLauncher.launch(toRestock);


            }
        });
   }

}