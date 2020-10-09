package com.walter.covid19app.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.walter.covid19app.API.APIClient;
import com.walter.covid19app.Model.Global;
import com.walter.covid19app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    TextView cases, deaths, recovered, newCases;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        cases = root.findViewById(R.id.dashCases);
        deaths = root.findViewById(R.id.dashDeaths);
        recovered = root.findViewById(R.id.dashRecovered);
        newCases = root.findViewById(R.id.dashNewCases);

        getData();
        return root;
    }

    private void getData() {
        Call<Global> call = APIClient.getInstance().getAPI().getInfoGlobal();
        call.enqueue(new Callback<Global>() {
            @Override
            public void onResponse(Call<Global> call, Response<Global> response) {
                Global data = response.body();
                cases.setText(data.getCases());
                deaths.setText(data.getDeaths());
                recovered.setText(data.getRecovered());
                newCases.setText(data.getNewCases());
            }

            @Override
            public void onFailure(Call<Global> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}