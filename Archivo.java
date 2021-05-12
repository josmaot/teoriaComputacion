package ppal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Archivo {

    static File archivo = null;
    static FileReader fr = null;
    static BufferedReader br = null;
    static String linea = null;
    static String[] lines;
    static String cadena = "";
    static String src = "";

    public Archivo(String path) {
        this.src = path;
    }

    public void getCodigoToCadena() {
        try {
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                cadena += linea;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public String getCadena() {
        return cadena;
    }

}
