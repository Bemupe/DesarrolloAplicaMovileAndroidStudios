package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class registro extends AppCompatActivity {

    private Spinner spinner1;
    private EditText tv1;
    private EditText tv2;
    private EditText tv3;
    private Spinner  tv4;
    private EditText tv5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        spinner1 = (findViewById(R.id.id_spinner1));
        String[] opciones={"Elige lugar de recogida","A domicilio","Establecimiento"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, opciones);
        spinner1.setAdapter(adapter);

    }

    public void volver (View view) {

        onBackPressed();
    }

    public void seguir (View view) {
        tv1=(EditText)findViewById(R.id.id_nombre);
        tv2=(EditText)findViewById(R.id.id_primer_apellido);
        tv3=(EditText)findViewById(R.id.id_telefono);
        tv4= (Spinner)findViewById(R.id.id_spinner1);
        tv5=(EditText)findViewById(R.id.id_direccion);

        Intent a = new Intent (this, zumos.class);

        //Envío variables registro
        a.putExtra("variable_nom", tv1.getText().toString());
        a.putExtra("variable_ape", tv2.getText().toString());
        a.putExtra("variable_tel", tv3.getText().toString());
        a.putExtra("variable_spi", tv4.getSelectedItem().toString());
        a.putExtra("variable_dir", tv5.getText().toString());

        //Validación datos introducidos
        String variable_nom= tv1.getText().toString();
        String variable_ape=tv2.getText().toString();
        String variable_tel=tv3.getText().toString();
        String variable_spi=tv4.getSelectedItem().toString();
        String variable_dir=tv5.getText().toString();

        if (variable_nom.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Nombre");
            builder.setMessage("Tienes que poner un nombre");
            builder.setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();}

        if (variable_tel.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Teléfono");
            builder.setMessage("Tienes que poner un teléfono");
            builder.setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();}

        if (tv4.getSelectedItem().toString().equals("Elige lugar de recogida")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Recogida");
            builder.setMessage("Tienes que poner una recogida");
            builder.setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();}

        if (tv4.getSelectedItem().toString().equals("A domicilio") && variable_dir.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Dirección");
            builder.setMessage("Tienes que poner una dirección");
            builder.setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();}

        if (tv1.getText().toString().length()>0
                &&tv3.getText().toString().length()>0
                &&tv4.getSelectedItem().toString().equals("A domicilio")
                &&tv5.getText().toString().length()>0){
            startActivity(a); }

        if (tv1.getText().toString().length()>0
                &&tv3.getText().toString().length()>0
                &&tv4.getSelectedItem().toString().equals("Establecimiento")) {
            startActivity(a); }

    }

}