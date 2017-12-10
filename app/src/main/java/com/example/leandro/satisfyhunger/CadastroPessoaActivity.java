package com.example.leandro.satisfyhunger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import model.Pessoa;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText  editEmail, editSenha;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        inicializarComponentes();

    }


    private void inicializarComponentes(){
        editEmail = (EditText)findViewById(R.id.editEmail);
        editSenha = (EditText)findViewById(R.id.editSenha);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_voltar){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.menu_salvar) {
            // testa se os campos estão preenchidos e se o email já existe na base
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();
           if(validarUsuario(email, senha)){
               criarUsuario(email, senha);
           }
            }
        
        return true;

    }
private boolean validarUsuario(String email, String senha){
        if (email.isEmpty()|| senha.isEmpty()){
            alert("E-mail e Senha devem ser preenchidos");
            return false;

        }else{
            return true;
        }
}

    private void criarUsuario(String email, String senha) {
        firebaseAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(CadastroPessoaActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário Cadastrado com Sucesso");
                            Intent intent = new Intent(CadastroPessoaActivity.this,InicialActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            alert( "Usuário não Cadastrado");
                        }
                    }
                });
    }

    private void limparCampos() {
        editEmail.setText("");
        editSenha.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    private void alert(String msg){
        Toast.makeText(CadastroPessoaActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
