package modularity.coderdojoevents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.EventBrite.Response.TopMatchEvents;
import modularity.coderdojoevents.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private List<Events> eventList;

    public EventsAdapter(List<Events> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(EventsViewHolder viewHolder, int i) {
        Events event = eventList.get(i);

        viewHolder.textViewEvent.setText(event.getName().getText());
        viewHolder.textViewOrganizer.setText(event.getOrganizer().getName());
        viewHolder.textViewLocation.setText(event.getVenue().getName());
        viewHolder.textViewDate.setText(event.getStart().getLocal());

    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_event, viewGroup, false);
        return new EventsViewHolder(itemView);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        protected TextView textViewEvent;

        protected TextView textViewLocation;

        protected TextView textViewDate;

        @Bind(R.id.textViewOrganizer)
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