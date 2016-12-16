/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jc.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author campitos
 */
public class DAOPelicula {
    static ArrayList<Pelicula> peliculas;
    
    public static String guardarPelicula(String titulo, String sinopsis) throws Exception{
        Conexion c=new Conexion();
        Connection con=c.conectarse();
     CallableStatement callate=con.prepareCall("{call guardar_pelicula(?,?,?)}");
        callate.registerOutParameter(1,java.sql.Types.INTEGER);
        callate.setString(2,titulo);
        callate.setString(3,sinopsis);
      
        callate.execute();
        int pk=callate.getInt(1);
        return "SE guardo la pelicula con id:"+pk; 
    }
    public static String actualizarPelicula(Integer id,String titulo, String sinopsis) throws Exception{
        Conexion c=new Conexion();
        Connection con=c.conectarse();
     CallableStatement callate=con.prepareCall("{call actualizar_pelicula(?,?,?)}");
        callate.setInt(1,id);
        callate.setString(2,titulo);
        callate.setString(3,sinopsis);
      
        callate.execute();
        return "Pelicula actualizada con exito"; 
    }
     public static  ArrayList<Pelicula> buscarTodasPeliculas() throws Exception{
        String mensajito = "nada";
        Connection con= Conexion.conectarse();
        //Generamos una setencia para usar oracle desde java
        Statement st = con.createStatement();
        //Cursor
        ResultSet res = st.executeQuery("SELECT * FROM PELICULA");
        int numRegistros=0;
        peliculas= new ArrayList<>();
        while(res.next()){
            numRegistros++;
            Integer id=res.getInt("ID_PELICULA");
            String titulo=res.getString("TITULO");
            String sinopsis=res.getString("SINOPSIS");
            //CREAMOS EL OBJETO DE TIPO PELICULA
            Pelicula p=new Pelicula(id, titulo, sinopsis);
            peliculas.add(p);
        }
        mensajito="Registros encontrados: "+numRegistros;
        return peliculas;
     }
     
     public static  ArrayList<Pelicula> buscarPorNombre(String titulo) throws Exception{
        String mensajito = "nada";
        Connection con= Conexion.conectarse();
        //Generamos una setencia para usar oracle desde java
        Statement st = con.createStatement();
        //Cursor
        ResultSet res = st.executeQuery("SELECT * FROM PELICULA WHERE TITULO='"+titulo+"'");
        int numRegistros=0;
        peliculas= new ArrayList<>();
        while(res.next()){
            numRegistros++;
            Integer id=res.getInt("ID_PELICULA");
            String tit=res.getString("TITULO");
            String sinopsis=res.getString("SINOPSIS");
            //CREAMOS EL OBJETO DE TIPO PELICULA
            Pelicula p=new Pelicula(id, tit, sinopsis);
            peliculas.add(p);
        }
        mensajito="Registros encontrados: "+numRegistros;
        return peliculas;
     }
     
}
