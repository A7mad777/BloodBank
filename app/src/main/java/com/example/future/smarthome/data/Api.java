package com.example.future.smarthome.data;

import com.example.future.smarthome.Activities.DonationDetails;
import com.example.future.smarthome.Activities.PostsDetails;
import com.example.future.smarthome.data.ContactUs.ContactUs;
import com.example.future.smarthome.data.Donation.Donation;
import com.example.future.smarthome.data.FavouriteList.FavouriteList;
import com.example.future.smarthome.data.ListOfBlood.ListOfBlood;
import com.example.future.smarthome.data.ListOfGovernorates.ListOfGovernorates;
import com.example.future.smarthome.data.ListOofCities.ListOfCities;
import com.example.future.smarthome.data.Login.Data;
import com.example.future.smarthome.data.Login.Login;
import com.example.future.smarthome.data.NewPassWord.NewPassWord;
import com.example.future.smarthome.data.NotificationCount.NotificationCount;
import com.example.future.smarthome.data.NotificationList.NotificationList;
import com.example.future.smarthome.data.NotificationSetting.NotificationSetting;
import com.example.future.smarthome.data.Profile.Profile;
import com.example.future.smarthome.data.Register.Register;
import com.example.future.smarthome.data.Report.Report;
import com.example.future.smarthome.data.ResetPassWord.ResetPassWord;
import com.example.future.smarthome.data.Settings.Settings;
import com.example.future.smarthome.data.donation_request_create.DonationRequestCreate;
import com.example.future.smarthome.data.postToggleFavourite.PostToggleFavourite;
import com.example.future.smarthome.data.posts.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("login")
    Call<Login>setUser(
            @Field("phone") String phone,
            @Field("password") String password
    );
   @FormUrlEncoded
   @POST("register")
   Call<Register>creatUser(
       @Field("name") String name,
       @Field("email") String mail,
       @Field("birth_date") String birth_date,
       @Field("city_id") int city_id,
       @Field("phone") String phone,
       @Field("donation_last_date") String donation_date,
       @Field("password") String passWord,
       @Field("password_confirmation") String passWord_confirm,
       @Field("blood_type_id") int blood_type
   );
   @GET("posts")
    Call<Posts>getPosts(@Query("api_token") String Api_Token,
                        @Query("page1") int page);
   @GET("donation-requests")
    Call<Donation>getDonation(@Query("api_token") String api_token,@Query("page") int page);

   @GET("post")
    Call<com.example.future.smarthome.data.PostsDetails.PostsDetails>getPostsDetails(@Query("api_token")String api_token, @Query("post_id") int id, @Query("page") int page);

   @GET("donation-request")
    Call<com.example.future.smarthome.data.DonationDetails.DonationDetails>getDonationDetails(@Query("api_token")String api_token, @Query("donation_id")int id);

   @GET("notifications-count")
    Call<NotificationCount>getNotificationCount(@Query("api_token") String api_token);

   @GET("notifications")
    Call<NotificationList>getNotificationList(@Query("api_token")String api_token);

   @FormUrlEncoded
   @POST("profile")
    Call<Profile>editProfile(@Field("name") String name,
                             @Field("email") String email,
                             @Field("birth_date") String birth_date,
                             @Field("city_id") int city_id,
                             @Field("phone") String phone,
                             @Field("donation_last_date") String donation_date,
                             @Field("password") String passWord,
                             @Field("password_confirmation") String confirm_PassWord,
                             @Field("blood_type_id") int blood_id,
                             @Field("api_token") String api_token);
   @GET("governorates")
    Call<ListOfGovernorates>getListOfGovernors();


   @GET("cities")
    Call<ListOfCities>getListOfCities(@Query("governorate_id") int id);

   @GET("blood-types")
    Call<ListOfBlood>getBloodTypes();

   @FormUrlEncoded
    @POST("donation-request/create")
    Call<DonationRequestCreate>createDonationRequest(
            @Field("api_token") String api_token,
            @Field("patient_name") String patient_name,
            @Field("patient_age") String patient_age,
            @Field("blood_type_id") int blood_type_id,
            @Field("bags_num") String bags_num,
            @Field("hospital_name") String hospital_name,
            @Field("hospital_address") String hospital_address,
            @Field("city_id") int city_id,
            @Field("phone") String phone,
            @Field("notes") String notes,
            @Field("latitude") Double latitude,
            @Field("longitude") Double longitude
   );
   @FormUrlEncoded
    @POST("post-toggle-favourite")
    Call<PostToggleFavourite>checkPost(
            @Field("post_id")int post_id,
            @Field("api_token") String api_token
   );
   @GET("my-favourites")
    Call<FavouriteList>getFavouriteList(@Query("api_token") String api_token);

   @FormUrlEncoded
   @POST("contact")
    Call<ContactUs>createContact(
            @Field("api_token") String api_token,
            @Field("title") String title,
            @Field("message") String message
   );
   @GET("settings")
    Call<Settings>getSetting(@Query("api_token") String api_token);

   @FormUrlEncoded
    @POST("report")
    Call<Report>sendReport(
            @Field("api_token") String api_token,
            @Field("message") String message
   );
   @FormUrlEncoded
    @POST("notifications-settings")
    Call<NotificationSetting>setNotificationSetting(
           @Field("api_token") String api_token,
           @Field("governorates[]")List<String>governors,
           @Field("blood_types[]")List<String>blood_type
           );
   @FormUrlEncoded
    @POST("reset-password")
    Call<ResetPassWord>setResetPassWord(
            @Field("phone") String phone
   );
   @FormUrlEncoded
    @POST("new-password")
    Call<NewPassWord> setNewPassWord(
            @Field("password") String passWord,
            @Field("password_confirmation") String password_confirmation,
            @Field("pin_code") String pin_code,
            @Field("phone") String phone
   );
}
