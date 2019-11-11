package com.social.troops.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
