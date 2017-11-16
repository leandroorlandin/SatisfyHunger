package com.example.leandro.satisfyhunger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private EditText  editEmail, editSenha;
    private Button btnLogin;
    private TextView txtSenhaNova;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void MkLogar(View view) {
        Intent intent = new Intent(this, InicialActivity.class);
        startActivity(intent);
    }

    private void loginAjuste() {
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editEmail = (EditText) findViewById(R.id.editEmail);
                editSenha = (EditText) findViewById(R.id.editSenha);
                inicializarFirebase();
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                try {

                    MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
                    algorithm.update(senha.getBytes());
                    byte[] hash = algorithm.digest();
                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        if ((0xff & hash[i]) < 0x10)
                            hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                        else
                            hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                    String criptografado = hexString.toString();

                    login(email, criptografado);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void login(String email, String senha) {


    }


}

