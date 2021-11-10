package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {
    Product_List_Adapter customAdapter;
    static ArrayList<Product> stock;
    ListView simpleList;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        stock = this.getIntent().getExtras().getParcelableArrayList("stock");
        customAdapter = new Product_List_Adapter(getApplicationContext(), stock);
        simpleList = (ListView) findViewById(R.id.list);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;
            }
        });
        EditText newQ = (EditText) findViewById(R.id.newQ);

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = newQ.getText().toString();
                if (selectedIndex != -1 && !newValue.isEmpty()){
                    stock.get(selectedIndex).setProduct_quentity( stock.get(selectedIndex).getProduct_quentity() + Integer.parseInt(newValue));
                    customAdapter.notifyDataSetChanged();

                    Intent returnIntent = new Intent();
                    returnIntent.putParcelableArrayListExtra("stock",stock);
                    setResult(Activity.RESULT_OK,returnIntent);
                    //finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields are REQUIERED",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}