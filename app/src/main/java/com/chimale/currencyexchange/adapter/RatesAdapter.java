package com.chimale.currencyexchange.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chimale.currencyexchange.R;
import com.chimale.currencyexchange.activity.ConversionActivity;
import com.chimale.currencyexchange.model.Currencies;
import com.chimale.currencyexchange.model.RateParameters;
import com.squareup.picasso.Picasso;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Makindu ExpressC on 29/07/2017.
 */



public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ViewHolder> {
    private List<RateParameters> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView currencyCode, currencyName, btcTextValue, ethTextValue;
        public CircleImageView flagImageView;
        private Double btcValue, ethValue;

        public ViewHolder(View v) {
            super(v);

            currencyCode = (TextView)v.findViewById(R.id.currency_code);
            currencyName = (TextView)v.findViewById(R.id.currency_name);
            btcTextValue = (TextView)v.findViewById(R.id.btc_value);
            ethTextValue = (TextView)v.findViewById(R.id.eth_value);
            flagImageView = (CircleImageView)v.findViewById(R.id.rate_item_flag_image);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public RatesAdapter(Context c, List<RateParameters> myDataset) {
        mDataset = myDataset;
        context=c;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(rowView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conversionIntent = new Intent(parent.getContext(), ConversionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("btc_rate", mDataset.get(viewHolder.getAdapterPosition()).btc_value);
                bundle.putDouble("eth_rate", mDataset.get(viewHolder.getAdapterPosition()).eth_value);
                bundle.putInt("index" ,viewHolder.getAdapterPosition());
//                conversionIntent.putExtra("btc_rate", viewHolder.btcValue.getText().toString());
//                conversionIntent.putExtra("eth_rate", viewHolder.ethValue.getText().toString());
//                conversionIntent.putExtra("index", String.valueOf(viewHolder.getAdapterPosition()));
                conversionIntent.putExtras(bundle);

                parent.getContext().startActivity(conversionIntent);

            }
        });

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.currencyName.setText(mDataset.get(position).currency_name);
        String code = (Currency.getInstance(mDataset.get(position).currency_code).getSymbol(new Locale("en", "GB"))+" "+(mDataset.get(position).currency_code));
        holder.currencyCode.setText(code);
        String btc = ("Ƀ ")+(String.valueOf(mDataset.get(position).btc_value));
        holder.btcTextValue.setText(btc);
        String eth = ("Ξ ")+(String.valueOf(mDataset.get(position).eth_value));
        holder.ethTextValue.setText(eth);
        Picasso.with(holder.itemView.getContext()).load(Currencies.getImageResourceId(position)).into(holder.flagImageView);
    }

    public void updateData(List<RateParameters> r){
        mDataset=r;
        this.notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}