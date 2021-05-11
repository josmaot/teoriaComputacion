package ppal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*
    PENDIENTE:
    
    S->CONTENIDO
    CONTENIDO->INCLUDE SEPARADOR DEFINITION SEPARADOR METODOS
    INCLUDE-> #include MODULE | #include MODULE SEPARADOR INCLUDE | EMPTY
    MODULE-> < MODULE_NOMBRE >
    MODULE_NOMBRE-> CADENA .h
    DEFINITION-> METODO_ENCABEZADO FIN_INSTRUCCION
    METODO_ENCABEZADO-> TYPE SEPARADOR METODO_NOMBRE ARGS
    METODO_NOMBRE-> CADENA
    FIN_INSTRUCCION-> ;
    ARGS-> ( ARG ) | ()
    ARG-> VARIABLE | VARIABLE COMA ARG
    COMA-> ,
    METODOS-> METODO | EMPTY
    METODO-> METODO_ENCABEZADO { CODIGO }
    CODIGO-> VARIABLE CODIGO | ESTRUCTURA_IF CODIGO | INSTRUCCION CODIGO | EMPTY
    ESTRUCTURA_IF->IF(CONDICION) { CODIGO } | IF(CONDICION) { CODIGO } ELSE { CODIGO }
    CONDICION->CADENA COMPARADOR CADENA
    COMPARADOR-> < | > | <= | >= | != | ==
    INSTRUCCION-> ASIGNAR_VALOR | LLAMAR_METODO
    ASIGNAR_VALOR-> CADENA = VALOR FIN_INSTRUCCION
    LLAMAR_METODO-> CADENA();
     */
    public static void main(String args[]) {
        // Inicializar y pedir cadena a evaluar
        String cadena = "123";
        CGramatic gram = new CGramatic();
        gram.setCadena(cadena);
        System.out.println("Cadena a evaluar: " + gram.getCadena());

        // Evaluar gramatica
        boolean res = gram.NUMERO();

        // Imprimir resultado
        System.out.println("Tu cadena es: " + gram.getStatusCadena(res));
    }
}
