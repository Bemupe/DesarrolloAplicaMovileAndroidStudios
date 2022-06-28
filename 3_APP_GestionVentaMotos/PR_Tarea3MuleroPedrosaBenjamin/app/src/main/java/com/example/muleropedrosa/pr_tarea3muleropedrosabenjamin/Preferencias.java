package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.fragmento.AjustesFragment;

public class Preferencias extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        toolbar = findViewById(R.id.toolbar_6);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_preferencias, new AjustesFragment()).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pref, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.listado) {
            Toast.makeText(this, "Listado motos", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(Preferencias.this, ListadoMotocicletas.class);
            startActivity(a);
            finish();
            return true;
        } else if (id == R.id.insert_moto) {
            Toast.makeText(this, "Insertar motocicleta", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(Preferencias.this, InsertarMotocicleta.class);
            startActivity(a);
            finish();
            return true;
        } else if (id == R.id.elimi_moto) {
            Toast.makeText(this, "Eliminar motocicleta", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(Preferencias.this, EliminarMotocicleta.class);
            startActivity(a);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmar(View view) {
        Intent a = new Intent (this, MainActivity.class);
        startActivity(a);
        finish();
    }
}