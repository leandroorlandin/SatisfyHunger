package com.example.leandro.satisfyhunger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //makeLogin
    public void MkLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    //registrar
    public void MkCadastro(View view){
        Intent intent = new Intent(this, CadastroPessoaActivity.class);
        startActivity(intent);
    }
}
