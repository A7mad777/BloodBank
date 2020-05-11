package com.example.future.smarthome.View;

import com.example.future.smarthome.data.DonationDetails.Data;

import java.util.List;

public interface DonationDetailsView {
    void onSuccess(Data donationDetails);
    void onFailure(Throwable t);
}
