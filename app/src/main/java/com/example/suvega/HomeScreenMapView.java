package com.example.suvega;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.List;
//
public class HomeScreenMapView extends AppCompatActivity implements OnMapReadyCallback,SingleVehiclePopUp.OnNavigationClickListener{
    /*initialize every variable you are going to use */

    int backgroundColor;
    private ViewGroup rootView;
    private View helperOverlay;
    private View mapZoom;

    private boolean isOverlayShown = false;

    private nav_bar_activity_filter.OnFilterClick onFilterClick;
    private SingleVehiclePopUp.OnNavigationClickListener navigationClickListener;
    private boolean iconsVisible = false;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String FIRST_TIME_KEY = "firstTime";

    private BottomSheetBehavior<View> bottomSheetBehaviorDefault;
    private BottomSheetBehavior<View> bottomSheetSingleVehiclePopUp;
    private BottomSheetBehavior<View> bottomSheetTripMapView;
    LinearLayout frameLayoutNavBar;
    LinearLayout frameLayoutSingleVehicle,frameLayoutTripMapView;
    ImageView image_satellite, image_icon1, image_icon2, image_icon3, image_icon4,
            image_suvega, image_plus, image_filter,
            image_arrow,image_flag;
    ImageView image_close,image_brake,image_speed_meter,image_turn,image_naviagtionPin;
    TextView textView_mapView,textView_event;
    private GoogleMap googleMap;
    private TextView instructionTextView;
    private GeoApiContext geoApiContext;
    SingleVehiclePopUp bottomSheet;

    List<LatLng> polylinePoints = new ArrayList<>();
    LatLng origin = new LatLng(17.3850, 78.4867);
    LatLng destination = new LatLng(16.5062, 80.6480);
    private LinearLayout thirdBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_map_view);

        backgroundColor = getResources().getColor(R.color.teal_700);

        rootView = findViewById(android.R.id.content);

        geoApiContext = new GeoApiContext.Builder()
                .apiKey("AIzaSyAsBw2aVErXL04xlyjNez4kBvIWEa6ahH0")
                .build();

        /*declare variables with their value*/

        instructionTextView = findViewById(R.id.instructionTextView);

        image_icon1 = findViewById(R.id.imgIcon1);
        image_icon2 = findViewById(R.id.imgIcon2);
        image_icon3 = findViewById(R.id.imgIcon3);
        image_icon4 = findViewById(R.id.imgIcon4);

        image_satellite = findViewById(R.id.img_satellite);
        image_suvega = findViewById(R.id.img_suvega);
        image_plus = findViewById(R.id.img_plus);
        image_filter = findViewById(R.id.img_filter);

        textView_mapView = findViewById(R.id.textView_mapView);
        image_arrow = findViewById(R.id.image_arrow);

        image_flag = findViewById(R.id.image_flag);
        image_close = findViewById(R.id.image_close);
        image_brake = findViewById(R.id.image_brake);
        image_speed_meter = findViewById(R.id.image_speedo_meter);
        image_turn = findViewById(R.id.image_turn);
        image_naviagtionPin = findViewById(R.id.image_navigation_pin);

        /**/
        /* here initialized parent layout and child layout and passing child layout to parent layout
         * and assigning it to BottomSheetBehaviour classes variable */

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinatorLayout);
        frameLayoutNavBar = coordinatorLayout.findViewById(R.id.frameLayout_nav_bar);
        frameLayoutSingleVehicle = coordinatorLayout.findViewById(R.id.frameLayout_single_vehicle_pop_up);
        bottomSheetSingleVehiclePopUp = BottomSheetBehavior.from(frameLayoutSingleVehicle);
        bottomSheetBehaviorDefault = BottomSheetBehavior.from(frameLayoutNavBar);

        thirdBottomSheet = coordinatorLayout.findViewById(R.id.linear_layout_trip_map_view);
        bottomSheetTripMapView = BottomSheetBehavior.from(thirdBottomSheet);
        thirdBottomSheet.setVisibility(View.GONE);
        /*setting a listener to bottomSheet then you will get two more default methods are onStateChange and onSlide */

        bottomSheetBehaviorDefault.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == STATE_EXPANDED || newState == STATE_DRAGGING) {
                    hideIcons();

                } else if (newState == STATE_COLLAPSED) {
                    showIcons();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        image_filter.setOnClickListener(v -> {
            onFilterClick();
        });

        bottomSheetSingleVehiclePopUp.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == STATE_EXPANDED) {
                    hideIcons();

                } else if (newState == STATE_COLLAPSED) {
                    showIcons();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        //for satellite view
        image_satellite.setOnClickListener(v -> toggleMapType());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        View navigationIcon = findViewById(R.id.image_navigation_single_vehicle_popup);
        navigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the third bottom sheet
                if (!isOverlayShown){
                    bottomSheetTripMapView.setState(STATE_EXPANDED);
                    thirdBottomSheet.setVisibility(View.VISIBLE);
                    frameLayoutSingleVehicle.setVisibility(View.GONE);
                    image_arrow.setVisibility(View.VISIBLE);
                    textView_mapView.setVisibility(View.VISIBLE);
                    image_flag.setVisibility(View.VISIBLE);

                    helperOverlay = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_helper, (ViewGroup) getWindow().getDecorView(), false);

                    ((ViewGroup) getWindow().getDecorView()).addView(helperOverlay);

                    View flagIcon = findViewById(R.id.image_flag);

                    flagIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            helperOverlay.setVisibility(View.VISIBLE);
                            onFlagClick(new View(getApplicationContext()));
                        }
                    });
                    helperOverlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            helperOverlay.setVisibility(View.GONE);
                        }
                    });
                }

//                image_flag.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        createHelperOverlay();
//                    }
//                });

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<Items> items = new ArrayList<Items>();
        String vehicleNumber = "TS07AP1234";
        String location = "Hyderabad";
        int navigation = R.drawable.navigation;
        int profilePic = R.drawable.profile;
        String name = "Driver";
        String id = "0111230";
        int phone = R.drawable.phone;

        int numberOfItems = 100000;

        for (int i = 0; i < numberOfItems; i++) {
            items.add(new Items(vehicleNumber, location, navigation, profilePic, name, id, phone));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

        findViewById(R.id.button_trip_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenMapView.this,TripDetails.class);
                startActivity(intent);
            }
        });
    }


//    private void createHelperOverlay() {
//        // Create the root layout for the overlay
//        RelativeLayout overlayLayout = new RelativeLayout(this);
//        overlayLayout.setLayoutParams(new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//
//        // Semi-transparent background to dim the map
//        View dimView = new View(this);
//        dimView.setLayoutParams(new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        dimView.setBackgroundColor(Color.parseColor("#80000000"));
//        overlayLayout.addView(dimView);
//
//        // Arrow pointing towards the flag icon
//        ImageView arrowImage = new ImageView(this);
//        arrowImage.setLayoutParams(new RelativeLayout.LayoutParams(
//                getResources().getDimensionPixelSize(R.dimen.arrow_width),
//                getResources().getDimensionPixelSize(R.dimen.arrow_height)));
//        arrowImage.setImageResource(R.drawable.noun_arrow); // Replace with your arrow image
//        RelativeLayout.LayoutParams arrowParams = (RelativeLayout.LayoutParams) arrowImage.getLayoutParams();
//        arrowParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        arrowParams.topMargin = getResources().getDimensionPixelSize(R.dimen.arrow_margin_top);
//        overlayLayout.addView(arrowImage);
//
//        // Text explaining the feature
//        TextView textView = new TextView(this);
//        textView.setLayoutParams(new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//        textView.setText("This is the flag icon. Tap to learn more!");
//        textView.setTextColor(Color.WHITE);
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        RelativeLayout.LayoutParams textParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//        textParams.addRule(RelativeLayout.BELOW, arrowImage.getId());
//        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        textParams.topMargin = getResources().getDimensionPixelSize(R.dimen.text_margin_top);
//        overlayLayout.addView(textView);
//
//        // Set up the click listener to hide the overlay
//        overlayLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hideHelperOverlay();
//            }
//        });
//
//        // Add the overlay layout to the root view
//        helperOverlay = overlayLayout;
//        rootView.addView(helperOverlay);
//        helperOverlay.setVisibility(View.GONE);
//    }
//
//    private void hideHelperOverlay() {
//        if (helperOverlay != null) {
//            helperOverlay.setVisibility(View.GONE);
//        }
//    }


    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        addMarker(new LatLng(17.3850, 78.4867), "Hyderabad", R.id.imgIcon1);
        addMarker(new LatLng(16.5062, 80.6480), "vijayawada", R.id.imgIcon2);
        addMarker(new LatLng(16.3067, 80.4365), "guntur", R.id.imgIcon3);
        addMarker(new LatLng(17.6868, 83.2185), "visakhapatnam", R.id.imgIcon4);

        addMarker(origin, "Hyderabad", R.id.imgIcon1);
        addMarker(destination, "Vijayawada", R.id.imgIcon2);

        getDirections(origin, destination);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
//                SingleVehiclePopUp singleVehiclePopUp = SingleVehiclePopUp.newInstance(navigationClickListener);
//                singleVehiclePopUp.show(getSupportFragmentManager(), singleVehiclePopUp.getTag());
//                bottomSheetSingleVehiclePopUp.setState(STATE_EXPANDED);
                implement();
                return false;
            }
        });


    }
    private void getDirections(LatLng origin, LatLng destination) {
        com.google.maps.model.LatLng originLatLng = new com.google.maps.model.LatLng(origin.latitude, origin.longitude);
        com.google.maps.model.LatLng destLatLng = new com.google.maps.model.LatLng(destination.latitude, destination.longitude);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin, 10f));

        DirectionsApiRequest directionsRequest = new DirectionsApiRequest(geoApiContext);
        directionsRequest.origin(originLatLng);
        directionsRequest.destination(destLatLng);
        directionsRequest.mode(TravelMode.WALKING);

        directionsRequest.setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                showBestRoute(result);
            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }
        });
    }
    private void showBestRoute(DirectionsResult directionsResult) {
        if (directionsResult.routes != null && directionsResult.routes.length > 0) {
            DirectionsRoute bestRoute = directionsResult.routes[0];

            List<LatLng> decodedPath = PolyUtil.decode(bestRoute.overviewPolyline.getEncodedPath());
            Log.d("Polyline", "Decoded Path Size: " + decodedPath.size());

            if (decodedPath.size() > 0) {
                PolylineOptions polylineOptions = new PolylineOptions()
                        .addAll(decodedPath)
                        .color(Color.YELLOW)
                        .width(10f);

                if (googleMap != null) {
                    Polyline polyline = googleMap.addPolyline(polylineOptions);

                    LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
                    boundsBuilder.include(origin);
                    boundsBuilder.include(destination);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 100));
                } else {
                    Log.e("Polyline", "Google Map is null");
                }
            } else {
                Log.e("Polyline", "Decoded Path is empty");
            }
        } else {
            Log.e("Polyline", "No routes found");
        }
    }
    private void addMarker(LatLng position, String title,int iconId) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(position)
                .title(title);
        ImageView imageView=findViewById(iconId);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(
                ((BitmapDrawable) imageView.getDrawable()).getBitmap());
        markerOptions.icon(icon);
        Marker marker = googleMap.addMarker(markerOptions);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        assert marker != null;
        builder.include(marker.getPosition());
        LatLngBounds bounds = builder.build();
        int padding = 100;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cameraUpdate);
    }

    private void hideIcons() {
        image_satellite.setVisibility(View.GONE);
        image_suvega.setVisibility(View.GONE);
        image_plus.setVisibility(View.GONE);
        image_filter.setVisibility(View.GONE);

    }

    private void showIcons() {
        image_satellite.setVisibility(View.VISIBLE);
        image_suvega.setVisibility(View.VISIBLE);
        image_plus.setVisibility(View.VISIBLE);
        image_filter.setVisibility(View.VISIBLE);

    }
    private void implement(){
        if(bottomSheetBehaviorDefault.getState()==STATE_COLLAPSED){
            bottomSheetSingleVehiclePopUp.setState(STATE_EXPANDED);
            frameLayoutNavBar.setVisibility(View.GONE);
        }
        else{
            bottomSheetBehaviorDefault.setState(STATE_EXPANDED);
            frameLayoutNavBar.setVisibility(View.VISIBLE);
            frameLayoutSingleVehicle.setVisibility(View.GONE);
        }
    }
    private void toggleMapType() {
        if (googleMap != null) {
            if (googleMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            } else {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        }
    }
//    public void toggleIcons(View view) {
//        if (iconsVisible) {
//            image_brake.setVisibility(View.GONE);
//            image_speedoMeter.setVisibility(View.GONE);
//            image_turn.setVisibility(View.GONE);
//            image_navigation_pin.setVisibility(View.GONE);
//        } else {
//            image_brake.setVisibility(View.VISIBLE);
//            image_speedoMeter.setVisibility(View.VISIBLE);
//            image_turn.setVisibility(View.VISIBLE);
//            image_navigation_pin.setVisibility(View.VISIBLE);
//        }
//        iconsVisible = !iconsVisible;
//    }
    public void onFilterClick(){
        nav_bar_activity_filter navBarActivityFilter = nav_bar_activity_filter.newInstance(onFilterClick);
        navBarActivityFilter.show(getSupportFragmentManager(), navBarActivityFilter.getTag());
    }
    @Override
    public void onNavigationClick() {
    }

    public void onFlagClick(View view) {
        image_close.setVisibility(View.VISIBLE);
        image_flag.setVisibility(View.GONE);
        image_brake.setVisibility(View.VISIBLE);


        image_speed_meter.setVisibility(View.VISIBLE);
        image_turn.setVisibility(View.VISIBLE);
        image_naviagtionPin.setVisibility(View.VISIBLE);

        mapZoom = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_map_zoom, (ViewGroup) getWindow().getDecorView(), false);

        ((ViewGroup) getWindow().getDecorView()).addView(mapZoom);
        mapZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapZoom.setVisibility(View.GONE);

            }
        });
        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_close.setVisibility(View.GONE);
                image_flag.setVisibility(View.VISIBLE);
                image_brake.setVisibility(View.GONE);


                image_speed_meter.setVisibility(View.GONE);
                image_turn.setVisibility(View.GONE);
                image_naviagtionPin.setVisibility(View.GONE);
            }
        });

    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // Hide the instruction when the user interacts with the map
//        instructionTextView.setVisibility(View.GONE);
//        return super.onTouchEvent(event);
//    }
}