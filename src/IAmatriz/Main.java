package IAmatriz;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        com.formdev.flatlaf.FlatLightLaf.setup();
        UIManager.put("Button.arc", 10);
        //UIManager.put("Table.gridColor", new java.awt.Color(0, 0, 0));
        Interfaz frame = new Interfaz();
    }
}
