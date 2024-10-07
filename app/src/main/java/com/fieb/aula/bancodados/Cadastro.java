package com.fieb.aula.bancodados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Cadastro extends AppCompatActivity {

    private Button btn_voltar;

EditText editNome, editEmail, editSenha;
Button btn_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



editNome = (EditText) findViewById(R.id.editNome);
editEmail = (EditText) findViewById(R.id.editEmail);
editSenha = (EditText) findViewById(R.id.editSenha);
btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);

btn_cadastrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();


        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Snackbar.make(btn_cadastrar, "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
            return;
        }


        Usuario user = new Usuario(
        editNome.getText().toString(),
        editEmail.getText().toString(),
        editSenha.getText().toString());





      int res = UsuarioCrud.inserirUsuario(user, getBaseContext());
      if (res <=0){
          Snackbar.make(btn_cadastrar, "E-mail já cadastrado!", Snackbar.LENGTH_LONG).show();
      } else {
          Snackbar.make(btn_cadastrar, "Dados Cadastrados com Sucesso", Snackbar.LENGTH_LONG).show();
      }


    }
});

btn_voltar = findViewById(R.id.btn_voltar);
btn_voltar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Cadastro.this, Login.class);
        startActivity(intent);
    }
});


    }
}
