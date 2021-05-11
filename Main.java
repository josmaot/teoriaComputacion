package ppal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author jmola
 */
public class Main {

    /*      VARIABLE-> TIPO_DATO SEPARADOR CADENA ; | TIPO_DATO SEPARADOR CADENA = VALOR ;
            TIPO_DATO-> char | int | short | long
            SEPARADOR-> " " SEPARADOR | \t SEPARADOR | \n SEPARADOR | EMPTY
            CADENA-> CARACTER CADENA | CARACTER
            CARACTER-> a | b | c | ... | z
            VALOR-> null | false | true | "CADENA" | NUMERO
            NUMERO-> -DIGITOS FRACCION | DIGITOS FRACCION
            FRACCION-> .DIGITOS | EMPTY}
     */
    
    public static void main(String args[]) {

        CGramatic gramC = new CGramatic();
        
        String cadenaDigito = "134234982";
        String cadenaString = "ABC";
        
        gramC.setCadena(cadenaDigito);
        String estatusDigito = gramC.getStatusDigito();
        System.out.println("Tu cadena de digitos es: " + estatusDigito);
        
        gramC = new CGramatic();
        
        gramC.setCadena(cadenaString);
        String estatusCadena = gramC.getStatusCadena();
        System.out.println("Tu cadena String es: " + estatusCadena);
        
        
    }
}
