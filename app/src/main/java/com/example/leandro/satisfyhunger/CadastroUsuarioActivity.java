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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText  editEmail, editSenha;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
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
           }else{
               alert("E-mail e Senha devem ser preenchidos");
           }
            }

        return super.onOptionsItemSelected(item);
    }
public boolean validarUsuario(String email, String senha){
        if (email.isEmpty()|| senha.isEmpty()){
            return false;

        }else{
            return true;
        }
}

    private void criarUsuario(String email, String senha) {
        firebaseAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            alert("Usuário Cadastrado com Sucesso");
                            Intent intent = new Intent(CadastroUsuarioActivity.this,InicialActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            alert( "Usuário não Cadastrado");
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    private void alert(String msg){
        Toast.makeText(CadastroUsuarioActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
