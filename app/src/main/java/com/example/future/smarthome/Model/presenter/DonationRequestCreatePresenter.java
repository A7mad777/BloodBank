package com.example.future.smarthome.Model.presenter;

public interface DonationRequestCreatePresenter {
    void setDonationRequestData(String api_token,String patient_name,String patient_age,int blood_type_id,String bags_num,String hospital_name,String hospital_address,int city_id,String phone,String notes,Double latitude,Double longtitude);
}
