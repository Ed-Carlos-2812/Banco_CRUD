package com.fieb.aula.bancodados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextPassword;
    private Button loginRealizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginRealizado = findViewById(R.id.loginRealizado);

        loginRealizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextNome.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    new LoginTask().execute(name, email, password);
                } else {
                    Toast.makeText(Login.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String name = params[0];
            String email = params[1];
            String password = params[2];

            // Substitua os placeholders abaixo pelas informações do seu banco de dados
          /*  String url = "jdbc:jtds:sqlserver://172.19.0.57;databaseName=Banco_Android";
            String user = "sa";
            String pass = "@ITB123456";
*/
            try {
              /*  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection(url, user, pass);
              */
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://172.19.0.57;"+
                        "databaseName=Banco_Android;user=sa;password=@ITB123456;");
                String query = "SELECT * FROM Usuario WHERE nome = ? AND email = ? AND senha = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                boolean validLogin = resultSet.next(); // Se houver resultado, login é válido

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return validLogin;
            } catch (Exception e) {
                e.printStackTrace(); // Para depuração, mas evite expor detalhes ao usuário
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                // Login bem-sucedido, redireciona para a Home
                Toast.makeText(Login.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, MainActivity.class));
                finish(); // Fecha a tela de login
            } else {
                // Falha no login
                Toast.makeText(Login.this, "Nome, e-mail ou senha incorretos.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
