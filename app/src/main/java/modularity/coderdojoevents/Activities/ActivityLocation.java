package modularity.coderdojoevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import modularity.coderdojoevents.Location.PositionListener;
import modularity.coderdojoevents.Location.TimedPositionRequester;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;
import modularity.coderdojoevents.Utils.GeoUtils;

public class ActivityLocation extends AppCompatActivity implements PositionListener, OnMapReadyCallback {

    private LatLng currentLatLng = null;

    private GoogleMap map;
    private SupportMapFragment mapView;

    private PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initMap();
        setupMapView();
        setupAutocomplete();


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
            map.clear();
            focusMap(pos);
        }
    }

    @OnClick(R.id.gpsButton)
    protected void requestGps() {
        new TimedPositionRequester(this, 5000).requestPosition(getBaseContext());
        Toast.makeText(this, R.string.message_waiting_gps, Toast.LENGTH_SHORT).show();
    }

    private void setupAutocomplete() {
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                LatLng latLng = GeoUtils.getLatLng(getBaseContext(), place.getName());
                currentLatLng = latLng;
                if (latLng == null)
                    Toast.makeText(getBaseContext(), R.string.message_location_decode_error, Toast.LENGTH_LONG).show();
                else focusMap(latLng);
                Log.i("DOJO", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getBaseContext(), R.string.message_location_notfound, Toast.LENGTH_LONG).show();
                Log.i("DOJO", "An error occurred: " + status);
            }
        });
    }

    @OnClick(R.id.buttonConfirm)
    protected void confirm() {
        if (currentLatLng != null) {
            new DojoSettings(this).setUserPosition(currentLatLng);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else Toast.makeText(this, R.string.message_invalid_location, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPositionFixed(LatLng position) {
        if (position != null) {
            currentLatLng = new LatLng(position.latitude, position.longitude);
            ((PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment))
                    .setText(GeoUtils.getCityByLatLng(this, position));
            focusMap(position);
        } else
            Toast.makeText(this, R.string.message_gps_error, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap, new DojoSettings(this).getUserPosition());
        LatLng userPosition = new DojoSettings(this).getUserPosition();
        if (userPosition != null) {
            googleMap.getUiSettings().setAllGesturesEnabled(false);
            focusMap(userPosition);
            autocompleteFragment.setText(GeoUtils.getCityByLatLng(this, userPosition));
            this.currentLatLng = userPosition;
        }
    }

    private void focusMap(LatLng position) {
        if (position != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 15);
            map.animateCamera(cameraUpdate);
            map.addMarker(new MarkerOptions()
                            .position(position)
                            .title("Tu")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dojo_pin))
            );
        }
    }
}
