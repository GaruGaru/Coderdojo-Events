package modularity.coderdojoevents.Activities.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 25/01/2016.
 */
public class FragmentEventDescription extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;


    private Events event;
    private TextView textViewDescription;

    public static FragmentEventDescription newInstance(int position, Events event) {
        FragmentEventDescription f = new FragmentEventDescription();
        Bundle b = new Bundle();
        b.putSerializable("event", event);
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_description, container, false);
        this.event = (Events) getArguments().getSerializable("event");
        initLayout(v);
        return v;
    }

    private void initLayout(View v) {
        this.textViewDescription = (TextView) v.findViewById(R.id.textViewDescription);
        setupLayout(this.event);
    }

    public void setupLayout(Events event) {
        textViewDescription.setText(event.getDescription().getText());
    }


}
