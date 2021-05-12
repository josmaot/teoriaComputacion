package ppal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-* PROYECTO TERCER PARCIAL *-*-*-*-*-*-*-*-*-*-*-*\n");
            System.out.println("___________________________________________________________________________");
            System.out.println("OPCIONES\n");
            System.out.println("1)Informacion del proyecto\n");
            System.out.println("2)Comprobar Cadena\n");
            System.out.println("3)Salir del programa\n");
            System.out.println("Tu Opcion:\n");
            String opcionmenu = in.nextLine();

            opciones(opcionmenu);
        }

    }

    public static void opciones(String opcionmenu) {
        switch (opcionmenu) {
            case "1":
                System.out.println("----------Gramatica Lenguaje C-----------\n");
                System.out.println("__________________________________________\n");
                System.out.println("..............INTEGRANTES................\n");
                System.out.println("--> Bernardo Alfonso Borunda Jaquez -------> 314788\n");
                System.out.println("--> Jose Manuel Olague Trevizo  -----------> 298054\n");
                System.out.println("--> Marcela Alejandra Barrera Rodriguez ---> 311005\n");
                System.out.println("--> Roberto Ivan Nava Esparza -------------> 320832\n");
                System.out.println("__________________________________________\n");
                System.out.println(".................MATERIA..................\n");
                System.out.println("--> Teoria de la computacion\n");
                System.out.println("................DOCENTE..................\n");
                System.out.println("--> Mario Andres Cuevas Gutierrez\n");
                System.out.println("................FECHA..................\n");
                System.out.println("-->Entregado a : 13 de Mayo del 2021\n");

                break;

            case "2":
                System.out.println("Cadena a evaluar:");
                System.out.println("---------------------------------------------------------------------");
                Scanner scanner = new Scanner(Main.class.getResourceAsStream("Codigo.txt"));
                String cadena = new String();
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    System.out.println(linea);
                    cadena += linea;
                }
                System.out.println("---------------------------------------------------------------------");

                // Inicializar clase
                CGramatic gram = new CGramatic();
                gram.setCadena(cadena);

                // Evaluar gramatica
                boolean res = gram.CODIGO();

                // Imprimir resultado
                System.out.println("Tu cadena es: " + gram.getStatusCadena(res) + "\n");

                break;
            case "3":
                System.exit(0);
                break;
        }
    }
}
