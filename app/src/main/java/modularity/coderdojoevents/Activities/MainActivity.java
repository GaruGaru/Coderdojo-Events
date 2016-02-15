package modularity.coderdojoevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import modularity.coderdojoevents.Adapters.EventsAdapter;
import modularity.coderdojoevents.Api.EventBrite.Android.AsyncBriteRequestArea;
import modularity.coderdojoevents.Api.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.Custom.SpacesItemDecoration;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;

public class MainActivity extends AppCompatActivity implements BriteListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.eventList)
    protected RecyclerView eventView;
    @Bind(R.id.messageLayoutContainer)
    protected View messageContainerView;
    @Bind(R.id.textViewMessage)
    protected TextView textViewMessage;
    private DojoSettings settingsManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.settingsManager = new DojoSettings(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe2Refresh);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent
        );

        swipeRefreshLayout.setOnRefreshListener(this);


        setupListLayout();

        this.executeRequest(false);

        openGpsIfNeeded();

    }


    @Override
    protected void onResume() {
        super.onResume();
        openGpsIfNeeded();
    }

    @Override
    protected void onStart() {
        super.onStart();
        openGpsIfNeeded();
    }

    private void openGpsIfNeeded() {
        if (settingsManager.getUserPosition() == null) {
            Intent locationIntent = new Intent(this, ActivityWelcome.class);
            locationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(locationIntent);
        }
    }

    // Todo request builder
    protected void executeRequest(boolean forceUpdate) {
        swipeRefreshLayout.setRefreshing(true);
        setEvents(null);
        if (settingsManager.getUserPosition() != null && (forceUpdate || settingsManager.getEvents() == null))
            new AsyncBriteRequestArea(this).execute("Coderdojo",
                    String.valueOf(settingsManager.getUserPosition().latitude),
                    String.valueOf(settingsManager.getUserPosition().longitude),
                    "50km",
                    "venue,organizer,ticket_classes",
                    "date"
            );
        else onRequestDone(settingsManager.getEvents());
    }

    @OnClick(R.id.buttonWorld)
    protected void openWorldView() {
        Intent intent = new Intent(this, ActivityMap.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.buttonRefresh)
    protected void onButtonClick() {
        Intent locationIntent = new Intent(this, ActivityLocation.class);
        locationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(locationIntent);
    }

    @Override
    public void onRefresh() {
        executeRequest(true);
    }

    private void setupListLayout() {
        eventView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        eventView.addItemDecoration(new SpacesItemDecoration(this, 0, 0));
        eventView.setLayoutManager(llm);
    }

    private void setEvents(BriteEvent events) {
        this.messageContainerView.setVisibility(View.GONE);
        this.eventView.setVisibility(View.VISIBLE);
        EventsAdapter adapter = new EventsAdapter(this, (events != null) ? Arrays.asList(events.getEvents()) : new ArrayList<Events>());
        eventView.setAdapter(adapter);
    }

    @Override
    public void onRequestDone(BriteEvent eventList) {
        if (eventList != null && eventList.getEvents() != null && eventList.getEvents().length > 0)
            setEvents(eventList);
        else
            showErrorMessage(getString(R.string.message_no_events));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestError(int errorCode) {
        swipeRefreshLayout.setRefreshing(false);
        showErrorMessage(getString(R.string.message_connection_error));
    }


    private void showErrorMessage(String message) {
        this.textViewMessage.setText(message);
        this.messageContainerView.setVisibility(View.VISIBLE);
        this.eventView.setVisibility(View.GONE);
    }

}
