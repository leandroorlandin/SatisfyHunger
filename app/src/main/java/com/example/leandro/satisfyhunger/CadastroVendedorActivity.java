package com.example.leandro.satisfyhunger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import model.Vendedor;


public class CadastroVendedorActivity extends AppCompatActivity {

    private EditText editNome, editTelefone, editLocal, editProduto, editDescricao;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vendedor);
        inicializarComponentes();
        inicializarFirebase();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void inicializarComponentes(){
        editNome=(EditText)findViewById(R.id.editNome);
        editTelefone=(EditText)findViewById(R.id.editTelefone);
        editLocal=(EditText)findViewById(R.id.editLocal);
        editProduto=(EditText)findViewById(R.id.editProduto);
        editDescricao=(EditText)findViewById(R.id.editDescricao);

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_voltar){
            Intent intent = new Intent(this, InicialActivity.class);
            startActivity(intent);

        } else if (id == R.id.menu_salvar) {

            String nome = editNome.getText().toString().trim();
            String telefone = editTelefone.getText().toString().trim();
            String local = editLocal.getText().toString().trim();
            String produto = editProduto.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();

            if(validarInformacoes(nome, telefone, local, produto, descricao)){
                criarVendedor(nome, telefone, local, produto, descricao);
                alert("Cadastrado com Sucesso");
                Intent intent = new Intent(CadastroVendedorActivity.this,InicialActivity.class);
                startActivity(intent);
                finish();

            }else{
                alert("Todos os campos devem ser preenchidos");
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void criarVendedor(String nome, String telefone, String local,
                               String produto, String descricao) {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(UUID.randomUUID().toString());
        vendedor.setNome(nome);
        vendedor.setTelefone(telefone);
        vendedor.setLocal(local);
        vendedor.setProduto(produto);
        vendedor.setDescricao(descricao);

        databaseReference.child("Vendedor").child(vendedor.getId()).setValue(vendedor);
    }

    public boolean validarInformacoes(String nome, String telefone, String local, String produto, String descricao){
        if (nome.isEmpty()|| telefone.isEmpty()|| local.isEmpty()|| produto.isEmpty()|| descricao.isEmpty()){
            return false;

        }else{
            return true;
        }

    }
    private void alert(String msg){
        Toast.makeText(CadastroVendedorActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
