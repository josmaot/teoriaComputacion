package ppal;

public class CGramatic {

    public String cadena;
    public char[] arrCadena;
    public int posicion;

    public CGramatic() {
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

    // CADENA-> CARACTER CADENA | CARACTER
    public boolean CADENA() {
        int p = posicion;

        // Evaluar cadena continuo
        if (CARACTER()) {
            if (CADENA()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar caracter simple
        if (CARACTER()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // CARACTER-> a | b | c | ... | z
    public boolean CARACTER() {
        int p = posicion;
        boolean found = false;
        char[] caracteres = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Ver si el caracter pertenece a los caracteres validos
        char cur = arrCadena[posicion++];
        for (char c : caracteres) {
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

    // SEPARADOR-> " " SEPARADOR | \t SEPARADOR | \n SEPARADOR | EMPTY
    public boolean SEPARADOR() {
        int p = posicion;

        // Evaluar cadena continuo
        char cur = arrCadena[posicion++];
        if (cur == ' ' || cur == '\n' || cur == '\t') {
            if (SEPARADOR()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar caracter simple
        cur = arrCadena[posicion++];
        if (cur == ' ' || cur == '\n' || cur == '\t') {
            return true;
        }
        posicion = p;

        return false;
    }

    // FRACCION-> .DIGITOS
    public boolean FRACCION() {
        int p = posicion;

        // Evaluar fraccion
        char cur = arrCadena[posicion++];
        if (cur == '.') {
            if (DIGITOS()) {
                return true;
            }
        }
        posicion = p;

        return false;
    }

    // NUMERO-> -DIGITOS FRACCION | -DIGITOS | DIGITOS | DIGITOS FRACCION
    public boolean NUMERO() {
        int p = posicion;

        // Evaluar numero positivo con fraccion
        if (DIGITOS()) {
            if (FRACCION()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar numero positivo
        if (DIGITOS()) {
            return true;
        }
        posicion = p;

        // Evaluar numero negativo con fraccion
        char cur = arrCadena[posicion++];
        if (cur == '-') {
            if (DIGITOS()) {
                if (FRACCION()) {
                    return true;
                }
            }
        }
        posicion = p;

        // Evaluar numero negativo
        cur = arrCadena[posicion++];
        if (cur == '-') {
            if (DIGITOS()) {
                return true;
            }
        }
        posicion = p;

        return false;
    }

    // VALOR-> null | false | true | "CADENA" | NUMERO
    public boolean VALOR() {
        int p = posicion;

        // Evaluar null
        char cur = arrCadena[posicion++];
        if (cur == 'n') {
            cur = arrCadena[posicion++];
            if (cur == 'u') {
                cur = arrCadena[posicion++];
                if (cur == 'l') {
                    cur = arrCadena[posicion++];
                    if (cur == 'l') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // Evaluar true
        cur = arrCadena[posicion++];
        if (cur == 't') {
            cur = arrCadena[posicion++];
            if (cur == 'r') {
                cur = arrCadena[posicion++];
                if (cur == 'u') {
                    cur = arrCadena[posicion++];
                    if (cur == 'e') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // Evaluar false
        cur = arrCadena[posicion++];
        if (cur == 'f') {
            cur = arrCadena[posicion++];
            if (cur == 'a') {
                cur = arrCadena[posicion++];
                if (cur == 'l') {
                    cur = arrCadena[posicion++];
                    if (cur == 's') {
                        cur = arrCadena[posicion++];
                        if (cur == 'e') {
                            return true;
                        }
                    }
                }
            }
        }
        posicion = p;

        // Evaluar cadena
        cur = arrCadena[posicion++];
        if (cur == '"') {
            if (CADENA()) {
                cur = arrCadena[posicion++];
                if (cur == '"') {
                    return true;
                }
            }
        }
        posicion = p;

        // Evaluar numero
        if (NUMERO()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // TIPO_DATO-> char | int | short | long
    public boolean TIPO_DATO() {
        int p = posicion;

        // Evaluar short
        char cur = arrCadena[posicion++];
        if (cur == 's') {
            cur = arrCadena[posicion++];
            if (cur == 'h') {
                cur = arrCadena[posicion++];
                if (cur == 'o') {
                    cur = arrCadena[posicion++];
                    if (cur == 'r') {
                        cur = arrCadena[posicion++];
                        if (cur == 't') {
                            return true;
                        }
                    }
                }
            }
        }
        posicion = p;

        // Evaluar char
        cur = arrCadena[posicion++];
        if (cur == 'c') {
            cur = arrCadena[posicion++];
            if (cur == 'h') {
                cur = arrCadena[posicion++];
                if (cur == 'a') {
                    cur = arrCadena[posicion++];
                    if (cur == 'r') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // Evaluar long
        cur = arrCadena[posicion++];
        if (cur == 'l') {
            cur = arrCadena[posicion++];
            if (cur == 'o') {
                cur = arrCadena[posicion++];
                if (cur == 'n') {
                    cur = arrCadena[posicion++];
                    if (cur == 'g') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // Evaluar int
        cur = arrCadena[posicion++];
        if (cur == 'i') {
            cur = arrCadena[posicion++];
            if (cur == 'n') {
                cur = arrCadena[posicion++];
                if (cur == 't') {
                    return true;
                }
            }
        }
        posicion = p;

        return false;
    }

    // VARIABLE-> TIPO_DATO SEPARADOR CADENA ; | TIPO_DATO SEPARADOR CADENA = VALOR ;
    public boolean VARIABLE() {
        int p = posicion;

        // Evaluar variable sencilla
        if (TIPO_DATO()) {
            if (SEPARADOR()) {
                if (CADENA()) {
                    char cur = arrCadena[posicion++];
                    if (cur == ';') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // TIPO_DATO SEPARADOR CADENA = VALOR ;
        // Evaluar variable con asignacion
        if (TIPO_DATO()) {
            if (SEPARADOR()) {
                if (CADENA()) {
                    char cur = arrCadena[posicion++];
                    if (cur == '=') {
                        if (VALOR()) {
                            cur = arrCadena[posicion++];
                            if (cur == ';') {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        posicion = p;

        return false;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena + '\0';
        this.arrCadena = this.cadena.toCharArray();
    }

    public String getStatusCadena(boolean res) {
        this.arrCadena = this.cadena.toCharArray();
        int len = getCadena().length() - 1;
        return (res && getPosicion() == len) ? "correcta" : "incorrecta";
    }
}
