package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            String hostLocal = "localhost";
            int puertoServidor = 9012;

            //Conectamos con el servidor
            System.out.println("PROGRAMA CLIENTE INICIANDO......");
            Socket CLIENTE = new Socket(hostLocal, puertoServidor);

            //CREAMOS FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(CLIENTE.getInputStream());

            //EL SERVIDOR NOS ENVÍA UN MENSAJE
            System.out.println("BIENVENIDOS AL SERVIDOR\n" + "NÚMERO DE CLIENTES CONECTADOS: " + flujoEntrada.readInt());

            //CERRAMOS STREAMS Y SOCKETS
            flujoEntrada.close();

            CLIENTE.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

