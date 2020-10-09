package com.walter.covid19app.ui.countries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.covid19app.API.APIClient;
import com.walter.covid19app.Adapter.CountryAdapter;
import com.walter.covid19app.Model.Countries;
import com.walter.covid19app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListFragment extends Fragment {

    RecyclerView recyclerView;
    CountryAdapter countryAdapter;
    List<Countries> countryList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_country_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        countryData();
        return root;
    }

    private void countryData() {
        Call<List<Countries>> call;
        call = APIClient.getInstance().getAPI().getCountries("cases");
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    if (!countryList.isEmpty())
                        countryList.clear();

                    countryList = response.body();
                    countryAdapter = new CountryAdapter(getActivity(), countryList);
                    recyclerView.setAdapter(countryAdapter);
                    countryAdapter.notifyDataSetChanged();
                }
                else Toast.makeText(getActivity(), "No results", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) { }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setQueryHint("search for...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                countryAdapter.getFilter().filter(s);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);
    }
}