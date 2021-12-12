package com.numad21fa.vital;

import androidx.appcompat.app.AppCompatActivity;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class ShakyActivity extends AppCompatActivity {
    TextView text_recommended_food;
    List<String> recommendation_list = new ArrayList<>(){};
    // TODO: randomly pick food from recommendation list when shaking

    // Sensor variables
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private double accelCurrVal;
    private double accelPrevVal;
    private double accelThreshold = 0.05;

    // Instantiate a new sensorEventListener to catch motions on 3 directions
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            // Simple way to aggregate 3 directions
            accelCurrVal = Math.sqrt(x * x + y * y + z * z);
//            Log.i("Shaky-accelCurrVal", String.valueOf(accelCurrVal));


            double accelDiffVal = Math.abs(accelCurrVal - accelPrevVal);
            Log.i("Shaky-accelDiffVal", String.valueOf(accelDiffVal));


            // TODO call to pop up recommendation
            if (accelDiffVal > accelThreshold) {
                Toast.makeText(ShakyActivity.this, "ShakyShaky detected, new food is recommended", Toast.LENGTH_LONG).show();
                Log.i("Shaky-detected", "ShakyShaky detected, new food is recommended");
            }

            // update Previous Accelerometer value
            accelPrevVal = accelCurrVal;
//            Log.i("Shaky-accelPrevVal", String.valueOf(accelPrevVal));


            // update on TextView
            text_recommended_food.setText("Current Accelerometer = " + Math.round(accelCurrVal));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaky);

        text_recommended_food = findViewById(R.id.text_recommended_food);

        // Constructor for Accelerometer Sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Log.i("Shaky-mAccelerometer-status", String.valueOf(mAccelerometer));
    }

    // Overwrite the Listener here
    // https://developer.android.com/reference/android/hardware/SensorManager
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListener);
    }

}