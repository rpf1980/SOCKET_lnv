package com.company;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorChat
{
    //Atributos estáticos
    static List<PrintWriter> listaWriter = new ArrayList<>();

    //Método estático
    public static void lanzaServidor()
    {
        try
        {
            ServerSocket server = new ServerSocket(9012);
            ExecutorService pool = Executors.newFixedThreadPool(10);

            while (true)
            {
                Socket soc = server.accept(); //Aceptamos clientes
                pool.execute(new HiloServidor(soc)); //Lanzamos hilos del servidor (hilos por clientes conectados)
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

        //Constructor
        HiloServidor(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            String nombreUsuario = "";
            String linea = "";
            String comandoSalir = "/salir";
            try
            {
                //Lee el servidor
                Scanner sc = new Scanner(socket.getInputStream());
                //Escribe el servidor
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                listaWriter.add(pw);

                //Lee primera línea (nombre de usuario)
                nombreUsuario = sc.nextLine();

                //Mostramos al grupo nombres de usuarios conectados menos el nuestro
                imprime("Cliente conectado: " + nombreUsuario, pw);

                while (true)
                {
                    //Seguimos leyendo líneas
                    linea = sc.nextLine();
                    //Imprime linea con la función
                    imprime(linea, pw);
                    if (linea.equals(comandoSalir)) //Si linea == /salir... mensaje usuario sale
                    {
                        //pw.println("Usuario: " + nombreUsuario + " desconectado"); FUNCION
                        imprime("Usuario: " + nombreUsuario + " desconectado", pw);
                        break;
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
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
