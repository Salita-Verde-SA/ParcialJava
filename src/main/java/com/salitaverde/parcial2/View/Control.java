/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salitaverde.parcial2.View;

import com.salitaverde.parcial2.*;
import com.salitaverde.parcial2.persistencia.Persistencia;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Fabrizio Castillo
 */
public class Control extends Interfaz {

    public static void guardar(JTextField dni, JTextField Nom, JTextField Pse) {
        int DNI;
        
        try {
            DNI = Integer.parseInt(dni.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El DNI debe contener números únicamente.");
        } finally {
            DNI = Integer.parseInt(dni.getText());
        }

        String StNom = Nom.getText();
        if (StNom.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(null, "En nombre no puede contener números.");
        } else {
            System.out.println(StNom);
        }

        String pseu = Pse.getText();
        Autor autor = new Autor(DNI, StNom, pseu);
        
        Persistencia.guardarJson(autor);
    }

    public static void limpiar(JTextField dni, JTextField nom, JTextField pse) {
        dni.setText("");
        nom.setText("");
        pse.setText("");
    }

}
