package com.example.future.smarthome.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.future.smarthome.Activities.DonationDetails;
import com.example.future.smarthome.Activities.PostsDetails;
import com.example.future.smarthome.R;
import com.example.future.smarthome.data.Donation.DonationList;

import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {
    private Context context;
    List<DonationList> donationLists;

    public DonationAdapter(Context context, List<DonationList> donationLists) {
        this.context = context;
        this.donationLists = donationLists;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.requests_row,parent,false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        final DonationList list = donationLists.get(position);
        holder.BloodType.setText(list.getBloodType().getName());
        holder.Name.setText(list.getClient().getName());
        holder.Hospital.setText(list.getHospitalName());
        holder.City.setText(list.getCity().getName());
        holder.Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+list.getClient().getPhone()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return donationLists.size();
    }

    public class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView BloodType;
        TextView Name;
        TextView Hospital;
        TextView City;
        Button Call;
        Button Details;
        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            BloodType = itemView.findViewById(R.id.BK);
            Name = itemView.findViewById(R.id.Name);
            Hospital = itemView.findViewById(R.id.Hospital);
            City = itemView.findViewById(R.id.City);
            Call = itemView.findViewById(R.id.Call);
            Details = itemView.findViewById(R.id.Details);
            Details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DonationDetails.class);
                    context.startActivity(i);
                }
            });
        }
    }
}
