/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salitaverde.parcial2.persistencia;

import com.salitaverde.parcial2.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Fabrizio Castillo
 */
public class Persistencia {

    public static final String barra = File.separator;

    public static final String ubicacion = System.getProperty("user.dir") + barra + "Registros" + barra;

    public Persistencia() {
    }

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

 public static void guardarJson(Autor nuevoAutor) {
    List<Autor> autores = new ArrayList<>();
    File archivo = new File(ubicacion);
    if (archivo.exists()) {
        try (Reader lector = new FileReader(archivo)) {
            // Define el tipo de dato que se va a leer: una lista de autores
            Type listType = new TypeToken<List<Autor>>() {}.getType();
            // Convierte el contenido del archivo JSON a una lista de autores
            autores = gson.fromJson(lector, listType);
            if (autores == null) {
                autores = new ArrayList<>();
            }
        } catch (JsonSyntaxException e) {
            System.out.println("El archivo no contenia una lista válida. Se inicializa una nueva.");
            autores = new ArrayList<>();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    autores.add(nuevoAutor);

    try (Writer escritor = new FileWriter(archivo)) {
         // Convierte la lista de autores a JSON y la guarda en el archivo
        gson.toJson(autores, escritor);
    } catch (IOException e) {
        e.printStackTrace();
    
}}}
