package modularity.coderdojoevents.Activities.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 25/01/2016.
 */
public class FragmentEventMap extends Fragment implements OnMapReadyCallback {

    private static final String ARG_POSITION = "position";
    private int position;

    private GoogleMap map;
    private SupportMapFragment mapView;

    private Events event;

    public static FragmentEventMap newInstance(int position, Events event) {

        FragmentEventMap f = new FragmentEventMap();
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
        View v = inflater.inflate(R.layout.fragment_event_map, container, false);
        this.event = (Events) getArguments().getSerializable("event");
        initLayout(v);
        return v;

    }


    private void initLayout(View v) {
        setupLayout(this.event);
    }

    public void setupLayout(Events event) {
        setupMapView();
    }


    private void setupMapView() {
        if (map == null) {
            this.mapView = (SupportMapFragment) (getChildFragmentManager().findFragmentById(R.id.mapViewFull));
            this.mapView.getMapAsync(this);
        }
    }

    private void initMap(GoogleMap map, LatLng pos) {

        this.map = map;
        if (map != null) {
            map.getUiSettings().setMyLocationButtonEnabled(false);

            boolean permission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;


            map.setMyLocationEnabled(permission);
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, 15);
            map.animateCamera(cameraUpdate);
            map.addMarker(new MarkerOptions()
                            .position(pos)
                            .title("CoderDojo")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dojo_pin))
            );
        }
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (map == null) {
            this.mapView = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapViewFull));
            this.mapView.getMapAsync(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (map != null && !this.getActivity().isFinishing() && (getChildFragmentManager().findFragmentById(R.id.mapViewFull) != null)) {
            map.clear();
            getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentById(R.id.mapViewFull)).commitAllowingStateLoss();
            map = null;

        }
        if (mapView != null) {
            mapView.onDestroyView();
            mapView = null;
        }

        super.onDestroyView();
    }


    @Override
    public void onPause() {
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
        LatLng location = new LatLng(
                Double.valueOf(event.getVenue().getLatitude()),
                Double.valueOf(event.getVenue().getLongitude())
        );
        initMap(googleMap, location);
    }

}
