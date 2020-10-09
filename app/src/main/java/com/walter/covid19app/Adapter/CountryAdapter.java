package com.walter.covid19app.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.walter.covid19app.Model.Countries;
import com.walter.covid19app.R;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> implements Filterable {

    Context context;
    List<Countries> countryList;
    List<Countries> countryFilter;
    Dialog dialog;

    public CountryAdapter(Context context, List<Countries> countryList) {
        this.context = context;
        this.countryList = countryList;
        this.countryFilter = new ArrayList<>(countryList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_country_details);
        dialog.setCanceledOnTouchOutside(true);

        vh.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView active, deaths, recovered, test, updated;
                active = dialog.findViewById(R.id.dActiveTv);
                deaths = dialog.findViewById(R.id.dDeathsTv);
                recovered = dialog.findViewById(R.id.dRecoveredTv);
                test = dialog.findViewById(R.id.dTestTv);
                updated = dialog.findViewById(R.id.updatedTv);

                active.setText(String.valueOf(countryList.get(vh.getAdapterPosition()).getActive()));
                deaths.setText(String.valueOf(countryList.get(vh.getAdapterPosition()).getDeaths()));
                recovered.setText(String.valueOf(countryList.get(vh.getAdapterPosition()).getRecovered()));
                test.setText(String.valueOf(countryList.get(vh.getAdapterPosition()).getTest()));

                // Convert Date
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(Long.parseLong(countryList.get(vh.getAdapterPosition()).getUpdated()));
                String currentDate = DateFormat.format("dd/MM/yyyy hh:mm aa", cal).toString();

                updated.setText("Date: " + currentDate);
                dialog.show();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Countries countries = countryList.get(position);
        holder.countryName.setText(countries.getCountry());
        holder.ncases.setText(String.valueOf(countries.getCases()));
        holder.nrecovered.setText(String.valueOf(countries.getRecovered()));
        String imageURL = countries.getCountryInfo().getFlag();

        Picasso.get().load(imageURL).into(holder.countryFlag);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence text) {
            List<Countries> filterList = new ArrayList<>();
            if (text.toString().isEmpty()) {
                filterList.addAll(countryFilter);
            } else {
                for (Countries c : countryFilter)
                    if (c.getCountry().toLowerCase().contains(text.toString().toLowerCase()))
                        filterList.add(c);
            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence text, FilterResults filterResults) {
            countryList.clear();
            countryList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        CircularImageView countryFlag;
        TextView countryName, ncases, ndeaths, nrecovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.idLinear);
            countryName = itemView.findViewById(R.id.tvCountry);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            ncases = itemView.findViewById(R.id.tvCases);
            nrecovered = itemView.findViewById(R.id.tvRecovered);
        }
    }
}