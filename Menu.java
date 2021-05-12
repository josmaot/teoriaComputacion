
package ppal;


public class Menu {
    
    static String path = "src/ppal/Codigo3.txt";
    static Archivo archivo = new Archivo(path);
    static CGramatic gram = new CGramatic();
    static boolean res;
    static String opcionMenu;

    public Menu() {
    }

    public static String getOpcionMenu() {
        return opcionMenu;
    }

    public static void setOpcionMenu(String opcionMenu) {
        Menu.opcionMenu = opcionMenu;
    }

    public static void menu() {
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-* PROYECTO TERCER PARCIAL *-*-*-*-*-*-*-*-*-*-*-*\n");
        System.out.println("___________________________________________________________________________");
        System.out.println("OPCIONES\n");
        System.out.println("1)Informacion del proyecto\n");
        System.out.println("2)Comprobar Cadena\n");
        System.out.println("3)Salir del programa\n");
        System.out.println("Tu Opcion:\n");
    }

    public static void opciones() {
        switch (opcionMenu) {
            case "1":
                informacion();
                break;

            case "2":
                evaluarCodigo();
                break;

            case "3":
                System.exit(0);
                break;
        }
    }

    static void informacion() {
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

    }

    static void evaluarCodigo() {
        System.out.println("Codigo a evaluado:");
        System.out.println("---------------------------------------------------------------------");
        //Obtener codigo del archivo.txt a la variable cadena
        archivo.getCodigoToCadena();
        System.out.println("---------------------------------------------------------------------");
        
        //Enviar la variable cadena a la gramatica
        gram.setCadena(archivo.getCadena());
        
        //Evaluar codigo con GRAMATICA_C
        res = gram.GRAMATICA_C();

        // Imprimir resultado obteniendo el estatus de la cadena segun la gramatica
        System.out.println("Tu gramatica es: " + gram.getStatusCadena(res) + "\n");
    }
}
