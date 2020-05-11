package com.example.future.smarthome.View;

import com.example.future.smarthome.data.ListOfGovernorates.Governors;

import java.util.List;

public interface ListOfGovernorsView {
    void setSpinner(List<Integer>integerList);
    void setAdapter(List<String>stringList,List<Integer>integerList);
    void onFailure(Throwable t);
}
