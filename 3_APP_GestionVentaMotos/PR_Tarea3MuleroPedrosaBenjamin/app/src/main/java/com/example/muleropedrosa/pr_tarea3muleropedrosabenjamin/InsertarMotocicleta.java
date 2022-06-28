package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades.Utilidades;

import java.io.ByteArrayOutputStream;

public class InsertarMotocicleta extends AppCompatActivity {
    Toolbar toolbar;
    private Spinner marca;
    private EditText modelo;
    private EditText km;
    private EditText anio;
    private EditText cc;
    private EditText cv;
    private EditText precio;
    private CheckBox vendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_motocicleta);

        //Asociamos cada variable con su editText, spinner y checkbox a través de su id.
        toolbar = findViewById(R.id.toolbar_3);
        marca = findViewById(R.id.id_spinner_marca);
        modelo = findViewById(R.id.id_modelo);
        km = findViewById(R.id.id_km);
        anio = findViewById(R.id.id_anio);
        cc = findViewById(R.id.id_cc);
        cv = findViewById(R.id.id_cv);
        precio = findViewById(R.id.id_precio);
        vendido = findViewById(R.id.id_vendido_check);



        //Añadimos toolbar.
        setSupportActionBar(toolbar);

        //Añadimos las opciones al spinner "marca"
        String[] opciones={"Selecciona una marca","Honda","Kawasaki","Aprillia", "Ducaty","Keeway", "Daelim", "BMW", "Yamaha"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, opciones);
        marca.setAdapter(adapter);
    }

    private void registrarMotoSQL(){//Método para registrar la moto modificada
        ConexionSQLiteHelper con=new ConexionSQLiteHelper(this, "baseDatos_motos",null,1);
        SQLiteDatabase base_Datos_motos=con.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_MARCA,marca.getSelectedItem().toString());
        values.put(Utilidades.CAMPO_MODELO,modelo.getText().toString());
        values.put(Utilidades.CAMPO_KM,km.getText().toString());
        values.put(Utilidades.CAMPO_ANIO,anio.getText().toString());
        values.put(Utilidades.CAMPO_CC,cc.getText().toString());
        values.put(Utilidades.CAMPO_CV,cv.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO,precio.getText().toString());
        values.put(Utilidades.CAMPO_VENDIDO,vendido.isChecked());
        Long idResultante=base_Datos_motos.insert(Utilidades.TABLA_MOTO,Utilidades.CAMPO_MARCA,values);
        base_Datos_motos.close();

    }

    public void vol_main(View view) {//Método que se utiliza al pulsar el botón de registro que lanza el método registrarMotoSQL
        registrarMotoSQL();
        Intent a = new Intent (this, MainActivity.class);
        Toast.makeText(this, "Los datos han sido insertados", Toast.LENGTH_SHORT).show();
        startActivity(a);
        finish();

    }


}