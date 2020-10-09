package com.walter.covid19app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("updated")
    @Expose
    private String updated;

    @SerializedName("tests")
    @Expose
    private int test;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("cases")
    @Expose
    private int cases;

    @SerializedName("deaths")
    @Expose
    private int deaths;

    @SerializedName("recovered")
    @Expose
    private int recovered;

    @SerializedName("countryInfo")
    @Expose
    private CountryFlags countryInfo;

    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getTest() {
        return test;
    }

    public String getUpdated() {
        return updated;
    }

    public CountryFlags getCountryInfo() {
        return countryInfo;
    }
}
