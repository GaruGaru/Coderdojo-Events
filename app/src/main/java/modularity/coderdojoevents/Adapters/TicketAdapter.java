package modularity.coderdojoevents.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import modularity.coderdojoevents.Api.EventBrite.Response.Ticket_classes;
import modularity.coderdojoevents.R;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.EventsViewHolder> {

    private Context context;
    private String eventUrl;

    private List<Ticket_classes> ticketList;

    public TicketAdapter(Context context, String eventUrl, List<Ticket_classes> ticketList) {
        this.eventUrl = eventUrl;
        this.ticketList = ticketList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    @Override
    public void onBindViewHolder(EventsViewHolder viewHolder, int i) {
        final Ticket_classes ticket = ticketList.get(i);
        viewHolder.textViewTitle.setText(ticket.getName());

        boolean available = ticket.getOn_sale_status().equals(Ticket_classes.AVAILABLE);

        viewHolder.container.setBackgroundColor(
                ContextCompat.getColor(context, (available ? R.color.green : R.color.red))
        );

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(eventUrl));
                context.startActivity(i);
            }
        };

        viewHolder.container.setOnClickListener(clickListener);
        viewHolder.imageViewTicket.setOnClickListener(clickListener);

    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_ticket, viewGroup, false);
        return new EventsViewHolder(itemView);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        protected TextView textViewTitle;
        protected ImageView imageViewTicket;
        protected View container;

        public EventsViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTicketTitle);
            imageViewTicket = (ImageView) itemView.findViewById(R.id.imageViewTicket);
            container = itemView.findViewById(R.id.ticketContainer);
        }


    }
}