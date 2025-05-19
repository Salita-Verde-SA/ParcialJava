/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Fabrizio Castillo
 */
public class Persistencia {

    public static final String BARRA = File.separator;

    public static final String UBICACION = System.getProperty("user.dir") + BARRA;

    public static final String UBICACION_ARCHIVO = UBICACION + "Datos.json" + BARRA;

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarJson(Autor nuevoAutor) {
        ArrayList<Autor> autores = new ArrayList<>();
        File archivo = new File(UBICACION_ARCHIVO);
        boolean archivoExiste = archivo.exists();
        
        autores = leer(autores, archivo, archivoExiste);
        System.out.println(autores.size());
        
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
    }

    public static Object[][] obtenerArrayJson() {
        ArrayList<Autor> autores = new ArrayList<>();
        File archivo = new File(UBICACION_ARCHIVO);
        boolean archivoExiste = archivo.exists();
        autores = leer(autores, archivo, archivoExiste);

        Object[][] array = new Object[autores.size()][3];

        for (int i = 0; i < autores.size(); i++) {
            Autor autor = autores.get(i);
            array[i][0] = autor.getDni();
            array[i][1] = autor.getNombre();
            array[i][2] = autor.getPseudonimo();
        }

        // array = autores.toArray();

        return array;
    }

    private static ArrayList<Autor> leer(ArrayList<Autor> autores, File archivo, boolean archivoExiste) {
        if (archivoExiste) {
            try (Reader lector = new FileReader(archivo)) {
                // Define el tipo de dato que se va a leer: una lista de autores
                Type listType = new TypeToken<List<Autor>>() {
                }.getType();

                // Convierte el contenido del archivo JSON a una lista de autores
                autores = gson.fromJson(lector, listType);
            } catch (JsonSyntaxException e) {
                System.out.println("El archivo no contiene una lista válida. Se inicializa una nueva.");
                autores = new ArrayList<>();
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe, se crea uno nuevo.");
            try {
                System.out.println(archivo.getParentFile());
                archivo.getParentFile().mkdir();
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("No se pudo crear el archivo: " + e.getMessage());
            }
        }
        return autores;
    }
}
