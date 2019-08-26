package com.example.helpeyes;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static android.hardware.Sensor.TYPE_LIGHT;

public class SensorLuz extends Activity {

    TextView intensidadLuz;

    SensorManager sm;
    Sensor sensorLuz;
    SensorEventListener lightEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luz);

        intensidadLuz=(TextView)findViewById(R.id.txtLuz);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLuz =sm.getDefaultSensor(TYPE_LIGHT);

        if(sensorLuz==null){
            Toast.makeText(this,"no hay sensor de luz",Toast.LENGTH_SHORT).show();
            finish();
        }

        lightEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float intensidad=sensorEvent.values[0];
                intensidadLuz.setText("intensidad de luz:  "+intensidad);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(lightEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(lightEventListener, sensorLuz,sm.SENSOR_DELAY_FASTEST);
    }


}
