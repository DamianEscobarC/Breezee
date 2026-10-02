package com.example.breeze;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtRegNombre, edtRegCorreo, edtRegPassword;
    private Button btnRegistrarUsuario;

    public static final String PREFS_USUARIOS = "BreezeUsuariosPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegNombre = findViewById(R.id.edtRegNombre);
        edtRegCorreo = findViewById(R.id.edtRegCorreo);
        edtRegPassword = findViewById(R.id.edtRegPassword);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);

        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtRegNombre.getText().toString().trim();
                String correo = edtRegCorreo.getText().toString().trim();
                String pass = edtRegPassword.getText().toString().trim();

                if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Guardar credenciales en el almacenamiento interno
                SharedPreferences prefs = getSharedPreferences(PREFS_USUARIOS, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", nombre);
                editor.putString("correo", correo);
                editor.putString("password", pass);
                editor.apply();

                Toast.makeText(RegisterActivity.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                finish(); // Cierra el registro y vuelve a la pantalla de Login
            }
        });
    }
}