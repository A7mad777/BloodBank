package com.example.future.smarthome.View;

import com.example.future.smarthome.data.ListOfBlood.Bloods;

import java.util.List;

public interface BloodTypeView {
    void setBloodAdapter(List<Bloods>bloodsList,List<String>stringList,List<Integer>integerList);
    void setBloodSpinner(List<Integer>integerList);

}
