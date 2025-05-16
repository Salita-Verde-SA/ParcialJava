/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package LogicaI;

import java.util.ArrayList;

/**
 *
 * @author Fabrizio Castillo
 */
public class Autor extends Persona {
    public String pseudonimo;
    public ArrayList<Autor> autores ;
    
    

    public Autor(int dni,String nombre, String pseudonimo ){
        super(dni, nombre);
        this.pseudonimo = pseudonimo;
        
    }

}


