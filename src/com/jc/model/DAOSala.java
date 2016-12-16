/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.model;

/**
 *
 * @author T-202
 */
import java.sql.*;

public class DAOSala {
    public static String comprarBoletos(Integer id_pelicula, Integer id_sala, Integer boletos)throws Exception{
        Conexion c=new Conexion();
        Connection con=c.conectarse();
     CallableStatement callate=con.prepareCall("{call comprar_boletos(?,?,?)}");
        callate.setInt(1, id_pelicula);
        callate.setInt(2,id_sala);
        callate.setInt(3,boletos);
        callate.execute();
        String mensaje="Boletos comprados con exito";
        return mensaje;
    }
    
}
