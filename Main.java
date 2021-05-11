package ppal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
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
        boolean res = gram.INCLUDE();

        // Imprimir resultado
        System.out.println("Tu cadena es: " + gram.getStatusCadena(res) + "\n");
    }
}
