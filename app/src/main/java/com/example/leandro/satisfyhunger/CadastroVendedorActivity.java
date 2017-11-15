package com.example.leandro.satisfyhunger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import model.Vendedor;

public class CadastroVendedorActivity extends AppCompatActivity {


    EditText editNome , editEmail, editLocal, editTelefone, editProduto, editDescricao;
    ListView list_vDados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = (EditText)findViewById(R.id.editNome);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editLocal = (EditText)findViewById(R.id.editLocal);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        editProduto = (EditText)findViewById(R.id.editProduto);
        editDescricao  = (EditText)findViewById(R.id.editDescricao);
        list_vDados = (ListView) findViewById(R.id.list_vDados);
        inicializarFirebase();

    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_novo){
            Vendedor vendedor = new Vendedor();
            vendedor.setId(UUID.randomUUID().toString());
            vendedor.setNome(editNome.getText().toString());
            vendedor.setEmail(editEmail.getText().toString());
            vendedor.setLocal(editLocal.getText().toString());
            vendedor.setTelefone(editTelefone.getText().toString());
            vendedor.setProduto(editProduto.getText().toString());
            vendedor.setDescricao(editDescricao.getText().toString());
            vendedor.setTipo(1);
            vendedor.setStatus(1);
            databaseReference.child("Vendedor").child(vendedor.getId()).setValue(vendedor);
            limparCampos();

        }else if(id == R.id.menu_altera){

        }else if(id == R.id.menu_deleta) {


        }else if(id == R.id.menu_buscar){
        //    Intent i = new Intent(MainActivity.this,Pesquisa.class);
        //    startActivity(i);
        }

        return true;
    }

    private void limparCampos() {
        editNome.setText("");
        editEmail.setText("");
        editLocal.setText("");
        editTelefone.setText("");
        editProduto.setText("");
        editDescricao.setText("");
    }
}
