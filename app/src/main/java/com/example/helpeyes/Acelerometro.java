package com.example.helpeyes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import static android.os.SystemClock.sleep;

public class Acelerometro extends Activity implements SensorEventListener {

    TextView x,y,z;
    String Xtxt, Ytxt, Ztxt;

    private Sensor mAccelerometer;

    public String getXtxt() {
        return Xtxt;
    }

    public void setXtxt(String xtxt) {
        Xtxt = xtxt;
    }

    public String getYtxt() {
        return Ytxt;
    }

    public void setYtxt(String ytxt) {
        Ytxt = ytxt;
    }

    public String getZtxt() {
        return Ztxt;
    }

    public void setZtxt(String ztxt) {
        Ztxt = ztxt;
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_acelerometro);

        x = (TextView)findViewById(R.id.xID);

        y = (TextView)findViewById(R.id.yID);

        z = (TextView)findViewById(R.id.zID);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    protected void onResume()

    {

        super.onResume();

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (sensors.size() > 0) //dispositivo android tiene acelerometro

        {

            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);

        }

    }

    protected void onPause()

    {

        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.unregisterListener(this, mAccelerometer);

        super.onPause();

    }

    protected void onStop()

    {

        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.unregisterListener(this, mAccelerometer);

        super.onStop();
    }

    @Override

    public void onSensorChanged(SensorEvent event) {
        Cliente c=new Cliente(this);

        this.x.setText("X = "+event.values[SensorManager.DATA_X]);
        setXtxt(Float.toString(event.values[SensorManager.DATA_X]));

        this.y.setText("Y = "+event.values[SensorManager.DATA_Y]);
        setYtxt(Float.toString(event.values[SensorManager.DATA_Y]));

        this.z.setText("Z = "+event.values[SensorManager.DATA_Z]);
        setZtxt(Float.toString(event.values[SensorManager.DATA_Z]));


        if(event.values[SensorManager.DATA_X]<-4){
            c.execute("incL");
        }
        if(event.values[SensorManager.DATA_X]>4){
            c.execute("incR");
        }
        if(event.values[SensorManager.DATA_Y]<7 && event.values[SensorManager.DATA_Z]>7){
            c.execute("esp");
        }
        if(event.values[SensorManager.DATA_Y]<7 && event.values[SensorManager.DATA_Z]<-6){
            c.execute("frte");
        }


    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
