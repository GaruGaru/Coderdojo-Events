package modularity.coderdojoevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import modularity.coderdojoevents.Api.EventBrite.Android.AsyncBriteRequestArea;
import modularity.coderdojoevents.Api.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;

public class ActivityMap extends AppCompatActivity implements OnMapReadyCallback {

    public static final int DEFAULT_ZOOM = 8;
    private GoogleMap map;
    private SupportMapFragment mapView;

    private DojoSettings dojoSettings;

    private Events[] events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.dojoSettings = new DojoSettings(this);
        setupMapView();

    }

    private void loadDojos() {
        new AsyncBriteRequestArea(new BriteListener() {
            @Override
            public void onRequestDone(BriteEvent eventList) {

                events = eventList.getEvents();

                for (Events e : events) {
                    LatLng location = e.getLocation();
                    String name = e.getName().getText();
                    addMarker(location, name, R.drawable.ic_dojo_pin);
                }

            }

            @Override
            public void onRequestError(int errorCode) {

            }
        }).execute("Coderdojo",
                String.valueOf(dojoSettings.getUserPosition().latitude),
                String.valueOf(dojoSettings.getUserPosition().longitude),
                "100000km",
                "venue,organizer,ticket_classes",
                "date"
        );
    }

    private void openEventInfo(Events event) {
        Intent intent = new Intent(this, ActivityEventTabbed.class);
        intent.putExtra("event", event);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    private void setupMapView() {
        if (map == null) {
            this.mapView = (SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.mapView));
            this.mapView.getMapAsync(this);
        }
    }

    private void initMap(GoogleMap map, LatLng pos) {
        this.map = map;
        if (map != null) {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            focusMap(pos);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap, dojoSettings.getUserPosition());

        loadDojos();

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Events eventByName = getEventByName(marker.getTitle());
                if (eventByName != null)
                    openEventInfo(eventByName);
            }
        });
    }

    private void focusMap(LatLng position) {
        if (position != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, DEFAULT_ZOOM);
            map.animateCamera(cameraUpdate);
            addMarker(position, "Tu", R.drawable.ic_walk);
        }
    }

    private void addMarker(LatLng position, String name, int resid) {
        map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(name)
                        .icon(BitmapDescriptorFactory.fromResource(resid))
        );

    }

    private Events getEventByName(String name) {
        for (Events e : events) {
            if (e.getName().getText().equals(name))
                return e;
        }
        return null;
    }
}
