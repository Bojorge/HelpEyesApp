package com.example.helpeyes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends AsyncTask<String,Void,Void> {
    Socket cliente;
    PrintWriter writer;
    String mensaje;
    Context c;
    Handler h=new Handler();
    Cliente(Context c){this.c=c;}




    @Override
    protected Void doInBackground(String... strings) {

        try {
            mensaje=strings[0];
            String ip = "192.168.8.102";
            cliente = new Socket(ip, 1234);
            writer=new PrintWriter(cliente.getOutputStream());
            writer.write(mensaje);
            writer.flush();
            cliente.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}