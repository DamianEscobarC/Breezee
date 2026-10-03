package com.example.breeze;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private TextView txtBienvenida, txtCerrarSesion;
    private LinearLayout cardCalcularKm, cardHistorial, cardImpacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtBienvenida = findViewById(R.id.txtBienvenida);
        txtCerrarSesion = findViewById(R.id.txtCerrarSesion);
        cardCalcularKm = findViewById(R.id.cardCalcularKm);
        cardHistorial = findViewById(R.id.cardHistorial);
        cardImpacto = findViewById(R.id.cardImpacto);

        SharedPreferences userPrefs = getSharedPreferences(RegisterActivity.PREFS_USUARIOS, MODE_PRIVATE);
        String nombre = userPrefs.getString("nombre", "Viajero");
        txtBienvenida.setText("Hola, " + nombre);

        cardCalcularKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Abriendo Calculador de Km...", Toast.LENGTH_SHORT).show();
            }
        });

        cardHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Abriendo Historial de Viajes...", Toast.LENGTH_SHORT).show();
            }
        });

        cardImpacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Abriendo Impacto Ambiental...", Toast.LENGTH_SHORT).show();
            }
        });

        txtCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}