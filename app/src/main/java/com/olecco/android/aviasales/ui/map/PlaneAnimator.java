package com.olecco.android.aviasales.ui.map;


import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PlaneAnimator {

    public interface PlaneAnimatorListener {
        void onPlaneAnimationStart();
        void onPlaneAnimationStop();
        void onPlanePositionUpdate(LatLng position, float angle);
    }

    private static final int ANIMATION_STEP_DURATION = 500;

    private PlaneAnimatorListener planeAnimatorListener;

    private List<LatLng> pathPoints = new ArrayList<>();
    private float[] angles;

    private Handler animationHandler = new Handler();
    private int animationStep = -1;
    private Interpolator interpolator = new LinearInterpolator();
    private long startTime;

    private final Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {
            long elapsedTime = SystemClock.uptimeMillis() - startTime;
            double fraction = interpolator.getInterpolation((float) elapsedTime / ANIMATION_STEP_DURATION);

            double lat = fraction * getAnimEnd().latitude + (1 - fraction) * getAnimStart().latitude;
            double lng = fraction * getAnimEnd().longitude + (1 - fraction) * getAnimStart().longitude;

            float angle = (float) (fraction * getAngleEnd() + (1 - fraction) * getAngleStart());

            notifyPlanePositionUpdate(new LatLng(lat, lng), angle);

            if (fraction < 1.0) {
                continueAnimation();
            } else {
                nextAnimationStep();
            }
        }
    };

    public void setPlaneAnimatorListener(PlaneAnimatorListener planeAnimatorListener) {
        this.planeAnimatorListener = planeAnimatorListener;
    }

    public void setPathPoints(List<LatLng> pathPoints) {
        this.pathPoints = new ArrayList<>(pathPoints);
    }

    public void setAngles(float[] angles) {
        this.angles = new float[angles.length];
        System.arraycopy(angles, 0, this.angles, 0, angles.length);
    }

    public void start() {
        stop();
        notifyPlaneAnimationStart();
        nextAnimationStep();
    }

    public void stop() {
        if (isAnimating()) {
            notifyPlaneAnimationStop();
        }
        animationStep = -1;
        animationHandler.removeCallbacks(animationRunnable);
    }

    public boolean isAnimating() {
        return animationStep > -1;
    }

    private void continueAnimation() {
        animationHandler.postDelayed(animationRunnable, 16);
    }

    private void nextAnimationStep() {
        animationStep++;
        if (animationStep == pathPoints.size() - 1) {
            stop();
        } else {
            startTime = SystemClock.uptimeMillis();
            continueAnimation();
        }
    }

    private LatLng getAnimStart() {
        return pathPoints.get(animationStep);
    }

    private LatLng getAnimEnd() {
        return pathPoints.get(animationStep + 1);
    }

    private float getAngleStart() {
        return angles[animationStep];
    }

    private float getAngleEnd() {
        return angles[animationStep + 1];
    }

    private void notifyPlaneAnimationStart() {
        if (planeAnimatorListener != null) {
            planeAnimatorListener.onPlaneAnimationStart();
        }
    }

    private void notifyPlaneAnimationStop() {
        if (planeAnimatorListener != null) {
            planeAnimatorListener.onPlaneAnimationStop();
        }
    }

    private void notifyPlanePositionUpdate(LatLng position, float angle) {
        if (planeAnimatorListener != null) {
            planeAnimatorListener.onPlanePositionUpdate(position, angle);
        }
    }

}
