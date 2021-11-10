package com.example.assignment_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity  {

    static Store_Manager manager =  new Store_Manager();

     ArrayList<HistoryProduct> listOfHistory = new ArrayList<>(10);

    ListView simpleList;
    TextView product_name;
    TextView selected_Quentity;
    TextView total;
    int i = 0;
    Button managerBUT;
    int selectedItemIndex;
    AlertDialog.Builder builder;
    Product_List_Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (savedInstanceState == null || !savedInstanceState.containsKey("stock"))
//        {
//            manager  = new Store_Manager();
//        }
//        else
//            manager.productList = savedInstanceState.getParcelableArrayList("stock");

        setContentView(R.layout.activity_main);
        selected_Quentity = (TextView)findViewById(R.id.quentity);
        total = (TextView)findViewById(R.id.total);
        simpleList = (ListView) findViewById(R.id.list_view);
        customAdapter = new Product_List_Adapter(getApplicationContext(), manager.productList);
        simpleList.setAdapter(customAdapter);

        product_name = (TextView)findViewById(R.id.product_type);
        builder = new AlertDialog.Builder(this);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                product_name.setText( manager.productList.get(i).getProduct_Name());
                selectedItemIndex = i;
                update_total();
            }
        });


        final ActivityResultLauncher<Intent> restockActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK )  {
                            // There are no request codes
                            Intent data = result.getData();
                            Bundle extra = data.getExtras();
                            manager.productList =  extra.getParcelable("stock");
                            customAdapter.notifyDataSetChanged();
                        }
                    }
                });

        managerBUT = (Button) findViewById(R.id.manager);
        managerBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toManager = new Intent(getApplicationContext(),ManagerPanelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("historyList", listOfHistory);
                bundle.putParcelableArrayList("stock",manager.productList);
                toManager.putExtras(bundle);

               // toManager.put("historyList",listOfHistory);
                startActivity(toManager);
                restockActivityResultLauncher.launch(toManager);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        customAdapter.notifyDataSetChanged();


    }



        public void digit_clicked(View view){
            String v = ((Button)view).getText().toString();
            selected_Quentity.setText(selected_Quentity.getText()+v);
            update_total();
        }


    public void clear_clicked(View view){
        selected_Quentity.setText("");
        total.setText("");

    }

    public void update_total() {
        if ((!selected_Quentity.getText().toString().isEmpty()) && (!product_name.getText().toString().isEmpty())) {
            int q = Integer.parseInt(selected_Quentity.getText().toString());


            double unitPrice = manager.productList.get(selectedItemIndex).getProduct_Price();
            total.setText(String.valueOf(new DecimalFormat("##.##").format(q * unitPrice)));
        }
    }


    public void buy_clicked(View view) {
        if ((!selected_Quentity.getText().toString().isEmpty()) && (!product_name.getText().toString().isEmpty()) &&
                (selectedItemIndex >= 0)
        ) {
            int q = Integer.parseInt(selected_Quentity.getText().toString());
            if (!manager.checkAvailability(manager.productList.get(selectedItemIndex), q))
                Toast.makeText(this,"No enough quentity in the stock!!!",Toast.LENGTH_LONG).show();
            else {
                int newQ = manager.productList.get(selectedItemIndex).getProduct_quentity() - q;
                manager.productList.get(selectedItemIndex).setProduct_quentity(newQ);
                customAdapter.notifyDataSetChanged();
                String p = manager.productList.get(selectedItemIndex).getProduct_Name();
                Double t = Double.parseDouble(String.valueOf(total.getText()));
                HistoryProduct historyProduct = new HistoryProduct(p,t,q,(new Date().toString()));
                listOfHistory.add(historyProduct);

                builder.setTitle("Thank You for your purchase");
                builder.setMessage("Your purchase is "+ q  + " " + p + " for " + t);
                builder.show();
            }


        }else {
            Toast.makeText(this,"All fields are required!!!",Toast.LENGTH_LONG).show();
        }
    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("stock",manager.productList);
        super.onSaveInstanceState(outState);

    }
}

