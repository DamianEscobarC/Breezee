package com.example.breeze;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsuario, edtClave;
    private CheckBox chkRecordarme;
    private Button btnIngresar;
    private TextView txtIrARegistro;

    private static final String PREFS_LOGIN = "BreezeLoginPrefs";
    private static final String KEY_RECORDAR = "recordar";
    private static final String KEY_USUARIO = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Union de variables Java con los IDs del layout XML
        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);
        chkRecordarme = findViewById(R.id.chkRecordarme);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtIrARegistro = findViewById(R.id.txtIrARegistro);

        // Cargar datos si el usuario activó "Recuérdame" anteriormente
        SharedPreferences loginPrefs = getSharedPreferences(PREFS_LOGIN, MODE_PRIVATE);
        boolean recordado = loginPrefs.getBoolean(KEY_RECORDAR, false);
        if (recordado) {
            edtUsuario.setText(loginPrefs.getString(KEY_USUARIO, ""));
            chkRecordarme.setChecked(true);
        }

        // EVENTO CLIC: Abrir la pantalla de Registro mediante Intent
        txtIrARegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //EVENTO CLIC: Validar inicio de sesión
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoIngresado = edtUsuario.getText().toString().trim();
                String claveIngresada = edtClave.getText().toString().trim();

                if (correoIngresado.isEmpty() || claveIngresada.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Leer datos registrados previamente en RegisterActivity
                SharedPreferences userPrefs = getSharedPreferences(RegisterActivity.PREFS_USUARIOS, MODE_PRIVATE);
                String correoRegistrado = userPrefs.getString("correo", "");
                String passRegistrada = userPrefs.getString("password", "");

                // Validar credenciales
                if (correoIngresado.equals(correoRegistrado) && claveIngresada.equals(passRegistrada)) {
                    // Manejar persistencia de Recuérdame
                    SharedPreferences.Editor editor = loginPrefs.edit();
                    if (chkRecordarme.isChecked()) {
                        editor.putBoolean(KEY_RECORDAR, true);
                        editor.putString(KEY_USUARIO, correoIngresado);
                    } else {
                        editor.clear();
                    }
                    editor.apply();

                    String nombre = userPrefs.getString("nombre", "Usuario");
                    Toast.makeText(MainActivity.this, "¡Bienvenido/a, " + nombre + "!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas o usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}