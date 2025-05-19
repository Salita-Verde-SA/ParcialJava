/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salitaverde.parcial2.persistencia;

import com.salitaverde.parcial2.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Fabrizio Castillo
 */
public class Persistencia {

    public static final String barra = File.separator;

    public static final String ubicacion = System.getProperty("user.dir") + barra + "Registros" + barra;

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarJson(Autor autor) throws IOException {
        try (FileWriter escritor = new FileWriter(ubicacion)) {
                gson.toJson(autor, escritor);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}
