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

public class SensorLuz extends Activity implements SensorEventListener{

    TextView intensidadLuz;



    String intensidad;

    SensorManager sm;
    Sensor sensorLuz;
    SensorEventListener lightEventListener;

    Cliente c=new Cliente(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luz);

        intensidadLuz=(TextView)findViewById(R.id.txtLuz);


    }


    @Override
    protected void onPause() {
        super.onPause();

        //sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sm.unregisterListener(this, sensorLuz);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLuz =sm.getDefaultSensor(TYPE_LIGHT);
        if(sensorLuz==null){
            Toast.makeText(this,"no hay sensor de luz",Toast.LENGTH_SHORT).show();
            finish();
        }

        sm.registerListener(this, sensorLuz,sm.SENSOR_DELAY_FASTEST);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Cliente c=new Cliente(this);

        float intensidad=sensorEvent.values[0];
        intensidadLuz.setText("intensidad de luz:  "+intensidad);

        if(intensidad<20){
            c.execute("d");
        }
        else if(intensidad<100 && intensidad>20){
            c.execute("b");
        }
        else if(intensidad>500){
            c.execute("l");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
