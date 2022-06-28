package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades.Utilidades;

public class FichaMoto extends AppCompatActivity {
    Toolbar toolbar;
    private Spinner marca;
    private EditText modelo;
    private EditText km;
    private EditText anio;
    private EditText cc;
    private EditText cv;
    private EditText precio;
    private CheckBox vendido;
    private String variable_id;
    ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "baseDatos_motos", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_moto);
        toolbar = findViewById(R.id.toolbar_5);
        setSupportActionBar(toolbar);

        //Asociamos cada variable con su editText, spinner y checkbox a través de su id.
        marca = findViewById(R.id.id_mar_spi_fic);
        modelo = findViewById(R.id.id_mod_fic);
        anio = findViewById(R.id.id_an_fic);
        km = findViewById(R.id.id_kil_fic);
        cc = findViewById(R.id.id_cili_fic);
        cv = findViewById(R.id.id_po_fic);
        precio = findViewById(R.id.id_pre_fic);
        vendido = findViewById(R.id.id_ven_box_fic);

        //Añadimos las opciones al spinner "marca"
        String[] opciones = {"Selecciona una marca", "Honda", "Kawasaki", "Aprillia", "Ducaty", "Keeway", "Daelim", "BMW", "Yamaha"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, opciones);
        marca.setAdapter(adapter);


        variable_id = getIntent().getStringExtra("variable_id");

        //Conexion con base de datos y creación del cursor con la sentencia SQL para obtener el registro seleccionado a partir del campo Id.
        SQLiteDatabase base_Datos_motos = con.getReadableDatabase();
        Cursor cursor = base_Datos_motos.rawQuery(" SELECT * FROM motos WHERE id=" + variable_id, null);

        //Sacamos datos e introducimos la información en los editText, spinner y checkbox.
        if (cursor.moveToFirst())
        {
            do {
                for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].equals(cursor.getString(1))) {
                        marca.setSelection(i);
                    }
                }
                modelo.setText(cursor.getString(2));
                km.setText(cursor.getString(3));
                anio.setText(cursor.getString(4));
                precio.setText(cursor.getString(5));
                cv.setText(cursor.getString(6));
                cc.setText(cursor.getString(7));

                if (cursor.getString(8).equals("0"))
                {
                    vendido.setChecked(false);
                }
                else
                    {
                        vendido.setChecked(true);
                    } ;
            } while(cursor.moveToNext());
        }
        base_Datos_motos.close();
    }


    //Introducimos actualización
       public void vol_list(View view)
       {
           SQLiteDatabase base_Datos_motos = con.getWritableDatabase();
           String[] parametros={variable_id};
           ContentValues values=new ContentValues();
           values.put(Utilidades.CAMPO_MARCA,marca.getSelectedItem().toString());
           values.put(Utilidades.CAMPO_MODELO,modelo.getText().toString());
           values.put(Utilidades.CAMPO_KM,km.getText().toString());
           values.put(Utilidades.CAMPO_ANIO,anio.getText().toString());
           values.put(Utilidades.CAMPO_CC,cc.getText().toString());
           values.put(Utilidades.CAMPO_CV,cv.getText().toString());
           values.put(Utilidades.CAMPO_PRECIO,precio.getText().toString());
           values.put(Utilidades.CAMPO_VENDIDO,vendido.isChecked());

           base_Datos_motos.update(Utilidades.TABLA_MOTO,values,Utilidades.CAMPO_ID+"=?",parametros);

           Intent a = new Intent (this, ListadoMotocicletas.class);
           Toast.makeText(this, "El estado de la motocicleta ha sido actualizado", Toast.LENGTH_SHORT).show();
           startActivity(a);

           base_Datos_motos.close();
    }


}