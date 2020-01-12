package com.example.experiment_1.Event;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experiment_1.LoginRegister.MainActivity;
import com.example.experiment_1.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mCtx;
    private List<Event> eventList;
    private String privacyStatus;

    public EventAdapter(Context mCtx, List<Event> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dashboard_events_list, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.eventTitle.setText(event.getEventName());
        holder.eventDesc.setText(event.getEventDescription());
        holder.eventHost.setText(holder.eventHost.getText().toString() + event.getHost());
        holder.eventDate.setText(holder.eventDate.getText().toString() + event.getEventDate());
        holder.eventID.setText(event.getId());
        privacyStatus = event.getPrivacy();

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        public View view;
        private ImageView eventImage;
        private TextView eventTitle, eventDesc, eventHost, eventDate, eventID;

        public EventViewHolder(@NonNull final View itemView) {

            super(itemView);
            view = itemView;
            eventImage = itemView.findViewById(R.id.eventImage);
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventDesc = itemView.findViewById(R.id.eventDesc);
            eventHost = itemView.findViewById(R.id.eventHost);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventID = itemView.findViewById(R.id.eventID);

            //be able to press on each event
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fromUserName = MainActivity.username;

                    Log.d("Event name", eventTitle.getText().toString());
                    Intent i = new Intent(mCtx, EventActivity.class);
                    i.putExtra("eventName", eventTitle.getText().toString());
                    i.putExtra("eventDesc", eventDesc.getText().toString());
                    Log.d("Event Desc before", eventDesc.getText().toString());

                    i.putExtra("eventHost", eventHost.getText().toString());
                    i.putExtra("eventDate", eventDate.getText().toString());
                    i.putExtra("privacy", privacyStatus);
                    i.putExtra("userName", fromUserName);

                    i.putExtra("Event ID", eventID.getText().toString());
                  //  Toast.makeText(mCtx, String.valueOf(eventID), Toast.LENGTH_LONG).show();

                   mCtx.startActivity(i);
                }
            });
        }
    }
}
