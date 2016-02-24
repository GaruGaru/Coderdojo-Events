package modularity.coderdojoevents.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;

import butterknife.OnClick;
import modularity.coderdojoevents.Adapters.FormatHelper;
import modularity.coderdojoevents.Adapters.TicketAdapter;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.Api.EventBrite.Response.Ticket_classes;
import modularity.coderdojoevents.Custom.FixedSizeLinearLayout;
import modularity.coderdojoevents.R;
import modularity.coderdojoevents.Settings.DojoSettings;
import modularity.coderdojoevents.Utils.MapsUtils;

/**
 * Created by Garu on 25/01/2016.
 */
public class FragmentEventMain extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final String ARG_POSITION = "position";
    protected TextView textViewTitle;
    protected TextView textViewEventVenue;
    protected TextView textViewEventDate;
    private GoogleMap map;
    private SupportMapFragment mapView;

    private View ticketContainerView;

    private RecyclerView ticketList;

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
        int position = getArguments().getInt(ARG_POSITION);
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
        this.ticketList = (RecyclerView) v.findViewById(R.id.ticketList);
        this.ticketContainerView = v.findViewById(R.id.cardTicketContainer);

        setupLayout(this.event);

    }

    public void setupLayout(Events event) {

        setupMapView();

        setupTicketList();

        textViewTitle.setText(FormatHelper.formatTitle(event.getName().getText()));
        textViewEventVenue.setText(FormatHelper.formatVenue(event.getVenue()));
        textViewEventDate.setText(FormatHelper.formatDate(event.getStart(), event.getEnd()));

    }

    private void setupTicketList() {
        Ticket_classes[] tickets = event.getTicket();

        if (tickets == null || tickets.length == 0) {
            this.ticketContainerView.setVisibility(View.GONE);
        } else {
            ticketList.setHasFixedSize(true);
            LinearLayoutManager llm = new FixedSizeLinearLayout(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            ticketList.setLayoutManager(llm);
            ticketList.setAdapter(new TicketAdapter(getContext(), event.getUrl(), Arrays.asList(tickets)));
        }


    }

    protected void launchMapIntent() {
        LatLng userPosition = new DojoSettings(getContext()).getUserPosition();
        LatLng destination = new LatLng(Double.valueOf(event.getVenue().getLatitude()), Double.valueOf(event.getVenue().getLongitude()));
        Intent intent = MapsUtils.getDirectionIntent(userPosition, destination);
        getContext().startActivity(intent);
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
        map.getUiSettings().setAllGesturesEnabled(false);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

        map.addMarker(new MarkerOptions()
                        .position(pos)
                        .title("CoderDojo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dojo_pin))
        );

        map.setOnMapClickListener(this);

    }

    @OnClick(R.id.buttonTicket)
    protected void openTicketUrl() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(event.getUrl()));
        getContext().startActivity(i);
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

    @Override
    public void onMapClick(LatLng latLng) {
        launchMapIntent();
    }

}
