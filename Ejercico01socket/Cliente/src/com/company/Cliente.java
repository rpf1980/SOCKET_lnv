package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente
{
    //Método stático del CLIENTE
    public static void conectarConServidor()
    {
        //Atributos
        //String hostLocal = "localhost";
        //String hostLocal = "192.168.1.35";
        //int puertoServidor = 9012;

        try
        {
            //Conectamos con el servidor
            System.out.println("PROGRAMA client INICIANDO......");
            Socket client = new Socket("172.16.0.158", 9009);
            //CREAMOS FLUJO DE SALIDA AL SERVIDOR
            DataOutputStream flujoSalida = new DataOutputStream(client.getOutputStream());

            //ENVIAMOS UN SALUDO AL SERVIDOR
            flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL client");

            //CREAMOS FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(client.getInputStream());

            //EL SERVIDOR NOS ENVÍA UN MENSAJE
            System.out.println("Recibiendo el mensaje del servidor: " + flujoEntrada.readUTF());

            //CERRAMOS STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            client.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
