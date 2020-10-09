package com.walter.covid19app.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.walter.covid19app.API.APIClient;
import com.walter.covid19app.Model.Global;
import com.walter.covid19app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    PieChart chart;
    private ProgressBar progressBar;
    ArrayList<Integer> values = new ArrayList<>(); // Stores the values of cases, deaths and recovered

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        progressBar = root.findViewById(R.id.pbHome);

        // pie chart graph
        chart = root.findViewById(R.id.pieChart);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setCenterText("PIE CHART");
        chart.setCenterTextSize(25f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        // activate rotation
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend leg = chart.getLegend();
        leg.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        leg.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        leg.setOrientation(Legend.LegendOrientation.VERTICAL);
        leg.setDrawInside(false);
        leg.setXEntrySpace(7f);
        leg.setYEntrySpace(0f);
        leg.setYOffset(0f);

        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(16f);

        getData();
        return root;
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Global> call = APIClient.getInstance().getAPI().getInfoGlobal();
        call.enqueue(new Callback<Global>() {
            @Override
            public void onResponse(Call<Global> call, Response<Global> response) {
                Global data = response.body();
                values.add(Integer.parseInt(data.getRecovered()));
                values.add(Integer.parseInt(data.getCases()));
                values.add(Integer.parseInt(data.getDeaths()));

                sendData();
            }

            @Override
            public void onFailure(Call<Global> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void sendData() {
        ArrayList<PieEntry> chartData = new ArrayList<>();
        final String[] labels = new String[]{"Recovered", "Cases", "Death"};

        for (int i = 0; i < labels.length; i++)
            chartData.add(new PieEntry(values.get(i), labels[i]));

        PieDataSet pieDataSet = new PieDataSet(chartData, "");
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c: ColorTemplate.MATERIAL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.highlightValue(null);
        chart.invalidate();
    }
}