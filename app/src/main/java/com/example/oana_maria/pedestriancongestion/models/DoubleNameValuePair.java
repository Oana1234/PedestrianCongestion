package com.example.oana_maria.pedestriancongestion.models;

import org.apache.http.NameValuePair;

/**
 * Created by Oana-Maria on 10/01/2018.
 */

public class DoubleNameValuePair implements NameValuePair {
    String name;
    Double value;

    public DoubleNameValuePair(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return Double.toString(value);
    }
}
