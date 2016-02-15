package modularity.coderdojoevents.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
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

import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.OnClick;
import modularity.coderdojoevents.Location.PositionListener;
import modularity.coderdojoevents.Location.TimedPositionRequester;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;
import modularity.coderdojoevents.Utils.GeoUtils;

public class ActivityLocation extends AppCompatActivity implements PositionListener, OnMapReadyCallback {

    public static final String EXTRA_REFRESH = "REFRESH";
    private LatLng currentLatLng = null;

    private GoogleMap map;
    private SupportMapFragment mapView;

    private PlaceAutocompleteFragment autocompleteFragment;

    private DojoSettings dojoSettings;
    private MaterialDialog autoGpsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.dojoSettings = new DojoSettings(this);
        //initMap();
        setupMapView();
        setupAutocomplete();

        showDialogIfNeeded();


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


    private void setupAutocomplete() {
        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setFilter(AutocompleteFilter.create(Collections.singletonList(AutocompleteFilter.TYPE_FILTER_CITIES)));
        autocompleteFragment.setHint(getString(R.string.city));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                ActivityLocation.this.onPlaceSelected(place);
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getBaseContext(), R.string.message_location_notfound, Toast.LENGTH_LONG).show();
                Log.i("DOJO", "An error occurred: " + status);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                onPlaceSelected(place);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                // Error
            } else if (resultCode == RESULT_CANCELED) {
                // Dismiss
            }
        }
    }

    private void onPlaceSelected(Place place) {
        LatLng latLng = GeoUtils.getLatLng(getBaseContext(), place.getName());
        currentLatLng = latLng;
        if (latLng == null)
            Toast.makeText(getBaseContext(), R.string.message_location_decode_error, Toast.LENGTH_LONG).show();
        else {
            if (!GeoUtils.getCityByLatLng(this, currentLatLng).equals("null")) {
                focusMap(latLng);
                showDialogConfirm();
            } else showErrorDialog();
        }
    }

    @OnClick(R.id.buttonConfirm)
    protected void confirm() {
        if (currentLatLng != null) {
            DojoSettings settings = new DojoSettings(this);
            settings.setUserPosition(currentLatLng);
            settings.setEvents(null);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(EXTRA_REFRESH, true);
            startActivity(intent);
        } else showErrorDialog();
    }

    private void showErrorDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.title_user_pos)
                .content("Prima di procedere è necessaria una posizione valida")
                .positiveText("Ok")
                .show();
    }

    @Override
    public void onPositionFixed(LatLng position) {

        if (autoGpsDialog != null)
            autoGpsDialog.dismiss();

        if (position != null) {
            currentLatLng = new LatLng(position.latitude, position.longitude);
            ((PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment))
                    .setText(GeoUtils.getCityByLatLng(this, position));
            focusMap(position);
            showDialogConfirm();
        } else {
            if (autoGpsDialog != null) handleAutoGpsFailture();
            else Toast.makeText(this, R.string.message_gps_error, Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogConfirm() {
        new MaterialDialog.Builder(this)
                .title(R.string.title_user_pos)
                .content(getString(R.string.string_your_city) + GeoUtils.getCityByLatLng(this, currentLatLng) + getString(R.string.string_remember_pos))
                .positiveText("Ok")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        confirm();
                    }
                })
                .negativeText(R.string.string_dismiss)
                .show();
    }

    private void handleAutoGpsFailture() {

        new MaterialDialog.Builder(this)
                .title(R.string.title_gps_needed)
                .content(R.string.message_gps_auto_failed)
                .positiveText("Ok")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        showAutocompleteLocation();
                    }
                })
                .show();
    }

    private void showAutocompleteLocation() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .build(this);
            startActivityForResult(intent, 1);


        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Toast.makeText(this, "Google play service not available!", Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogIfNeeded() {
        if (dojoSettings.getUserPosition() == null) {
            new MaterialDialog.Builder(this)
                    .title(R.string.title_gps_needed)
                    .content(R.string.message_gps_needed)
                    .positiveText("Ok")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            gpsAutoSearch();
                        }
                    })
                    .show();
        }
    }

    @OnClick(R.id.gpsButton)
    protected void gpsAutoSearch() {
        new TimedPositionRequester(this, 10000).requestPosition(getBaseContext());
        autoGpsDialog = new MaterialDialog.Builder(this)
                .title("Gps")
                .content(R.string.message_gps_wait)
                .progress(true, 0)
                .show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap, dojoSettings.getUserPosition());
        LatLng userPosition = dojoSettings.getUserPosition();
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
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_walk))
            );
        }
    }
}
