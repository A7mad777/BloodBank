package com.example.future.smarthome.View;

import com.example.future.smarthome.data.Donation.DonationList;

import java.util.List;

public interface DonationView {
    void getDonationList(List<DonationList> donationLists);
    void onFailure(Throwable t);
}
