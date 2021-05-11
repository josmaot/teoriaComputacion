/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppal;

/**
 *
 * @author jmola
 */
public class CGramatic {
    
    public String cadena;
    private char[] arrCadena;
    private int posicion;
    
    public CGramatic(){
        posicion = 0;
    }

    // DIGITOS-> DIGITO DIGITOS | DIGITO
    public boolean DIGITOS() {
        int p = posicion;

        // Evaluar digito continuo
        if (DIGITO()) {
            if (DIGITOS()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar digito simple
        if (DIGITO()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // DIGITO-> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    public boolean DIGITO() {
        int p = posicion;
        boolean found = false;
        char[] digitos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // Ver si el digito pertenece a los caracteres validos
        char cur = arrCadena[posicion++];
        for (char c : digitos) {
            if (c == cur) {
                found = true;
                break;
            }
        }

        if (!found) {
            posicion = p;
        }

        return found;
    }
    
    public boolean CADENA() {
        int p = posicion;
        boolean found = false;
        char[] abcd = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'
        , 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        // Ver si el digito pertenece a los caracteres validos
        char cur = arrCadena[posicion++];
        for (char c : abcd) {
            if (c == cur) {
                found = true;
                break;
            }
        }

        if (!found) {
            posicion = p;
        }

        return found;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getCadena() {
        return cadena;
    }
    
    public void setCadena(String cadena) {
        this.cadena = cadena + '\0';
    }

    
    public String getStatusDigito(){
        this.arrCadena = this.cadena.toCharArray();
        int len = getCadena().length() - 1;
        boolean res = DIGITOS();
        
        return (res && getPosicion() == len) ? "correcta" : "incorrecta";
    }
    
    public String getStatusCadena(){
        this.arrCadena = this.cadena.toCharArray();
        int len = getCadena().length() - 1;
        boolean res = CADENA();
        return (res && getPosicion() == len) ? "correcta" : "incorrecta";
    }


}
