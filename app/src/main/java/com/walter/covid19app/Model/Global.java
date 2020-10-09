package com.walter.covid19app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Global {
    @SerializedName("cases")
    @Expose
    private String cases;

    @SerializedName("todayCases")
    @Expose
    private String newCases;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    public String getCases() {
        return cases;
    }

    public String getNewCases() {
        return newCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }
}
