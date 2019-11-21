package com.company;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    //DESDE EL CLIENTE: ( PARA LO QUE PIDE EL EJERCICIO )
    // -
    public static void main(String[] args)
    {
        try
        {
            InetAddress destino = InetAddress.getLocalHost();
            int port = 12345; //Puerto al que envío el datagrama
            byte[] mensaje = new byte[1024];

            String Saludo = "Enviando Saludos !!";
            mensaje = Saludo.getBytes(); //codigo String a bytes

            //CONSTRUYO EL DATAGRAMA A ENVIAR
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
            DatagramSocket socket = new DatagramSocket(); //Puerto local
            System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
            System.out.println("Host destino                  : " + destino.getHostName());
            System.out.println("IP Destino                    : " + destino.getHostAddress());
            System.out.println("Puerto local del socket       : " + socket.getLocalPort());
            System.out.println("Puerto al que envío           : " + envio.getPort());

            //ENVIO DATAGRAMA
            socket.send(envio);
            socket.close(); //cierro el socket
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
