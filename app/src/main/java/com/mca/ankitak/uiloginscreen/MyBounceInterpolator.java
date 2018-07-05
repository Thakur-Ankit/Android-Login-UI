package com.mca.ankitak.uiloginscreen;

/**
 * Created by ANKIT on 14-Apr-18.
 */

class MyBounceInterpolator implements android.view.animation.Interpolator {

    private double mAmplitude = 1.3;
    private double mFrequency = 24.5;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
