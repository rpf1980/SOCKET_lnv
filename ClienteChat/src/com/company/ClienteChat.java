package com.company;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat
{
    //Método estático
    public static void conectar(String ip, String usuario)
    {
        String mensaje = "";
        String comandoSalir = "/salir";

        try
        {
            Socket soc = new Socket(ip, 9012);

            //El cliente lanza el hilo
            HiloRecibir hr = new HiloRecibir(soc);
            Thread th = new Thread(hr);
            th.start();

            //Enviamos nombre usuario al servidor
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);
            System.out.print("USUARIO: "); //Pedimos nombre usuario
            mensaje = sc.nextLine();
            pw.write(mensaje); // Y lo aparte mandamos al servidor

            mensaje = sc.nextLine();
            while(!mensaje.equals(comandoSalir))
            {
                pw.println(mensaje);
                mensaje = sc.nextLine();
            }

            pw.println(comandoSalir); //Mandamos comando salir para que el servidor actue en consecuencia
            hr.stop(); //Paramos el hilo
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //Clase interna
    public static class HiloRecibir implements Runnable
    {
        //Atributos
        Socket socket;
        private volatile boolean salir = false;

        //Constructor
        public HiloRecibir(Socket socket)
        {
            this.socket = socket;
        }

        //Métodos
        public void run()
        {
            try
            {
                while(this.salir)
                {
                    //Leemos líneas que llegan por el socket
                    Scanner sc = new Scanner(socket.getInputStream());

                    //Mostramos mensaje por consola
                    System.out.println(sc.nextLine());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void stop()
        {
            this.salir = true;
        }

    }
}