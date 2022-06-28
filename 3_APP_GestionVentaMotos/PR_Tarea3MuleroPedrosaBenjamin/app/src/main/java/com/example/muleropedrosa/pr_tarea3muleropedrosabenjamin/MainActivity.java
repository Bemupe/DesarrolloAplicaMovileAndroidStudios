package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity
//PreferenceFragmentCompat
{
    Toolbar toolbar;
    private Boolean preferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_wala);
        setSupportActionBar(toolbar);



        //Utilizamos "PreferenceManager" para que los cambios en preferencia se guarden en el programa.
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        ConexionSQLiteHelper con=new ConexionSQLiteHelper(this, "baseDatos_motos",null,1);
    }


    public void listadomotocicletas(View view) {
        Intent a = new Intent(this, ListadoMotocicletas.class);
        startActivity(a);
    }

}