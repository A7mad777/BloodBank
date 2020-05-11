package com.example.future.smarthome.View;

import com.example.future.smarthome.Activities.PostsDetails;
import com.example.future.smarthome.data.PostsDetails.Data;

import java.util.List;

public interface PostsDetailsView {
    void onSuccess(Data details);
    void onFailure(Throwable t);

}
