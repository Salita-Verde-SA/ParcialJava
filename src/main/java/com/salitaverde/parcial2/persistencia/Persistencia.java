package com.salitaverde.parcial2.persistencia;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.salitaverde.parcial2.Autor;
import com.salitaverde.parcial2.EditView.EditView;

/**
 *
 * @author Fabrizio Castillo
 */
public class Persistencia {

    public static final String BARRA = File.separator;

    public static final String UBICACION = System.getProperty("user.dir") + BARRA;

    public static final String UBICACION_ARCHIVO = UBICACION + "Datos.json";

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarJson(Autor nuevoAutor) {
        ArrayList<Autor> autores = new ArrayList<>();
        File archivo = new File(UBICACION_ARCHIVO);
        boolean archivoExiste = archivo.exists();

        autores = leer(autores, archivo, archivoExiste);

        for (Autor autor : autores) {
            if (autor.getDni() == nuevoAutor.getDni()) {
                String mensaje = "Ya existe un autor con el mismo DNI: " + nuevoAutor.getDni() + ".";
                System.err.println(mensaje);
                JOptionPane.showMessageDialog(
                        null,
                        mensaje,
                        "El autor ya existe",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        autores.add(nuevoAutor);

        try (Writer escritor = new FileWriter(archivo)) {
            // Convierte la lista de autores a JSON y la guarda en el archivo
            gson.toJson(autores, escritor);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        JOptionPane.showMessageDialog(
                null,
                "Se registró el autor correctamente.",
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void guardarJson(ArrayList<Autor> nuevoAutor) {
        ArrayList<Autor> autores = new ArrayList<>();
        File archivo = new File(UBICACION_ARCHIVO);
        boolean archivoExiste = archivo.exists();

        autores = leer(autores, archivo, archivoExiste);

        // Comprobar DNI repetido
        // Crea un HashSet para almacenar los DNIs y detectar duplicados
        java.util.HashSet<Integer> dnis = new java.util.HashSet<>();
        // Itera sobre la colección de autores llamada nuevoAutor
        for (Autor autor : nuevoAutor) {
            // Intenta agregar el DNI del autor al HashSet; si ya existe, retorna false
            if (!dnis.add(autor.getDni())) {
                // Prepara un mensaje indicando que hay un DNI duplicado
                String mensaje = "Hay autores duplicados con el mismo DNI: " + autor.getDni() + ".";
                // Muestra el mensaje de error en la consola
                System.err.println(mensaje);
                // Muestra un cuadro de diálogo de error al usuario
                JOptionPane.showMessageDialog(
                        null,
                        mensaje,
                        "Autores duplicados",
                        JOptionPane.ERROR_MESSAGE);
                // Sale del método para evitar continuar con datos duplicados
                return;
            }
        }
        // Limpia la lista de autores actual
        autores.clear();
        // Agrega todos los autores nuevos a la lista
        autores.addAll(nuevoAutor);

        try (Writer escritor = new FileWriter(archivo)) {
            // Convierte la lista de autores a JSON y la guarda en el archivo
            gson.toJson(autores, escritor);
            // Muestra un mensaje indicando que los datos fueron actualizados
            JOptionPane.showMessageDialog(
                    null,
                    "Datos actualizados correctamente.",
                    "Operación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            // Si ocurre un error al escribir el archivo, se muestra el mensaje de error en consola
            System.err.println(e.getMessage());
        }
    }
// Método que convierte la lista de autores del 
//archivo en una matriz de objetos para usar en tablas
    public static Object[][] obtenerArrayJson() {
        // Se crea una lista vacía de autores
        ArrayList<Autor> autores = new ArrayList<>();
        // Se accede al archivo donde están almacenados los autores
        File archivo = new File(UBICACION_ARCHIVO);
        // Se verifica si el archivo existe
        boolean archivoExiste = archivo.exists();
            // Se leen los datos del archivo y se actualiza la lista de autores
        autores = leer(autores, archivo, archivoExiste);
    // Se crea una matriz de tamaño [cantidad de autores][3 columnas: dni, nombre, pseudónimo]
        Object[][] array = new Object[autores.size()][3];
    // Se cargan los datos en la matriz
        for (int i = 0; i < autores.size(); i++) {
            Autor autor = autores.get(i);
            array[i][0] = autor.getDni();
            array[i][1] = autor.getNombre();
            array[i][2] = autor.getPseudonimo();
        }

        // array = autores.toArray();
            // Se devuelve la matriz con los datos
        return array;
    }
// Método privado que lee autores desde un archivo JSON
    private static ArrayList<Autor> leer(ArrayList<Autor> autores, File archivo, boolean archivoExiste) {
            // Si el archivo no existe, se intenta crear uno nuevo
        if (!archivoExiste) {
            System.out.println("El archivo no existe, se crea uno nuevo.");
            try {
                // Crea los directorios necesarios
                archivo.getParentFile().mkdir();
                // Crea el archivo vacío
                archivo.createNewFile();
            } catch (IOException e) {
                // Muestra un error si no se pudo crear el archivo
                System.err.println("No se pudo crear el archivo: " + e.getMessage());
            }
        }
        try (Reader lector = new FileReader(archivo)) {
            // Define el tipo de dato que se va a leer: una lista de autores
            Type listType = new TypeToken<List<Autor>>() {
            }.getType();
// Lee el contenido del archivo JSON y lo convierte en una lista de autores
            // Convierte el contenido del archivo JSON a una lista de autores
            ArrayList<Autor> json;
            json = gson.fromJson(lector, listType);
// Si el archivo está vacío o contiene null, devuelve la lista original (vacía)
            if (json == null) {
                return autores;
            } else {
           // Si la lectura fue exitosa, asigna la lista leída
                autores = json;
            }

        } catch (JsonSyntaxException e) {
            // Si el contenido del archivo no es válido JSON, se reinicia la lista
            System.out.println("El archivo no contiene una lista válida. Se inicializa una nueva.");
            autores = new ArrayList<>();
        } catch (IOException e) {
            // Si ocurre un error al leer el archivo, se muestra por consola
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
         // Devuelve la lista de autores, ya sea vacía o cargada
        return autores;

    }
}
