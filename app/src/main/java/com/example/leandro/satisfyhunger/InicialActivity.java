package com.example.leandro.satisfyhunger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }
    public void MkPesquisa(View view) {
        Intent intent = new Intent(this, Pesquisa.class);
        startActivity(intent);
    }
    public void MkCadastroVendedor(View view) {
        Intent intent = new Intent(this, CadastroVendedorActivity.class);
        startActivity(intent);
    }
}
