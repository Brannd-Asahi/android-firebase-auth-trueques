package com.example.loginapptrueques;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class EditarPublicacion extends AppCompatActivity {

    private EditText etSlug, etNuevoTitulo, etNuevaDescripcion;
    private Button btnBuscar, btnActualizar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicacion);

        etSlug = findViewById(R.id.etSlug);
        etNuevoTitulo = findViewById(R.id.etNuevoTitulo);
        etNuevaDescripcion = findViewById(R.id.etNuevaDescripcion);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        db = FirebaseFirestore.getInstance();

        // Primer paso: buscar publicación mediante el slug
        btnBuscar.setOnClickListener(v -> {
            String slug = etSlug.getText().toString().trim();
            if (slug.isEmpty()) {
                Toast.makeText(EditarPublicacion.this, "Ingrese el slug de la publicación", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("publicaciones").document(slug)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // Rellenar los campos para editar
                            etNuevoTitulo.setText(documentSnapshot.getString("titulo"));
                            etNuevaDescripcion.setText(documentSnapshot.getString("descripcion"));
                        } else {
                            Toast.makeText(EditarPublicacion.this, "No se encontró la publicación", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(EditarPublicacion.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        // Segundo paso: actualizar publicación
        btnActualizar.setOnClickListener(v -> {
            String slug = etSlug.getText().toString().trim();
            String nuevoTitulo = etNuevoTitulo.getText().toString().trim();
            String nuevaDescripcion = etNuevaDescripcion.getText().toString().trim();

            if (slug.isEmpty() || nuevoTitulo.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(EditarPublicacion.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("publicaciones").document(slug)
                    .update("titulo", nuevoTitulo, "descripcion", nuevaDescripcion)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditarPublicacion.this, "Publicación actualizada", Toast.LENGTH_SHORT).show();
                        etSlug.setText("");
                        etNuevoTitulo.setText("");
                        etNuevaDescripcion.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(EditarPublicacion.this, "Error al actualizar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
