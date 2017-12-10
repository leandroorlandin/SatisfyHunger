package com.example.leandro.satisfyhunger;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private EditText  editEmail, editSenha;
    private Button btnLogin, btnNovo;
    private TextView txtSenhaNova;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        eventoClicks();
    }

    public void eventoClicks() {
        btnNovo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CadastroPessoaActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                if (validarUsuario(email,senha)){
                    login(email,senha);
                }

            }
        });
    }

    public boolean validarUsuario(String email, String senha){
        if (email.isEmpty()|| senha.isEmpty()){
            alert("E-mail e Senha devem ser preenchidos");
            return false;

        }else{
            return true;
        }
    }
    private void inicializarComponentes(){
        editEmail = (EditText) findViewById(R.id.email);
        editSenha = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNovo = (Button) findViewById(R.id.btnNovo);
    }




    private void login(String email, String senha) {
    firebaseAuth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this,InicialActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        alert( "E-mail ou senha incorretos");
                    }
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }
    private void alert(String s){
        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
    }


}

