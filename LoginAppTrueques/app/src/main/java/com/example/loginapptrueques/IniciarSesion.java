package com.example.loginapptrueques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciarsesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Creación de variables para los EditText
        EditText etEmail  = findViewById(R.id.email);
        EditText etPassword = findViewById(R.id.password);

        // Botón para iniciar sesión
        Button btnIniciarSesion = findViewById(R.id.iniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Verifica que los campos no estén vacíos
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(IniciarSesion.this, "Por favor, complete todos los campos.", Toast.LENGTH_LONG).show();
                    return; // No procede si falta información
                }

                // Redirige a la actividad Home
                Intent intent = new Intent(IniciarSesion.this, Inicio.class);
                startActivity(intent);
            }
        });


        // Botón para redirigir a Registro
        Button btnRedirigirRegistro = findViewById(R.id.redirigirCrearCuenta);
        btnRedirigirRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IniciarSesion.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}
