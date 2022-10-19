package com.example.plantaid_application.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("o")
    @Expose
    private String o;
    @SerializedName("m")
    @Expose
    private String m;
    @SerializedName("s")
    @Expose
    private String s;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
