package com.company;

import java.util.Scanner;

import static com.company.Servidor.mostrarTexto;

public class Main {

    public static void main(String[] args)
    {
        Servidor s = new Servidor();
        Scanner sc = new Scanner(System.in);

        mostrarTexto("Escribe el puerto [5050 por defecto]: ");
        String puerto = sc.nextLine();
        if (puerto.length() <= 0) puerto = "5050";
        s.ejecutarConexion(Integer.parseInt(puerto));
        s.escribirDatos();
    }
}
