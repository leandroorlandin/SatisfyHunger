package com.example.leandro.satisfyhunger;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import model.Vendedor;

public class MainActivity extends AppCompatActivity {

    EditText editNome , editEmail;
    ListView list_vDados;

    Button btPesquisar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = (EditText)findViewById(R.id.editNome);
        editEmail= (EditText)findViewById(R.id.editEmail);
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
            databaseReference.child("Vendedor").child(vendedor.getId()).setValue(vendedor);
            limparCampos();
            
        }

        return true;
    }

    private void limparCampos() {
        editNome.setText("");
        editEmail.setText("");
    }
}
