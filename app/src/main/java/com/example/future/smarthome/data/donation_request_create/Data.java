
package com.example.future.smarthome.data.donation_request_create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("donationRequest")
    @Expose
    private DonationRequest donationRequest;

    public DonationRequest getDonationRequest() {
        return donationRequest;
    }

    public void setDonationRequest(DonationRequest donationRequest) {
        this.donationRequest = donationRequest;
    }

}
