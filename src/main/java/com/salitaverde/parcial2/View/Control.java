/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salitaverde.parcial2.View;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Fabrizio Castillo
 */
public class Control extends Interfaz{
    public static void guardar(JTextField dni, JTextField Nom, JTextField Pse) {
        
        try {
            int DNI = Integer.parseInt(dni.getText());
            System.out.println(DNI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ser un numero");
        }
        String StNom = Nom.getText();
        if (StNom.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(null, "No debe contener números");
        } else {
           System.out.println(StNom);
        }
    }
    
}
