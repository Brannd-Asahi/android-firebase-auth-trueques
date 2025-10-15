package com.example.loginapptrueques;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class BorrarPublicacion extends AppCompatActivity {

    private EditText etSlugDelete;
    private Button btnEliminar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_publicacion);

        etSlugDelete = findViewById(R.id.etSlugDelete);
        btnEliminar = findViewById(R.id.btnEliminar);
        db = FirebaseFirestore.getInstance();

        btnEliminar.setOnClickListener(v -> {
            String slug = etSlugDelete.getText().toString().trim();
            if (slug.isEmpty()) {
                Toast.makeText(BorrarPublicacion.this, "Ingrese el slug de la publicación", Toast.LENGTH_SHORT).show();
                return;
            }
            db.collection("publicaciones").document(slug)
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(BorrarPublicacion.this, "Publicación eliminada", Toast.LENGTH_SHORT).show();
                        etSlugDelete.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(BorrarPublicacion.this, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
