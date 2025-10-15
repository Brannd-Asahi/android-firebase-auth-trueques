package com.example.loginapptrueques;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class EleccionCRUD extends AppCompatActivity {

    private MaterialButton btnCrear, btnLeer, btnEditar, btnBorrar; //Creamos los botones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_crud);

        // Buscamos en id en la vista xml y las enlazamos con estas variables que creamos
        btnCrear = findViewById(R.id.btnCrear);
        btnLeer = findViewById(R.id.btnLeer);
        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);


        btnCrear.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionCRUD.this, CrearOferta.class);
            startActivity(intent);
        });


        btnLeer.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionCRUD.this, LeerPublicaciones.class);
            startActivity(intent);
        });


        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionCRUD.this, EditarPublicacion.class);
            startActivity(intent);
        });


        btnBorrar.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionCRUD.this, BorrarPublicacion.class);
            startActivity(intent);
        });
    }
}
