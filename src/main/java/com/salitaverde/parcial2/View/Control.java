package com.salitaverde.parcial2.View;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.salitaverde.parcial2.Autor;
import com.salitaverde.parcial2.persistencia.Persistencia;

/**
 *
 * @author Fabrizio Castillo
 */

public class Control {

    public static void guardar(JTextField dni, JTextField Nom, JTextField Pse) throws IOException {
        int DNI;

        try {
            DNI = Integer.parseInt(dni.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El DNI debe contener números únicamente.");
            return; // Salir del método si el DNI no es válido
        }
        System.out.println("DNI: " + dni.getText());

        String StNom = Nom.getText();
        if (StNom.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(null, "En nombre no puede contener números.");
        } else {
            System.out.println(StNom);
        }

        String pseu = Pse.getText();
        Autor a = new Autor(DNI, StNom, pseu);
       
        Persistencia.guardarJson(a);

    }

    public static void limpiar(JTextField dni, JTextField nom, JTextField pse) {
        dni.setText("");
        nom.setText("");
        pse.setText("");
    }

}
