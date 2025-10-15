package com.example.loginapptrueques;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CrearOferta extends AppCompatActivity {

    //Creamos las variables a usar
    private Spinner categoriaSpinner;
    private EditText tituloText, descripcionText;
    private MaterialButton btnCrearPublicacion;
    private String categoriaSeleccionada;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_oferta);


        categoriaSpinner = findViewById(R.id.categoriaSpinner);
        tituloText = findViewById(R.id.tituloText);
        descripcionText = findViewById(R.id.descripcionText);
        btnCrearPublicacion = findViewById(R.id.btnCrearPublicacion);

        // Inicializamos la base de datos
        db = FirebaseFirestore.getInstance();

        // Creamos la lista desplegable
        String[] categorias = {"Sesiones de clases", "Servicio especial", "Intercambio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categorias);
        categoriaSpinner.setAdapter(adapter);

        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSeleccionada = parent.getItemAtPosition(position).toString(); //Guarda la eleccion del usuario
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoriaSeleccionada = null;
            }
        });

        // Botón para crear la publicación
        btnCrearPublicacion.setOnClickListener(v -> {

            //Creamos e inicializamos estas variables para obtener la informacion mas reciente del usuario

            String titulo = tituloText.getText().toString().trim();
            String descripcion = descripcionText.getText().toString().trim();

            if (titulo.isEmpty() || descripcion.isEmpty() || categoriaSeleccionada == null) {
                Toast.makeText(CrearOferta.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Generar un slug numérico de 3 dígitos
            String slug = generarSlug();

            // Crear objeto a guardar
            Map<String, Object> publicacion = new HashMap<>();
            publicacion.put("titulo", titulo);
            publicacion.put("descripcion", descripcion);
            publicacion.put("categoria", categoriaSeleccionada);
            publicacion.put("slug", slug);
            publicacion.put("timestamp", System.currentTimeMillis());

            // Guardar en Firestore, usando el slug como clave
            db.collection("publicaciones")
                    .document(slug)
                    .set(publicacion)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(CrearOferta.this, "Publicación creada con éxito con ID: " + slug, Toast.LENGTH_SHORT).show();

                        tituloText.setText("");
                        descripcionText.setText("");
                        categoriaSpinner.setSelection(0);
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(CrearOferta.this, "Error al crear la publicación: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    // Método para generar un slug numérico de 3 dígitos (entre 100 y 999)
    private String generarSlug() {
        Random random = new Random();
        int num = random.nextInt(900) + 100; // [100, 999]
        return String.valueOf(num);
    }
}
