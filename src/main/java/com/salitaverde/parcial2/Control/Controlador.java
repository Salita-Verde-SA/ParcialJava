package com.salitaverde.parcial2.Control;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.salitaverde.parcial2.Autor;
import com.salitaverde.parcial2.EditView.EditView;
import com.salitaverde.parcial2.View.View;
import static com.salitaverde.parcial2.View.View.editView;
import com.salitaverde.parcial2.persistencia.Persistencia;
import javax.swing.JFrame;

/**
 *
 * @author Fabrizio Castillo
 */
public class Controlador {

    public static void mostrarVistaEditar(View vista, EditView editView) {
        // Se obtiene el modelo de la tabla de autores de la ventana
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) editView.getAutores()
                .getModel();
        //Se cargan los datos en el modelo de la tabla usando un método del controlador
        Controlador.obtenerDatos(model);
        //Se posiciona la ventana de edición en el centro de la ventana actual (this)
        editView.setLocationRelativeTo(vista);
        //Se hace visible la ventana de edición
        editView.setVisible(true);
    }
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
        if (comprobarTextoVacio(StNom)) {
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
    
    private static boolean comprobarTextoVacio(String nombre) {
        if (nombre.matches(".*\\d.*") || nombre.equals("")) { // Verifica el nombre
            JOptionPane.showMessageDialog(
                    null,
                    "El nombre no puede contener números o estar vacío.",
                    "Nombre inválido",
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
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

        // Se eliminan los datos  si es que la tabla se abre otra vez
        model.setRowCount(0);
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

                if (comprobarTextoVacio(nombre)) { // Verificar el nombre
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
