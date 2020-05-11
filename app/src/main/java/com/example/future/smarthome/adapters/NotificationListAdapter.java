package com.example.future.smarthome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.future.smarthome.R;
import com.example.future.smarthome.data.NotificationList.Datum;

import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder> {
    private Context context;
    List<Datum>notficationList;

    public NotificationListAdapter(List<Datum> notficationList,Context context) {
        this.context = context;
        this.notficationList = notficationList;
    }

    @NonNull
    @Override
    public NotificationListAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.notification_row,parent,false);
        return new NotificationListAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationListAdapter.NotificationViewHolder holder, int position) {
        Datum notify = notficationList.get(position);
        holder.title.setText(notify.getContent());
        holder.date.setText(notify.getCreatedAt());
    }
    @Override
    public int getItemCount() {
        return notficationList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView15);
            date = itemView.findViewById(R.id.textView22);
        }
    }
}
