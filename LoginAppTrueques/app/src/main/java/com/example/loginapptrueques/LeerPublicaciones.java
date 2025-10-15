package com.example.loginapptrueques;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class LeerPublicaciones extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView tvPublicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_publicaciones);

        tvPublicaciones = findViewById(R.id.tvPublicaciones);
        db = FirebaseFirestore.getInstance();

        cargarPublicaciones();
    }

    private void cargarPublicaciones() {
        db.collection("publicaciones")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        StringBuilder sb = new StringBuilder();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            sb.append("Slug: ").append(doc.getString("slug")).append("\n")
                                    .append("Título: ").append(doc.getString("titulo")).append("\n")
                                    .append("Categoría: ").append(doc.getString("categoria")).append("\n")
                                    .append("Descripción: ").append(doc.getString("descripcion")).append("\n")
                                    .append("------------------------\n");
                        }
                        tvPublicaciones.setText(sb.toString());
                    } else {
                        Toast.makeText(LeerPublicaciones.this, "Error al leer publicaciones.", Toast.LENGTH_SHORT).show();
                        Log.e("Firestore", "Error: ", task.getException());
                    }
                });
    }
}
