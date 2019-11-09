package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Servidor
{
    //Método estático
    public static void lanzaServidor()
    {
        try
        {
            ServerSocket server = new ServerSocket(9009);
            ExecutorService pool = Executors.newFixedThreadPool(100);
            int numeroClienteConectado = 0;

            while (true)
            {
                Socket soc = server.accept(); //Aceptamos clientes
                numeroClienteConectado++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Clase interna
    public static class HiloServidor implements Runnable
    {
        //Atributo
        Socket socket;
        int indice; // Conteo de clientes conectados

        //Constructor
        HiloServidor(Socket socket, int indice)
        {
            this.socket = socket;
            this.indice = 0;
        }

        public void run()
        {
            //String nombreUsuario = "";
            String linea = "";
            String comandoSalir = "/salir";
            try
            {
                //Lee el servidor
                Scanner sc = new Scanner(socket.getInputStream());
                //Escribe el servidor
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                //Mostramos al grupo número de cliente conectado
                imprime("Número de clientes: " + indice, pw);
                indice++;

                while (true)
                {
                    //Seguimos leyendo líneas
                    linea = sc.nextLine();
                    //Imprime linea con la función
                    imprime(linea, pw);
                    if (linea.equals(comandoSalir)) //Si linea == /salir... mensaje usuario sale
                    {
                        //pw.println("Usuario: " + nombreUsuario + " desconectado"); FUNCION
                        imprime("Cliente: " + indice + " desconectado", pw);
                        break;
                    }
                }
            } catch (Exception e)
            {
               < e.printStackTrace();>
            }
        }
    }

    //Métodos aparte
    public static void imprime(String texto, PrintWriter pw)
    {
        for (int i = 0; i < listaWriter.size(); i++)
        {
            if (listaWriter.get(i) != pw)
            {
                listaWriter.get(i).println(texto);
            }
        }
    }
}
