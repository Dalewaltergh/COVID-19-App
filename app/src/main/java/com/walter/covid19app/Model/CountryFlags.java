package com.walter.covid19app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryFlags {

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getFlag()  {
        return flag;
    }
}
