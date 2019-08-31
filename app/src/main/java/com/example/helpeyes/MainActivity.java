package com.example.helpeyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainActivity extends Activity {

    /** Called when the activity is first created. */

    Button acelerometro;
    Button sensorLuz;
    Button proximidad;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acelerometro=(Button)findViewById(R.id.btnAcelerometro);
        sensorLuz=(Button)findViewById(R.id.btnSensorLuz);
        proximidad=(Button)findViewById(R.id.btnProximidad);

        acelerometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acelerometro=new Intent(MainActivity.this,Acelerometro.class);
                startActivity(acelerometro);
            }
        });

        sensorLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensorLuz=new Intent(MainActivity.this,SensorLuz.class);
                startActivity(sensorLuz);
            }
        });

        proximidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proximidad=new Intent(MainActivity.this,SensorProximidad.class);
                startActivity(proximidad);
            }
        });
    }


    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }


}