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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import modularity.coderdojoevents.Adapters.FormatHelper;
import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.R;

/**
 * Created by Garu on 25/01/2016.
 */
public class FragmentEventMain extends Fragment implements OnMapReadyCallback {

    private static final String ARG_POSITION = "position";
    protected TextView textViewTitle;
    protected TextView textViewEventVenue;
    protected TextView textViewEventDate;
    private int position;
    private GoogleMap map;
    private SupportMapFragment mapView;

    private Events event;


    public static FragmentEventMain newInstance(int position, Events event) {

        FragmentEventMain f = new FragmentEventMain();
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

        View v = inflater.inflate(R.layout.fragment_event, container, false);
        this.event = (Events) getArguments().getSerializable("event");
        initLayout(v);
        return v;
    }


    private void initLayout(View v) {
        this.textViewEventDate = (TextView) v.findViewById(R.id.textViewEventDate);
        this.textViewEventVenue = (TextView) v.findViewById(R.id.textViewEventVenue);
        this.textViewTitle = (TextView) v.findViewById(R.id.textViewEventTitle);
        setupLayout(this.event);
    }

    public void setupLayout(Events event) {
        textViewTitle.setText(event.getName().getText());
        textViewEventVenue.setText(event.getVenue().getAddress().getCity() + " - " + event.getVenue().getAddress().getAddress_1());
        textViewEventDate.setText(FormatHelper.formatDate(event.getStart(), event.getEnd()));

        setupMapView();
    }


    private void setupMapView() {
        if (mapView == null) {
            this.mapView = (SupportMapFragment) (getChildFragmentManager().findFragmentById(R.id.mapView));
            this.mapView.getMapAsync(this);
        }
    }

    private void initMap(GoogleMap map, LatLng pos) {

        this.map = map;
        map.getUiSettings().setMyLocationButtonEnabled(false);

        boolean permission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;


        map.setMyLocationEnabled(permission);
        map.getUiSettings().setAllGesturesEnabled(false);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //  MapsInitializer.initialize(this.getActivity());

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, 15);
        map.animateCamera(cameraUpdate);


        map.addMarker(new MarkerOptions()
                        .position(pos)
                        .title("CoderDojo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dojo_pin))
        );

    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (map == null) {
            this.mapView = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView));
            this.mapView.getMapAsync(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (getChildFragmentManager().findFragmentById(R.id.mapView) != null) {
            map.clear();
            getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentById(R.id.mapView)).commitAllowingStateLoss();
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
        if (mapView != null)
            mapView.onPause();
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
