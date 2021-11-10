package com.example.assignment_2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;
public class Product_List_Adapter extends BaseAdapter{

        Context context;

        List<Product> productList;
        LayoutInflater inflter;

        public Product_List_Adapter(Context applicationContext, List<Product> pl ) {
            this.context = context;
            this.productList = pl;

            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.list_item, null);
            TextView name = (TextView)view.findViewById(R.id.Item_Name);
            TextView price = (TextView)view.findViewById(R.id.Item_Price);
            TextView quentity = (TextView)view.findViewById(R.id.Item_Quentity);

            name.setText(productList.get(i).getProduct_Name());
            price.setText(String.valueOf( productList.get(i).getProduct_Price()));
            quentity.setText(String.valueOf( productList.get(i).getProduct_quentity()));

            return view;
        }


}
