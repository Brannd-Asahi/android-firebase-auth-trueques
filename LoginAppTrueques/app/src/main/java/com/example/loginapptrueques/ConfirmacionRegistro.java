package com.example.loginapptrueques;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmacionRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmacion_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a los TextView en activity_main2.xml
        TextView tvFullName = findViewById(R.id.tvName);
        TextView tvLastname= findViewById(R.id.tvLastname);
        TextView tvEmail = findViewById(R.id.tvEmail);

        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Recuperar los datos del Intent
        String name = intent.getStringExtra("name");
        String lastname = intent.getStringExtra("lastname");
        String email = intent.getStringExtra("email");

        // Mostrar los datos en los TextView
        tvFullName.setText("Nombre: " + name);
        tvEmail.setText("Correo: " + email);
        tvLastname.setText("Contraseña: " + lastname);

    }
}