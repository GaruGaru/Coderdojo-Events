package modularity.coderdojoevents.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import modularity.coderdojoevents.Adapters.FormatHelper;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 24/01/2016.
 */
public class ActivityEvent extends AppCompatActivity implements OnMapReadyCallback {

    @Bind(R.id.textViewEventTitle)
    protected TextView textViewTitle;

    @Bind(R.id.textViewEventVenue)
    protected TextView textViewEventVenue;

    @Bind(R.id.textViewEventDate)
    protected TextView textViewEventDate;

    private GoogleMap map;
    private SupportMapFragment mapView;

    private Events event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.event = (Events) getIntent().getSerializableExtra("event");
        setupLayout(event);

        setupMapView();
    }

    public void setupLayout(Events event) {
        textViewTitle.setText(event.getName().getText());
        textViewEventVenue.setText(event.getVenue().getAddress().getCity() + " - " + event.getVenue().getAddress().getAddress_1());
        textViewEventDate.setText(FormatHelper.formatDate(event.getStart(), event.getEnd()));
    }


    private void setupMapView() {
        if (map == null) {
            this.mapView = (SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.mapView));
            this.mapView.getMapAsync(this);
        }
    }

    private void initMap(GoogleMap map, LatLng pos) {

        this.map = map;
        map.getUiSettings().setMyLocationButtonEnabled(false);

        boolean permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;


        map.setMyLocationEnabled(permission);
        map.getUiSettings().setAllGesturesEnabled(false);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //  MapsInitializer.initialize(this.getActivity());

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, 15);
        map.animateCamera(cameraUpdate);


        map.addMarker(new MarkerOptions()
                        .position(pos)
                        .title("CoderDojo")
        );

    }

    @Override
    protected void onDestroy() {
        if (map != null && !isFinishing() && (getSupportFragmentManager().findFragmentById(R.id.mapView) != null)) {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.mapView)).commitAllowingStateLoss();
            map = null;

        }
        if (mapView != null)
            mapView.onDestroyView();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mapView != null) mapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null)
            mapView.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap, event.getLocation());
    }
}

