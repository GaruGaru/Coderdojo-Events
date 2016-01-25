package modularity.coderdojoevents.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import modularity.coderdojoevents.Activities.ActivityEventTabbed;
import modularity.coderdojoevents.Custom.Picasso.BlurTransformation;
import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private Context context;

    private List<Events> eventList;

    public EventsAdapter(Context context, List<Events> eventList) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(EventsViewHolder viewHolder, int i) {
        final Events event = eventList.get(i);

        String eventName = event.getName().getText();

        String organizerName = event.getOrganizer().getName();

        String venueName = FormatHelper.formatVenue(event.getVenue());

        String dateString = FormatHelper.formatDate(event.getStart(), event.getEnd());


        viewHolder.textViewEvent.setText(eventName);
        viewHolder.textViewOrganizer.setText(organizerName);
        viewHolder.textViewLocation.setText(venueName);
        viewHolder.textViewDate.setText(dateString);


        viewHolder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityEventTabbed.class);
                intent.putExtra("event", event);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        if (!event.getLogo().getUrl().isEmpty()) {
            Picasso.with(context)
                    .load(event.getLogo().getUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .transform(new BlurTransformation(context, 24))
                    .into(viewHolder.imageViewIcon);
        } else
            viewHolder.imageViewIcon.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_launcher));


    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_event_blurred, viewGroup, false);
        return new EventsViewHolder(itemView);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        protected TextView textViewEvent;

        protected TextView textViewLocation;

        protected TextView textViewDate;

        protected TextView textViewOrganizer;

        protected ImageView imageViewIcon;

        public EventsViewHolder(View itemView) {
            super(itemView);
            textViewEvent = (TextView) itemView.findViewById(R.id.textViewEvent);
            textViewOrganizer = (TextView) itemView.findViewById(R.id.textViewOrganizer);
            textViewLocation = (TextView) itemView.findViewById(R.id.textViewLocation);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.eventIcon);
        }


    }
}