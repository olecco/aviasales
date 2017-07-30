package com.olecco.android.aviasales.ui.map;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olecco.android.aviasales.R;
import com.olecco.android.aviasales.model.CityPair;
import com.olecco.android.aviasales.model.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class ShowMapFragment extends MapFragment implements OnMapReadyCallback {

    public static final String TAG = "MapFragment";

    private static final int PATH_POINTS_COUNT = 40;

    private GoogleMap map;
    private LatLng fromLocation;
    private LatLng toLocation;
    private LatLngBounds locationBounds;
    private int pathColor;
    private PlaneAnimator planeAnimator;
    private Marker planeMarker;

    public static ShowMapFragment newInstance(CityPair cityPair) {
        ShowMapFragment fragment = new ShowMapFragment();
        fragment.setArguments(CitiesMarshaller.toBundle(cityPair));
        return fragment;
    }

    private final PlaneAnimator.PlaneAnimatorListener planeAnimatorListener =
            new PlaneAnimator.PlaneAnimatorListener() {
        @Override
        public void onPlaneAnimationStart() {
            planeMarker = map.addMarker(
                    new MarkerOptions()
                            .position(fromLocation)
                            .rotation(0.0f)
                            .anchor(0.5f, 0.5f)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.plane)));
        }

        @Override
        public void onPlaneAnimationStop() {
            if (planeMarker != null) {
                planeMarker.remove();
            }
        }

        @Override
        public void onPlanePositionUpdate(LatLng position, float angle) {
            planeMarker.setPosition(position);
            planeMarker.setRotation(angle);
        }
    };

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

        pathColor = getResources().getColor(R.color.pathColor);

        fromLocation = coordinatesToLatLng(CitiesMarshaller.getFromCoordinates(getArguments()));
        toLocation = coordinatesToLatLng(CitiesMarshaller.getToCoordinates(getArguments()));

        double minLat = Math.min(fromLocation.latitude, toLocation.latitude);
        double minLon = Math.min(fromLocation.longitude, toLocation.longitude);

        double maxLat = Math.max(fromLocation.latitude, toLocation.latitude);
        double maxLon = Math.max(fromLocation.longitude, toLocation.longitude);

        LatLng southWest = new LatLng(minLat, minLon);
        LatLng northEast = new LatLng(maxLat, maxLon);

        locationBounds = new LatLngBounds(southWest, northEast);

        planeAnimator = new PlaneAnimator();
        planeAnimator.setPlaneAnimatorListener(planeAnimatorListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        planeAnimator.stop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setRotateGesturesEnabled(false);

        map.addMarker(new MarkerOptions().position(fromLocation).title("FROM"));
        map.addMarker(new MarkerOptions().position(toLocation).title("TO"));

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(locationBounds, 100));

        preparePlanePath();
    }

    private void preparePlanePath() {
        Path path = new Path();
        drawPlanePath(path);

        PathMeasure pathMeasure = new PathMeasure(path, false);

        float pathStep =  pathMeasure.getLength() / PATH_POINTS_COUNT;
        float pointRadius = getPathPointRadius();

        List<LatLng> pathPoints = new ArrayList<>(PATH_POINTS_COUNT);
        float[] angles = new float[PATH_POINTS_COUNT + 1];

        Point point = new Point();
        float[] pathPoint = new float[2];
        float[] tan = new float[2];
        for (int i = 0; i <= PATH_POINTS_COUNT; i++) {

            pathMeasure.getPosTan(pathStep * i, pathPoint, tan);

            point.x = (int) pathPoint[0];
            point.y = (int) pathPoint[1];

            LatLng loc = getLocationFromPoint(point);

            pathPoints.add(loc);
            angles[i] = (float)(Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

            map.addCircle(new CircleOptions()
                    .center(loc)
                    .fillColor(pathColor)
                    .strokeColor(pathColor)
                    .strokeWidth(0.0f)
                    .radius(pointRadius));
        }

        planeAnimator.setPathPoints(pathPoints);
        planeAnimator.setAngles(angles);
        planeAnimator.start();
    }

    private void drawPlanePath(Path path) {
        Point startPoint = getPointFromLocation(fromLocation);
        Point endPoint = getPointFromLocation(toLocation);

        path.moveTo(startPoint.x, startPoint.y);

        float xDiff = Math.abs(startPoint.x - endPoint.x);
        float yDiff = Math.abs(startPoint.y - endPoint.y);

        if (xDiff <= yDiff) {
            path.cubicTo(
                    startPoint.x <= endPoint.x ? startPoint.x + xDiff : startPoint.x - xDiff,
                    startPoint.y,
                    startPoint.x <= endPoint.x ? endPoint.x - xDiff : endPoint.x + xDiff,
                    endPoint.y,
                    endPoint.x,
                    endPoint.y);
        } else {
            path.cubicTo(
                    startPoint.x,
                    startPoint.y <= endPoint.y ? startPoint.y + yDiff : startPoint.y - yDiff,
                    endPoint.x,
                    startPoint.y <= endPoint.y ? endPoint.y - yDiff : endPoint.y + yDiff,
                    endPoint.x,
                    endPoint.y);
        }
    }

    private float getPathPointRadius() {
        float[] dist = new float[1];
        Location.distanceBetween(fromLocation.latitude, fromLocation.longitude,
                toLocation.latitude, toLocation.longitude, dist);
        return dist[0] / PATH_POINTS_COUNT / 4;
    }

    private LatLng getLocationFromPoint(Point point) {
        return map.getProjection().fromScreenLocation(point);
    }

    private Point getPointFromLocation(LatLng latLng) {
        return map.getProjection().toScreenLocation(latLng);
    }

    private static LatLng coordinatesToLatLng(Coordinates coordinates) {
        return new LatLng(coordinates.getLat(), coordinates.getLon());
    }

}
