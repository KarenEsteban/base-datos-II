/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.model;

/**
 *
 * @author rapid
 */
import java.sql.*;
public class Operaciones {
    
    public String sumar(String valor1, String valor2){
        String resultado="Nada";
        try{
    Connection con=         Conexion.conectarse();
  CallableStatement callate=  con.prepareCall("{call suma(?,?,?)}");
                    callate.setFloat(1, Float.parseFloat(valor1));
                    callate.setFloat(2,Float.parseFloat(valor2));
                    callate.registerOutParameter(3, Types.FLOAT);
                    callate.execute();
         float res=           callate.getFloat(3);
         resultado="la suma es "+res;
            
        }catch(Exception e){
            resultado=e.getMessage();
        }
        return resultado;
    }
    
    
}
