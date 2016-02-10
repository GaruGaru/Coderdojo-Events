package modularity.coderdojoevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import modularity.coderdojoevents.Adapters.EventsAdapter;
import modularity.coderdojoevents.Custom.SpacesItemDecoration;
import modularity.coderdojoevents.EventBrite.Android.AsyncBriteRequestArea;
import modularity.coderdojoevents.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;

public class MainActivity extends AppCompatActivity implements BriteListener {

    @Bind(R.id.eventList)
    protected RecyclerView eventView;

    private DojoSettings settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.settingsManager = new DojoSettings(this);

        setupListLayout();

        this.executeRequest(false);

        openGpsIfNeeded();

    }

    private void openGpsIfNeeded() {
        if (settingsManager.getUserPosition() == null) {
            Intent locationIntent = new Intent(this, ActivityLocation.class);
            locationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(locationIntent);
        }
    }

    protected void executeRequest(boolean forceUpdate) {
        if (settingsManager.getUserPosition() != null && (forceUpdate || settingsManager.getEvents() == null))
            new AsyncBriteRequestArea(this).execute("Coderdojo",
                    String.valueOf(settingsManager.getUserPosition().latitude),
                    String.valueOf(settingsManager.getUserPosition().longitude),
                    "50km",
                    "venue,organizer",
                    "date"
            );
        else onRequestDone(settingsManager.getEvents());
    }

    @OnClick(R.id.buttonRefresh)
    protected void onRefresh() {
        Intent locationIntent = new Intent(this, ActivityLocation.class);
        locationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(locationIntent);
    }

    private void setupListLayout() {
        eventView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        eventView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.list_spacing));
        eventView.setLayoutManager(llm);
    }

    private void setEvents(BriteEvent events) {
        EventsAdapter adapter = new EventsAdapter(this, Arrays.asList(events.getEvents()));
        eventView.setAdapter(adapter);
    }

    @Override
    public void onRequestDone(BriteEvent eventList) {
        setEvents(eventList);
        settingsManager.setEvents(eventList);
    }

    @Override
    public void onRequestError(int errorCode) {
        //TODO handle errors
    }
}
