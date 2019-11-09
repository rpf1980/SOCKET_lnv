package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    //**SERVIDOR**
    public static void main(String[] args) throws IOException
    {
        InputStream entrada = null;     //Preparamos el buffer para leer los que llega del cliente
        Socket clienteConectado = null; //Objeto Socket para aceptar las conexiones
        try
        {
            //Ponemos el puerto por donde ESCUCHA el SERVIDOR
            ServerSocket servidor = new ServerSocket(9009);
            System.out.println("Esperando al cliente .......");
            clienteConectado = servidor.accept();

            //CREAMOS FLUJO DE ENTRADA DEL CLIENTE
            entrada = clienteConectado.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            //EL CLIENTE NOS ENVÍA UN MENSAJE
            System.out.println("Recibiendo del cliente: " + flujoEntrada.readUTF());

            //CREAMOS FLUJO DE SALIDA HACIA EL CLIENTE
            OutputStream salida = null;
            salida = clienteConectado.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            //ENVIAMOS SALUDO AL CLIENTE
            flujoSalida.writeUTF("Saludos al cliente del servidor");

            //CERRAMOS STREAMS Y SOCKETS
            entrada.close();
            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }
}

