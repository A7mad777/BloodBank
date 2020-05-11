package com.example.future.smarthome.View;


import com.example.future.smarthome.data.ListOofCities.Datum;

import java.util.List;

public interface CitiesView {
    void setAdapterCity(List<String>strings, List<Integer>integerList);
    void setCitySpinner(List<Integer>integerList);
}
