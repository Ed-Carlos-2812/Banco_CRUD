package com.fieb.aula.bancodados;

import android.content.Context;

import java.sql.PreparedStatement;

public class UsuarioCrud {

public static int inserirUsuario (Usuario usuario, Context ctx) {

    int resposta = 0;

    try {
        PreparedStatement pst = conexaoBanco.conectar(ctx).prepareStatement(
                "Insert Into Usuario (nome, email, senha)"+ "values (?,?,?)");

       pst.setString(1, Usuario.getNome());
       pst.setString(2, Usuario.getEmail());
       pst.setString(3, Usuario.getSenha());


       resposta = pst.executeUpdate();
    } catch (Exception e){
        e.getMessage();
    }


    return resposta;

}

}

