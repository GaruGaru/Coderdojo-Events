package modularity.coderdojoevents.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;
import modularity.coderdojoevents.Activities.Fragments.EventsTabAdapter;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 24/01/2016.
 */
public class ActivityEventTabbed extends AppCompatActivity {

    @Bind(R.id.pager)
    protected ViewPager pager;

    @Bind(R.id.tabs)
    protected PagerSlidingTabStrip tabs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tabbed);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabs.setShouldExpand(true);
        EventsTabAdapter adapter = new EventsTabAdapter(this, getSupportFragmentManager(), (Events) getIntent().getSerializableExtra("event"));
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        this.setupTabs();

    }

    private void setupTabs() {
        this.tabs.setIndicatorColor(ContextCompat.getColor(this, R.color.white));
        this.tabs.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        this.tabs.setTextColor(Color.WHITE);
    }
}

