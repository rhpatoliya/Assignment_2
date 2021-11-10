package com.example.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomHistoryAdapter
        extends RecyclerView.Adapter<CustomHistoryAdapter.ViewHolder>
        {

            public interface OnItemClickListener {
                void onItemClick(HistoryProduct item);
            }

            private final OnItemClickListener listener;
            private ArrayList<HistoryProduct> localDataSet;
            private LayoutInflater mInflater;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView product_textView;
        private final TextView quentity_textView;
        private final TextView price_textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            product_textView = (TextView) view.findViewById(R.id.product);
            quentity_textView = (TextView) view.findViewById(R.id.quentity);
            price_textView = (TextView) view.findViewById(R.id.price);

        }

        public TextView get_productTextView() {

            return product_textView;
        }
        public TextView get_priceTextView() {
            return price_textView;
        }
        public TextView get_quentityTextView() {
            return quentity_textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomHistoryAdapter(Context context, ArrayList<HistoryProduct> dataSet, OnItemClickListener listener) {
        localDataSet = dataSet;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
//        View view = LayoutInflater.from(viewGroup.getContext())
          //     .. .inflate(R.layout.history_list_row, viewGroup, false);
        View view = mInflater.inflate(R.layout.history_list_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CustomHistoryAdapter.ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.get_productTextView().setText(localDataSet.get(position).product_Name);
        String p = String.valueOf(localDataSet.get(position).product_Price);
        viewHolder.get_priceTextView().setText(p);
        String q = String.valueOf(localDataSet.get(position).product_quentity);
        viewHolder.get_quentityTextView().setText(q);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(localDataSet.get(position));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}