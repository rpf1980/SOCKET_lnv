package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor
{
    //Método estático
    public static void lanzaServidor()
    {
        try
        {
            ServerSocket server = new ServerSocket(9012);
            System.out.println("Servidor iniciado....");
            int contadorClientes = 1;

            while (true)
            {
                Socket cliente = server.accept(); //Aceptamos clientes
                HiloServidor hilo = new HiloServidor(cliente, contadorClientes);
                contadorClientes++;
                hilo.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Clase interna
    public static class HiloServidor extends Thread
    {
        //Atributo
        Socket socket = null;
        int numeroConexiones;

        //Constructor
        HiloServidor(Socket socket, int numeroConexiones)
        {
            try
            {
                this.socket = socket;
                this.numeroConexiones = numeroConexiones;
                //numeroConexiones++;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void run()
        {
            // Tarea a realizar con el cliente
            try
            {
                //Mostramos al grupo nombres de usuarios conectados menos el nuestro
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                flujoSalida.writeInt(numeroConexiones);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
