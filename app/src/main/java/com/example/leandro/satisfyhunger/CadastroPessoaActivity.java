package com.example.leandro.satisfyhunger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.*;
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
import model.Vendedor;

public class CadastroPessoaActivity extends AppCompatActivity {
    EditText editNome , editEmail, editTelefone, editSenha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        editNome = (EditText)findViewById(R.id.editNome);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        editSenha = (EditText)findViewById(R.id.editSenha);
        inicializarFirebase();

    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo) {
            // testa se os campos estão preenchidos e se o email já existe na base
            if (pesquisaEmail(editEmail.getText().toString()) == false) {
                if (
                        editNome.getText().toString().isEmpty() ||
                                editEmail.getText().toString().isEmpty() ||
                                editTelefone.getText().toString().isEmpty() ||
                                editSenha.getText().toString().isEmpty()
                        ) {


                } else {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(UUID.randomUUID().toString());
                    pessoa.setNome(editNome.getText().toString());
                    pessoa.setEmail(editEmail.getText().toString());
                    pessoa.setTelefone(editTelefone.getText().toString());
                    //adicionar criptografia
                    try {
                    String senha = editSenha.getText().toString();

                    MessageDigest algorithm = null;

                        algorithm = MessageDigest.getInstance("SHA-1");


                    algorithm.update( senha.getBytes() );
                    byte[] hash = algorithm.digest();
                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        if ((0xff & hash[i]) < 0x10)
                            hexString.append( "0" + Integer.toHexString((0xFF & hash[i])));
                        else
                            hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                    String criptografado = hexString.toString();

                    pessoa.setSenha(criptografado);
                    pessoa.setTipo(1);
                    pessoa.setStatus(1);
                    databaseReference.child("Pessoa").child(pessoa.getId()).setValue(pessoa);
                    limparCampos();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }

            } else if (id == R.id.home) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }

        }
        return true;

    }

    private void limparCampos() {
        editNome.setText("");
        editEmail.setText("");
        editTelefone.setText("");
        editSenha.setText("");
    }
    private boolean pesquisaEmail(String email){
        Query query;
            query = databaseReference.child("Pessoa").orderByChild("email").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                 String value = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            if(query == null) {

            return false;
        }else{
            return false;
        }
    }
}
