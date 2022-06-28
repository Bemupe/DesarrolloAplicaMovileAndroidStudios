package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades.TablaDinamica;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades.Utilidades;


public class EliminarMotocicleta extends AppCompatActivity {

    Toolbar toolbar;
    private String[] tit_cabecera={"Eliminar","Id","Marca","Modelo","KM","Año"};
    private String[] filas=new String [5];
    private String vendido;
    private TableLayout tableLayout;
    private String variable_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_motocicleta);
        toolbar = findViewById(R.id.toolbar_4);
        setSupportActionBar(toolbar);
        tableLayout=(TableLayout)findViewById(R.id.tabla_elim) ;

        //Creamos la cabecera de la tabla utilizando el constructor creado para añadir la cabecera.
        TablaDinamica tablaListado=new TablaDinamica(tableLayout,getApplicationContext());
        tablaListado.añadirCabecera(tit_cabecera);

        //Abrimos conexión con base de datos y creamos cursor
        ConexionSQLiteHelper con=new ConexionSQLiteHelper(this, "baseDatos_motos",null,1);
        SQLiteDatabase base_Datos_motos=con.getReadableDatabase();
        Cursor cursor = base_Datos_motos.rawQuery(" SELECT * FROM motos ", null);

        if (cursor.moveToFirst())
        {
            do //Recorremos el cursor hasta que no haya más registros y durante el recorrido de registros vamos creando las tabla. (do-while)
            {//Guardamos en una variable string si la moto esta vendida o no
                for (int i=0;i< filas.length;i++) //Guardamos el contenido de fila en un array
                    {
                        filas[i] = cursor.getString(i);
                        vendido=cursor.getString(8);//Guardamos en una variable string si la moto esta vendida o no
                    }
                if (vendido.equals("0"))//Dependiendo de si está vendida la moto, la fila será de un color distinto (verde)
                    {//Se crea la fila (TableRow) y se intoduce la primera celda que es el botón de eliminar registro para el caso moto no vendida o disponible.
                        int indiceC=0;
                        int indiceArray=1;
                        TableRow filaTabla=new TableRow(this);
                        Button boton=new Button(this);
                        TextView txtCelda=new TextView(this);
                        filaTabla.setBackgroundColor(Color.GREEN);
                        boton.setText("Eliminar");
                        boton.setBackgroundColor(Color.GREEN);
                        boton.setGravity(Gravity.CENTER);
                        filaTabla.addView(boton,newTableRowsParams());

                        while (indiceC<filas.length)//Se crean el resto de celdas y se introduce la información del array en la celdas. Se da formato.
                            {
                                txtCelda=new TextView(this);
                                txtCelda.setGravity(Gravity.CENTER);
                                txtCelda.setTextSize(14);
                                txtCelda.setTextColor(Color.BLACK);
                                txtCelda.setBackgroundColor(Color.WHITE);
                                txtCelda.setText((filas[indiceC++]));
                                filaTabla.addView(txtCelda,newTableRowsParams());
                            }

                        tableLayout.addView(filaTabla);//Se introuduce la fila (TableRow) en la tabla (TableLayout)
                        base_Datos_motos.close();//Se cierra la base de datos

                        boton.setOnClickListener(new View.OnClickListener() //Se utiliza setOnClickListener sobre el boton para que al pulsarlo se elimine esa fila.
                            //Buscamos id a eliminar, abrimos base de datos de forma para modificarla, establecemos la sentencia para borrar el registro, volvemos
                            //al listado de motocicletas con el toast de "Los datos han sido eliminados".
                        {
                            @Override
                            public void onClick(View v)
                            {
                                TextView firstTextView = (TextView) filaTabla.getChildAt(1);//Se busca el TextView donde se encuentra la columna del Id
                                variable_id= firstTextView.getText().toString();//Introducimos el número del id en variable_id
                                String[] parametros={firstTextView.getText().toString()};//Guardamos el id que nos servirá para señalar que registro que hay que eliminar.
                                SQLiteDatabase base_Datos_motos = con.getWritableDatabase();
                                base_Datos_motos.execSQL("DELETE FROM "+Utilidades.TABLA_MOTO+" WHERE id ="+variable_id);
                                Intent a = new Intent (EliminarMotocicleta.this, MainActivity.class);
                                Toast.makeText(EliminarMotocicleta.this, "Los datos han sido eliminados", Toast.LENGTH_SHORT).show();
                                startActivity(a);
                                finish();
                            }
                        });
                    }
                else
                    {//Se hace el mismo proceso pero para el caso de color de fila para moto vendida(rojo)
                        int indiceC=0;
                        int indiceArray=1;
                        TableRow filaTabla=new TableRow(this);
                        Button boton=new Button(this);
                        TextView txtCelda=new TextView(this);
                        filaTabla.setBackgroundColor(Color.RED);
                        boton.setText("Eliminar");
                        boton.setBackgroundColor(Color.RED);
                        boton.setGravity(Gravity.CENTER);
                        filaTabla.addView(boton,newTableRowsParams());

                        while (indiceC<filas.length)//Se crean el resto de celdas y se introduce la información del array en la celdas. Se da formato.

                            {

                                txtCelda=new TextView(this);
                                txtCelda.setGravity(Gravity.CENTER);
                                txtCelda.setTextSize(14);
                                txtCelda.setTextColor(Color.BLACK);
                                txtCelda.setBackgroundColor(Color.WHITE);
                                txtCelda.setText((filas[indiceC++]));
                                filaTabla.addView(txtCelda,newTableRowsParams());

                            }
                        tableLayout.addView(filaTabla);//Se introuduce la fila (TableRow) en la tabla (TableLayout)
                        base_Datos_motos.close();
                        boton.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                TextView firstTextView = (TextView) filaTabla.getChildAt(1);
                                variable_id= firstTextView.getText().toString();
                                String[] parametros={firstTextView.getText().toString()};//Guardamos el id que nos servirá para señalar que registro que hay que eliminar.
                                SQLiteDatabase base_Datos_motos = con.getWritableDatabase();
                                base_Datos_motos.execSQL("DELETE FROM "+Utilidades.TABLA_MOTO+" WHERE id ="+variable_id);
                                Intent a = new Intent (EliminarMotocicleta.this, MainActivity.class);
                                Toast.makeText(EliminarMotocicleta.this, "Los datos han sido eliminados", Toast.LENGTH_SHORT).show();
                                startActivity(a);
                                finish();
                            }
                        });
                    }
            }
            while(cursor.moveToNext());
        }
        base_Datos_motos.close();
    }

    private TableRow.LayoutParams newTableRowsParams(){//Parametros de la tabla.
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(1,1,1,1);
        parametros.weight=1;
        return parametros;
    }
}