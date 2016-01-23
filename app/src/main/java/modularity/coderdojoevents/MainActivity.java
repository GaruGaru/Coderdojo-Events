package modularity.coderdojoevents;

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
import modularity.coderdojoevents.EventBrite.Api.BriteManager;
import modularity.coderdojoevents.EventBrite.Response.BriteEventByArea;
import modularity.coderdojoevents.EventBrite.Response.EventList;

public class MainActivity extends AppCompatActivity implements BriteListener {

    @Bind(R.id.eventList)
    protected RecyclerView eventView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupListLayout();
    }


    @OnClick(R.id.buttonRefresh)
    protected void executeRequest() {
        new AsyncBriteRequestArea(this).execute("Coderdojo", "43.4674366", "11.15248447", "50km", "venue", "distance");
    }

    private void setupListLayout() {
        eventView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        eventView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.list_spacing));
        eventView.setLayoutManager(llm);
    }

    private void setEvents(BriteEventByArea events) {
        EventsAdapter adapter = new EventsAdapter(Arrays.asList(events.getEvents()));
        eventView.setAdapter(adapter);
    }

    @Override
    public void onRequestDone(BriteEventByArea eventList) {
        setEvents(eventList);
    }

    @Override
    public void onRequestError(int errorCode) {
        //TODO handle errors
    }
}
