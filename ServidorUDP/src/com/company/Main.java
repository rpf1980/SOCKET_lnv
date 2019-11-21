package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {

    //AQUÍ EN EL SERVIDOR: ( PARA LO QUE PIDE EL EJERCICIO )
    // - Debemos leer el fichero JSON para el ejercicio ( como binario )
    // - Se envía como binario al clientes
    // - Debemos usar un send desde aquí para enviar los datos
    public static void main(String[] args)
    {
        byte[] bufer = new byte[1024]; //Buffer para recibir el diagrama

        try
        {
            //ASOCIAMOS EL PUERTO AL SOCKET
            DatagramSocket socket = new DatagramSocket(9009);

            //ESPERANDO DATAGRAMA
            System.out.println("Esperando datagrama.... ");
            DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
            socket.receive(recibo); //Recibo datagrama
            int byetsRec = recibo.getLength(); //Obtenemos numero de bytes

            //Parseo
            String paquetes = new String(recibo.getData()); //Obtengo strings

            //VISUALIZO INFORMACIÓN
            System.out.println("Número de Bytes recibidos : " + byetsRec);
            System.out.println("Contenido del Paquete     : " + paquetes.trim());
            System.out.println("IP de origen              : " + recibo.getAddress().getHostAddress());
            System.out.println("Puerto destino del mensaje: " + socket.getLocalPort());
            socket.close(); //cierro el socket
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
