package com.example.loginapptrueques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private EditText etName, etLastname, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firestore = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.nameText);
        etLastname = findViewById(R.id.lastnameText);
        etEmail = findViewById(R.id.emailText);
        etPassword = findViewById(R.id.passwordText);

        findViewById(R.id.redirigirCrearCuenta).setOnClickListener(v -> registrarUsuario());
        findViewById(R.id.redirigirInicioSesion).setOnClickListener(v ->
                startActivity(new Intent(this, IniciarSesion.class)));
    }

    private void registrarUsuario() {
        String name = etName.getText().toString().trim();
        String lastname = etLastname.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("lastname", lastname);
        user.put("email", email);
        user.put("password", password);

        firestore.collection("users").add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ConfirmacionRegistro.class);
                    intent.putExtra("name", name);
                    intent.putExtra("lastname", lastname);
                    intent.putExtra("email", email);
                    startActivity(intent);
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error al registrar usuario: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
