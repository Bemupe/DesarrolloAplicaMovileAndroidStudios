package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opcion1) {
            Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(MainActivity.this, help.class);
            startActivity(a);
            return true;
        } else if (id == R.id.opcion2) {
            Toast.makeText(this, "Acerca de..", Toast.LENGTH_SHORT).show();
            Intent b = new Intent(MainActivity.this, acerca.class);
            startActivity(b);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void hacerPedido(View view) {
        Intent a = new Intent (this, registro.class);
        startActivity(a);
    }
}
