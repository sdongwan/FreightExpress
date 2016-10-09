package com.highspace.hs.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Administrator on 2016/6/1.
 */


public class MyOrientationListener implements SensorEventListener {

    private SensorManager mSensorManager;
    private Context mContext;
    private Sensor mSensor;

    private float mLastX;

    private OnOrientationListener mOnOrientationListener;

    public MyOrientationListener(Context context) {
        mContext = context;

    }

    public interface OnOrientationListener {
        public void onOrientationChange(float x);
    }


    public void setOnOrientationListener(OnOrientationListener mOnOrientationListener) {
        this.mOnOrientationListener = mOnOrientationListener;
    }

    public void start() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float x = event.values[SensorManager.DATA_X];
            if (Math.abs(mLastX - x) > 1.0f) {
                if (mOnOrientationListener != null) {
                    mOnOrientationListener.onOrientationChange(x);
                }
            }
            mLastX = x;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
