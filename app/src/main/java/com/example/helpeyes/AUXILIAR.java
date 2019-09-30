package com.example.helpeyes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class AUXILIAR extends AppCompatActivity  implements SensorEventListener{

    private TextView salida;
    private TextView intensidadLuz;

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

    TextView tv_steps;
    boolean running=false;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.auxiliar);

        salida = (TextView) findViewById(R.id.salida);
        intensidadLuz = (TextView) findViewById(R.id.txtLuz);

        x = (TextView)findViewById(R.id.xID);
        y = (TextView)findViewById(R.id.yID);
        z = (TextView)findViewById(R.id.zID);

        tv_steps=(TextView) findViewById(R.id.tv_steps);

        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.
                getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor: listaSensores) {

            log(sensor.getName());

        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {

            Sensor orientationSensor = listaSensores.get(0);

            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this,"no hay acelerometro",Toast.LENGTH_SHORT).show();
            //finish();
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if (!listaSensores.isEmpty()) {

            Sensor acelerometerSensor = listaSensores.get(0);

            sensorManager.registerListener(this, acelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this,"no hay sensor de luz",Toast.LENGTH_SHORT).show();
            //finish();
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_STEP_COUNTER);

        if (!listaSensores.isEmpty()) {

            Sensor orientationSensor = listaSensores.get(0);

            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this,"no hay contador de pasos",Toast.LENGTH_SHORT).show();
            //finish();
        }


    }

    private void log(String string) {

        salida.append(string + "\n");

    }

    @Override

    public void onSensorChanged(SensorEvent evento) {
        Cliente c=new Cliente(this);
        Cliente c2=new Cliente(this);
        Cliente c3=new Cliente(this);

        //Cada sensor puede provocar que un thread principal pase por aquí

        //así que sincronizamos el acceso (se verá más adelante)

        synchronized (this) {

            switch(evento.sensor.getType()) {

                case Sensor.TYPE_ACCELEROMETER:

                    this.x.setText("X = "+evento.values[SensorManager.DATA_X]);
                    setXtxt(Float.toString(evento.values[SensorManager.DATA_X]));

                    this.y.setText("Y = "+evento.values[SensorManager.DATA_Y]);
                    setYtxt(Float.toString(evento.values[SensorManager.DATA_Y]));

                    this.z.setText("Z = "+evento.values[SensorManager.DATA_Z]);
                    setZtxt(Float.toString(evento.values[SensorManager.DATA_Z]));


                    if(evento.values[SensorManager.DATA_X]<-4){
                        c.execute("L");
                    }
                    if(evento.values[SensorManager.DATA_X]>4){
                        c.execute("R");
                    }
                    if(evento.values[SensorManager.DATA_Y]<7 && evento.values[SensorManager.DATA_Z]>7){
                        c.execute("e");
                    }
                    if(evento.values[SensorManager.DATA_Y]<7 && evento.values[SensorManager.DATA_Z]<-6){
                        c.execute("f");
                    }

                case Sensor.TYPE_LIGHT:

                    float intensidad=evento.values[0];
                    intensidadLuz.setText("intensidad de luz:  "+intensidad);

                    if(intensidad<20){
                        c2.execute("d");
                    }
                    else if(intensidad<100 && intensidad>20){
                        c2.execute("b");
                    }
                    else if(intensidad>500){
                        c2.execute("l");
                    }

                case Sensor.TYPE_STEP_COUNTER:
                    if(running){
                        tv_steps.setText(String.valueOf(evento.values[0]));
                        c3.execute("r");
                    }


            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
