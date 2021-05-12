package ppal;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        int opc = 0;
        Scanner in = new Scanner(System.in);
        Menu m = new Menu();

        while (opc != 3) {
            m.menu();
            m.setOpcionMenu(in.nextLine());
            m.opciones();
            opc = Integer.parseInt(m.getOpcionMenu());
        }

    }
}
