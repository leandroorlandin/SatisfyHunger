package com.example.leandro.satisfyhunger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Vendedor;

public class Pesquisa extends AppCompatActivity {
    private EditText editPalavra;
    private ListView list_vPesquisa;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Vendedor> vendedorList = new ArrayList<Vendedor>();
    private ArrayAdapter<Vendedor> vendedorArrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        inicializarComponentes();
        inicializarFirebase();
        eventoEdit();
    }

    private void inicializarComponentes() {
        editPalavra = (EditText) findViewById(R.id.editPalavra);
        list_vPesquisa = (ListView) findViewById(R.id.list_vPesquisa);
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(Pesquisa.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoEdit() {
        editPalavra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String palavra = editPalavra.getText().toString().trim();
                pesquisaPalavra(palavra);
            }
        });
    }

    private void pesquisaPalavra(String palavra) {

        Query query;

        if(palavra.equals("")){
            query = databaseReference.child("Vendedor").orderByChild("nome");
        }else{
            query = databaseReference.child("Vendedor").orderByChild("local").startAt(palavra).endAt(palavra+"\uf8ff");
        }

        vendedorList.clear();

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Vendedor vendedor = objSnapshot.getValue(Vendedor.class);
                    vendedorList.add(vendedor);
                }
                vendedorArrayAdapter = new ArrayAdapter<Vendedor>(Pesquisa.this,
                        android.R.layout.simple_list_item_1, vendedorList);
                list_vPesquisa.setAdapter(vendedorArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesquisaPalavra("");
    }
}
