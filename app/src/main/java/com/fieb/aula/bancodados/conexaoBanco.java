package com.fieb.aula.bancodados;

import android.content.Context;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBanco {
    public static Connection conectar (Context ctx){
        Connection conn = null;
        try {
            StrictMode.ThreadPolicy politica;
            politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.24;"+
                    "databaseName=Banco_Android;user=sa;password=@ITB123456;");

        } catch (SQLException e){
            e.getMessage();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}
