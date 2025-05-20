package com.salitaverde.parcial2.Control;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.salitaverde.parcial2.Autor;
import com.salitaverde.parcial2.EditView.EditView;
import com.salitaverde.parcial2.persistencia.Persistencia;

/**
 *
 * @author Fabrizio Castillo
 */

public class Controlador {

    public static void guardar(JTextField dni, JTextField Nom, JTextField Pse) throws IOException {
        int DNI;

        try { // Intenta convertir el texto del campo de texto a un número entero
            DNI = Integer.parseInt(dni.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "El DNI debe contener números únicamente.",
                    "DNI inválido",
                    JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el DNI no es válido
        }
        System.out.println("DNI registrado: " + dni.getText());

        String StNom = Nom.getText();
        if (StNom.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(
                    null,
                    "El nombre no puede contener números.",
                    "Nombre inválido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("Nombre registrado: " + StNom);

        String pseu = Pse.getText();
        Autor a = new Autor(DNI, StNom, pseu);

        Persistencia.guardarJson(a);
        

    }

    public static void limpiar(JTextField dni, JTextField nom, JTextField pse) {
        dni.setText("");
        nom.setText("");
        pse.setText("");
    }

    public static void obtenerDatos(javax.swing.table.DefaultTableModel model) {
        Object[][] datos = Persistencia.obtenerArrayJson();
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }

    public static void guardarDesdeTabla(EditView vistaEdicion, javax.swing.table.DefaultTableModel model) {
        java.util.ArrayList<Autor> lista = new java.util.ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
            int dni = Integer.parseInt(model.getValueAt(i, 0).toString());
            String nombre = model.getValueAt(i, 1).toString();
            String pseudonimo = model.getValueAt(i, 2).toString();
            if (nombre.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(
                    null,
                    "El nombre no puede contener números.",
                    "Nombre inválido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
            lista.add(new Autor(dni, nombre, pseudonimo));
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(
                        vistaEdicion, 
                        "Algún dato de los ingresados es inválido o nulo.", 
                        "No se pudieron guardar los datos",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }
        Persistencia.guardarJson(lista);
        vistaEdicion.dispose();
    }

}
