package com.example.leandro.satisfyhunger;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    EditText edposicao;
    Button btPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edposicao = (EditText) findViewById(R.id.edposicao);
        btPesquisar = (Button) findViewById(R.id.btPesquisar);

        btPesquisar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(MainActivity.this);

                dialogo.setTitle("Pesquisa foi Realizada");

                dialogo.setMessage("Resultado na tabela");

                dialogo.setNeutralButton("OK", null);

                dialogo.show();

            }
        });



    }
}
