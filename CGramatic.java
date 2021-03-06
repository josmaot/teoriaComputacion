package ppal;

public class CGramatic {

    public String cadena;
    public char[] arrCadena;
    public int posicion;

    public CGramatic() {
        posicion = 0;
    }

    // CODIGO-> INSTRUCCION CODIGO | ESTRUCTURA_IF CODIGO | ESTRUCTURA_IF CODIGO | ESTRUCTURA_WHILE CODIGO | SEPARADOR
    public boolean CODIGO() {
        int p = posicion;

        // INSTRUCCION CODIGO
        if (SEPARADOR() && (INSTRUCCION() || ESTRUCTURA_IF() || ESTRUCTURA_WHILE())) {
            if (CODIGO()) {
                return true;
            }
        }
        posicion = p;

        return SEPARADOR();
    }

    public boolean FUNCIONES_RESERVADAS() {
        String[] functions = {"printf\0", "scanf\0", "puts\0"};
        int entro = 0;

        for (String foo : functions) {
            for (char letra : foo.toCharArray()) {
                if (letra == '\0') {
                    return true;
                }
                if (letra == arrCadena[posicion]) {
                    entro++;
                    posicion++;
                } else {
                    if (entro > 0) {
                        posicion = posicion - entro;
                        entro = 0;
                    }
                    break;
                }
            }

        }

        return false;
    }

    // INSTRUCCION-> VARIABLE; | ASIGNAR_VALOR; | LLAMAR_METODO; | FUNCIONES_RESERVADAS; | return SEPARADOR VALOR;
    public boolean INSTRUCCION() {

        if (FUNCIONES_RESERVADAS()) {//Revisa algunas funciones basicas 
            if (ARGS()) {
                if (arrCadena[posicion] == ';') {
                    posicion++;
                    return true;
                }
            }
        }

        int p = posicion;

        // VARIABLE;
        if (VARIABLE()) {
            char cur = arrCadena[posicion++];
            if (cur == ';') {
                return true;
            }
        }
        posicion = p;

        // ASIGNAR_VALOR;
        if (ASIGNAR_VALOR()) {
            char cur = arrCadena[posicion++];
            if (cur == ';') {
                return true;
            }
        }
        posicion = p;

        // LLAMAR_METODO;
        if (LLAMAR_METODO()) {
            char cur = arrCadena[posicion++];
            if (cur == ';') {
                return true;
            }
        }
        posicion = p;

        // return SEPARADOR VALOR;
        char cur = arrCadena[posicion++];
        if (cur == 'r') {
            cur = arrCadena[posicion++];
            if (cur == 'e') {
                cur = arrCadena[posicion++];
                if (cur == 't') {
                    cur = arrCadena[posicion++];
                    if (cur == 'u') {
                        cur = arrCadena[posicion++];
                        if (cur == 'r') {
                            cur = arrCadena[posicion++];
                            if (cur == 'n' && SEPARADOR() && VALOR()) {
                                cur = arrCadena[posicion++];
                                if (cur == ';') {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        posicion = p;

        return false;
    }

    // LLAMAR_METODO-> CADENA ARGS | CADENA . CADENA ARGS
    public boolean LLAMAR_METODO() {
        int p = posicion;

        // ADENA ARGS
        if (CADENA() && ARGS()) {
            return true;
        }
        posicion = p;

        // CADENA . CADENA ARGS
        if (CADENA()) {
            char cur = arrCadena[posicion++];
            if (cur == '.' && CADENA() && ARGS()) {
                return true;
            }
        }
        posicion = p;

        return false;
    }

    // ARGS-> ( ARG ) | ()
    public boolean ARGS() {
        int p = posicion;

        // Evaluar sin argumentos
        char cur = arrCadena[posicion++];
        if (cur == '(' && SEPARADOR()) {
            cur = arrCadena[posicion++];
            if (cur == ')') {
                return true;
            }
        }
        posicion = p;

        // Evaluar con argumentos
        cur = arrCadena[posicion++];
        if (cur == '(' && SEPARADOR() && ARG()) {
            cur = arrCadena[posicion++];
            if (cur == ')') {
                return true;
            }
        }
        posicion = p;

        return false;
    }

    // ARG-> VARIABLE | VARIABLE,ARG
    public boolean ARG() {
        int p = posicion;

        // Evaluar varios argumentos
        if (VARIABLE()) {
            char cur = arrCadena[posicion++];
            if (cur == ',' && SEPARADOR() && ARG()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar argumento simple
        if (VARIABLE()) {
            return true;
        }
        posicion = p;

        // Evaluar varios sin tipo de dato o digitos
        if (CADENA()) {
            char cur = arrCadena[posicion++];
            if (cur == ',' && SEPARADOR() && ARG()) {
                return true;
            }
        }
        posicion = p;

        // Evaluar argumento simple sin tipo de dato o digito
        if (CADENA()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // ASIGNAR_VALOR-> CADENA = VALOR
    public boolean ASIGNAR_VALOR() {
        int p = posicion;

        // CADENA = VALOR
        if (CADENA() && SEPARADOR()) {
            char cur = arrCadena[posicion++];
            if (cur == '=') {
                if (SEPARADOR() && VALOR()) {
                    return true;
                }
            }
        }
        posicion = p;

        return false;
    }

    // TODO: Soportar espacios entre IF" "(CONDICION)" "BLOQUE" "ELSE" "BLOQUE
    // ESTRUCTURA_IF->SECCION_IF | SECCION_IF ELSE BLOQUE
    public boolean ESTRUCTURA_IF() {
        int p = posicion;

        // If con else
        if (SECCION_IF() && SEPARADOR() && ELSE() && SEPARADOR() && BLOQUE()) {
            return true;
        }
        posicion = p;

        // If solo
        if (SECCION_IF()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // ESTRUCTURA_WHILE->SECCION_WHILE BLOQUE 
    public boolean ESTRUCTURA_WHILE() {
        int p = posicion;

        // while
        if (SECCION_WHILE()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // SECCION_IF->IF(CONDICION)BLOQUE
    public boolean SECCION_IF() {
        int p = posicion;

        // IF(CONDICION) BLOQUE
        if (IF() && SEPARADOR()) {
            char cur = arrCadena[posicion++];
            if (cur == '(') {
                if (CONDICION()) {
                    cur = arrCadena[posicion++];
                    if (cur == ')') {
                        if (SEPARADOR() && BLOQUE()) {
                            return true;
                        }
                    }
                }
            }
        }
        posicion = p;

        return false;
    }

    // SECCION_WHILE->WHILE(CONDICION) BLOQUE
    public boolean SECCION_WHILE() {
        int p = posicion;
        // while(CONDICION) BLOQUE
        if (WHILE() && SEPARADOR()) {
            char cur = arrCadena[posicion++];
            if (cur == '(') {
                if (CONDICION()) {
                    cur = arrCadena[posicion++];
                    if (cur == ')') {
                        if (SEPARADOR() && BLOQUE()) {
                            return true;
                        }
                    }
                }
            }
        }
        posicion = p;

        return false;
    }

    // BLOQUE->{CODIGO}
    public boolean BLOQUE() {
        int p = posicion;

        // {CODIGO}
        char cur = arrCadena[posicion++];
        if (cur == '{') {
            if (CODIGO()) {
                cur = arrCadena[posicion++];
                if (cur == '}') {
                    return true;
                }
            }
        }
        posicion = p;

        return false;
    }

    // IF-> if
    public boolean IF() {
        int p = posicion;

        char cur = arrCadena[posicion++];
        if (cur == 'i') {
            cur = arrCadena[posicion++];
            if (cur == 'f') {
                return true;
            }
        }
        posicion = p;

        return false;
    }

    public boolean WHILE() {
        int p = posicion;
        char cur = arrCadena[posicion++];

        if (cur == 'w') {
            cur = arrCadena[posicion++];
            if (cur == 'h') {
                cur = arrCadena[posicion++];
                if (cur == 'i') {
                    cur = arrCadena[posicion++];
                    if (cur == 'l') {
                        cur = arrCadena[posicion++];
                        if (cur == 'e') {
                            return true;
                        }
                    }
                }
            }
        }
        posicion = p;

        return false;
    }

    // ELSE-> else
    public boolean ELSE() {
        int p = posicion;

        char cur = arrCadena[posicion++];
        if (cur == 'e') {
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
        posicion = p;

        return false;
    }

    // CONDICION-> VALOR COMPARADOR VALOR
    public boolean CONDICION() {
        int p = posicion;

        // Condicion sin separadores "1>1"
        if (VALOR() && COMPARADOR() && VALOR()) {
            return true;
        }
        posicion = p;

        // Condicion con separadores "1 > 1"
        if (VALOR() && SEPARADOR() && COMPARADOR() && SEPARADOR() && VALOR()) {
            return true;
        }
        posicion = p;

        // Condicion separador izquierdo "1 >1"
        if (VALOR() && SEPARADOR() && COMPARADOR() && VALOR()) {
            return true;
        }
        posicion = p;

        // Condicion separador derecho "1> 1"
        if (VALOR() && COMPARADOR() && SEPARADOR() && VALOR()) {
            return true;
        }
        posicion = p;

        return false;
    }

    // COMPARADOR-> < | > | <= | >= | != | ==
    public boolean COMPARADOR() {
        int p = posicion;

        // Evaluar >=
        char cur = arrCadena[posicion++];
        if (cur == '>') {
            cur = arrCadena[posicion++];
            if (cur == '=') {
                return true;
            }
        }
        posicion = p;

        // Evaluar <=
        cur = arrCadena[posicion++];
        if (cur == '<') {
            cur = arrCadena[posicion++];
            if (cur == '=') {
                return true;
            }
        }
        posicion = p;

        // Evaluar !=
        cur = arrCadena[posicion++];
        if (cur == '!') {
            cur = arrCadena[posicion++];
            if (cur == '=') {
                return true;
            }
        }
        posicion = p;

        // Evaluar ==
        cur = arrCadena[posicion++];
        if (cur == '=') {
            cur = arrCadena[posicion++];
            if (cur == '=') {
                return true;
            }
        }
        posicion = p;

        // Evaluar >
        cur = arrCadena[posicion++];
        if (cur == '>' || cur == '<') {
            return true;
        }
        posicion = p;

        return false;
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
        if (!(arrCadena[posicion] == '"')) {
            posicion = p;
        } else {
            return true;
        }
        return false;
    }

    // CARACTER-> a | b | c | ... | z
    public boolean CARACTER() {
        int p = posicion;
        boolean found = false;
        char[] caracteres = {' ', '"', '&', '%', ':', '*', '+',
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

        return true;
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

    // VALOR-> null | false | true | "CADENA" | NUMERO | CADENA
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

        // Evaluar "cadena"
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

        // Evaluar numero y cadena
        if (NUMERO() || CADENA()) {
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

        // Evaluar void
        cur = arrCadena[posicion++];
        if (cur == 'v') {
            cur = arrCadena[posicion++];
            if (cur == 'o') {
                cur = arrCadena[posicion++];
                if (cur == 'i') {
                    cur = arrCadena[posicion++];
                    if (cur == 'd') {
                        return true;
                    }
                }
            }
        }
        posicion = p;

        // Evaluar void
        cur = arrCadena[posicion++];
        if (cur == 's') {
            cur = arrCadena[posicion++];
            if (cur == 't') {
                cur = arrCadena[posicion++];
                if (cur == 'r') {
                    cur = arrCadena[posicion++];
                    if (cur == 'i') {
                        cur = arrCadena[posicion++];
                        if (cur == 'n') {
                            cur = arrCadena[posicion++];
                            if (cur == 'g') {
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

    // VARIABLE-> TIPO_DATO SEPARADOR CADENA | TIPO_DATO SEPARADOR CADENA SEPARADOR = SEPARADOR VALOR
    public boolean VARIABLE() {
        int p = posicion;

        // TIPO_DATO SEPARADOR CADENA SEPARADOR = SEPARADOR VALOR
        if (TIPO_DATO() && SEPARADOR() && CADENA() && SEPARADOR()) {
            char cur = arrCadena[posicion++];
            if (cur == '=' && SEPARADOR() && VALOR()) {
                return true;
            }
        }
        posicion = p;

        // TIPO_DATO SEPARADOR CADENA
        if (TIPO_DATO() && SEPARADOR() && CADENA()) {
            return true;
        }
        posicion = p;

        return false;
    }

    //MODULE_NOMBRE-> CADENA .h
    public boolean MODULE_NOMBRE() {

        String[] libs = {"assert\0", "complex\0", "ctype\0", "errno\0", "fenv\0", "float\0",
            "inttypes\0", "iso646\0", "limits\0", "locale\0", "math\0", "setjmp\0",
            "signal\0", "starg\0", "stdbool\0", "stdint\0", "stddef\0", "stdio\0",
            "stdlib\0", "string\0", "tgmath\0", "time\0", "wchar\0", "wctype\0"};
        int entro = 0;

        for (String lib : libs) {
            for (char letra : lib.toCharArray()) {
                if (letra == '\0') {
                    return true;
                }
                if (letra == arrCadena[posicion]) {
                    entro++;
                    posicion++;
                } else {
                    if (entro > 0) {
                        posicion = posicion - entro;
                        entro = 0;
                    }
                    break;
                }
            }

        }

        return false;
    }

    //MODULE-> < MODULE_NOMBRE.h >
    public boolean MODULE() {
        if (arrCadena[posicion] == '<') {
            posicion++;
            if (MODULE_NOMBRE()) {
                if (arrCadena[posicion] == '.') {
                    posicion++;
                    if (arrCadena[posicion] == 'h') {
                        posicion++;
                        if (arrCadena[posicion] == '>') {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    

    //METODO_ENCABEZADO-> TYPE SEPARADOR CADENA ARGS
    public boolean METODO_ENCABEZADO() {
        if (TIPO_DATO()) {
            if (SEPARADOR()) {
                if (CADENA()) {
                    if (ARGS()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //DEFINICION-> METODO_ENCABEZADO;
    public boolean DEFINICION() {
        int p = posicion;
        if (METODO_ENCABEZADO()) {
            if (arrCadena[posicion] == ';') {
                p = posicion;
                if (SEPARADOR()) {
                    posicion++;
                    if (DEFINICION()) {
                        return true;
                    }
                } else {
                    posicion++;
                    return true;
                }
            } else if (arrCadena[posicion] == '{') {
                posicion = p;
                return true;
            }
        }
        return false;
    }

    //METODO-> METODO_ENCABEZADO BLOQUE
    public boolean METODO() {
        if (METODO_ENCABEZADO()) {
            if (BLOQUE()) {
                return true;
            }
        }
        return false;
    }

    //METODOS-> METODO | EMPTY
    public boolean METODOS() {
        if (METODO()) {
            if (posicion < getCadena().length() - 1) {
                METODOS();
                return true;
            } else {
                return true;
            }
        }
        return false;
    }
    
    //INCLUDE-> #include MODULE | #include MODULE SEPARADOR INCLUDE | EMPTY
    public boolean INCLUDE() {
        if (arrCadena[posicion] == '#') {
            posicion++;
            if (arrCadena[posicion] == 'i') {
                posicion++;
                if (arrCadena[posicion] == 'n') {
                    posicion++;
                    if (arrCadena[posicion] == 'c') {
                        posicion++;
                        if (arrCadena[posicion] == 'l') {
                            posicion++;
                            if (arrCadena[posicion] == 'u') {
                                posicion++;
                                if (arrCadena[posicion] == 'd') {
                                    posicion++;
                                    if (arrCadena[posicion] == 'e') {
                                        posicion++;
                                        if (MODULE()) {
                                            posicion++;
                                            if (posicion < getCadena().length() - 1 && (arrCadena[posicion] == '#' || arrCadena[posicion] == '\n')) {
                                                if (arrCadena[posicion] == '\n') {
                                                    posicion++;
                                                }
                                                INCLUDE();
                                                return true;

                                            } else {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }


    //CONTENIDO->INCLUDE SEPARADOR DEFINITION SEPARADOR METODOS
    public boolean CONTENIDO() {
        if (INCLUDE()) {
            if (SEPARADOR()) {
                if (DEFINICION()) {
                    if (SEPARADOR()) {
                        if (METODOS()) {
                            return true;
                        }
                    }
                } else //Si no hay definicion de funciones
                if (METODOS()) { //Verifica los metodos
                    return true;
                }
            }
        }
        return false;
    }

    public boolean GRAMATICA_C() {
        if (CONTENIDO()) {
            return true;
        }
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
