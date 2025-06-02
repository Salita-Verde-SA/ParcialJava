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
// Método para guardar un autor ingresado desde campos de texto
    public static void guardar(JTextField dni, JTextField Nom, JTextField Pse) {
        int DNI;

        try { // Intenta convertir el texto del campo de texto a un número entero
            DNI = Integer.parseInt(dni.getText());
        } catch (NumberFormatException e) {
            // Si falla, muestra mensaje y corta la ejecución
            JOptionPane.showMessageDialog(
                    null,
                    "El DNI debe contener números únicamente.",
                    "DNI inválido",
                    JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si el DNI no es válido
        }
        System.out.println("DNI registrado: " + dni.getText());

        String StNom = Nom.getText();
        // Verifica que el nombre no contenga números
        if (StNom.matches(".*\\d.*")) { // Verifica si hay algún dígito
            JOptionPane.showMessageDialog(
                    null,
                    "El nombre no puede contener números.",
                    "Nombre inválido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("Nombre registrado: " + StNom);
// Toma el seudónimo del campo
        String pseu = Pse.getText();
        // Crea un objeto Autor con los datos validados
        Autor a = new Autor(DNI, StNom, pseu);
// Llama al método para guardar el autor en un archivo JSON
        Persistencia.guardarJson(a);

    }
// Método para limpiar los campos de texto
    public static void limpiar(JTextField dni, JTextField nom, JTextField pse) {
        dni.setText("");
        nom.setText("");
        pse.setText("");
    }
 // Método para cargar los autores del archivo JSON en una tabla
    public static void obtenerDatos(javax.swing.table.DefaultTableModel model) {
        Object[][] datos = Persistencia.obtenerArrayJson();// Obtiene los datos en forma de arreglo
        for (Object[] fila : datos) {
            model.addRow(fila);// Agrega cada fila al modelo de la tabla
        }
    }
 // Método para guardar los datos modificados desde una tabla editable
    public static void guardarDesdeTabla(EditView vistaEdicion, javax.swing.table.DefaultTableModel model) {
        java.util.ArrayList<Autor> lista = new java.util.ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                // Lee los valores de cada fila de la tabla
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
                // Si todo es válido, se crea el Autor y se agrega a la lista
                lista.add(new Autor(dni, nombre, pseudonimo));
            } catch (NullPointerException e) {
                             // Si algún dato está vacío o inválido, muestra error y termina
                JOptionPane.showMessageDialog(
                        vistaEdicion,
                        "Algún dato de los ingresados es inválido o nulo.",
                        "No se pudieron guardar los datos",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        }// Guarda toda la lista de autores en el archivo JSON
        
        Persistencia.guardarJson(lista);
        // Cierra la ventana de edición
        vistaEdicion.dispose();
    }

}
