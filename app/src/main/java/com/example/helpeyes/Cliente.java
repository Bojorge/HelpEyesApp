package com.example.helpeyes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static android.net.wifi.WifiConfiguration.Status.strings;

public class Cliente extends AsyncTask<String,Void,Void> {
    Socket cliente;

    PrintWriter writer;
    String mensaje;

    String recibido;
    String texto;

    Context c;
    Handler h=new Handler();


    DataInputStream dataIn;
    InputStreamReader inputReader;
    BufferedReader buffRead;

    char[] cbuf = new char[512];

//cd Escritorio/HelpEyes/HelpEyesServer

    Cliente(Context c){
        this.c=c;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    protected Void doInBackground(String... strings) {

        try {

            mensaje=strings[0];
            String ip = "192.168.8.100";
            cliente = new Socket(ip, 1234);
            writer=new PrintWriter(cliente.getOutputStream());
            writer.write(mensaje);
            writer.flush();


            dataIn=new DataInputStream(cliente.getInputStream());
            inputReader=new InputStreamReader(dataIn);
            buffRead=new BufferedReader(inputReader);
            recibido=buffRead.toString();

            /*
            inputReader=new InputStreamReader(cliente.getInputStream(),"UTF8");
            inputReader.read(cbuf);
            for (char c : cbuf) {
                recibido += c;
                if (c == 00) {
                    break;
                }
            }

             */
            setTexto("gsdfgs");



            //buffRead=new BufferedReader(inputReader);
            //recibido=buffRead.readLine();
            //setTexto(recibido);
            //Toast.makeText(MainActivity,recibido,Toast.LENGTH_SHORT).show();

            dataIn.close();
            cliente.close();




        } catch (IOException e)
        {
            e.printStackTrace();
        }


        return null;
    }

}