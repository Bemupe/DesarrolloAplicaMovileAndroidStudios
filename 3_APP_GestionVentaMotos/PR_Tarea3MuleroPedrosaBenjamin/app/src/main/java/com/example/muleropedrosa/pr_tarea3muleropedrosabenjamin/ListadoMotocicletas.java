package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades.TablaDinamica;


public class ListadoMotocicletas extends AppCompatActivity {
    Toolbar toolbar;
    private Boolean check_preferencia;
    private TableLayout tableLayout;
    private String[] tit_cabecera={"Dispo.","Id","Marca","Modelo","KM","Año","Precio"};
    private String[] filas=new String [6];
    private String str_check_preferencia;
    private String vendido;
    private String id;
    private String contarRegistros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_motocicletas);
        toolbar = findViewById(R.id.toolbar_2);
        setSupportActionBar(toolbar);
        tableLayout=(TableLayout)findViewById(R.id.tabla) ;

        //Creamos la cabecera de la tabla
        TablaDinamica tablaListado=new TablaDinamica(tableLayout,getApplicationContext());
        tablaListado.añadirCabecera(tit_cabecera);

        //Abrimos conexión con base de datos y creamos cursor
        ConexionSQLiteHelper con=new ConexionSQLiteHelper(this, "baseDatos_motos",null,1);
        SQLiteDatabase base_Datos_motos=con.getReadableDatabase();


        //Obtenemos e introducimos el valor booleano del checkbox de preferencias en una variable
        check_preferencia=PreferenceManager.getDefaultSharedPreferences(this).getBoolean("prefe", true);
        //Pasamos a string el valor booleano de la variable anterior
        str_check_preferencia=check_preferencia.toString();

        //Contamos el número de registros para ver si está vacío. Si está vacío se informa al usuario con un Toast.
        Cursor cursor_contar_registros = base_Datos_motos.rawQuery(" SELECT count(id) FROM motos ", null);
        if (cursor_contar_registros.moveToFirst())
        {contarRegistros = cursor_contar_registros .getString(0);  } while(cursor_contar_registros.moveToNext());
        if (contarRegistros.equals("0")){ Toast.makeText(this, "Base de datos está vacía, inserta alguna moto", Toast.LENGTH_SHORT).show();}


        //Usamos un condicional para el caso de que en preferencias este seleccionado o no el checkbox.
        if (str_check_preferencia=="false")//Si preferencias no está seleccionada(falso)
        {   //Se abre el cursor para seleccionar los datos de la tabla de la base de datos.
            Cursor cursor = base_Datos_motos.rawQuery(" SELECT * FROM motos ", null);
            if (cursor.moveToFirst())
            {
            //Recorremos el cursor hasta que no haya más registros y durante el recorrido de registros vamos creando las tabla. (do-while)
                do
                    {   //Utilizamo un for para obtener cada fila de la base de datos y la introducimos en un Array de string.
                        for (int i=0;i< filas.length;i++)
                        {   //Guardamos el contenido de fila en un array
                            filas[i] = cursor.getString(i);
                            //Guardamos en una variable string si la moto esta vendida no no
                            vendido=cursor.getString(8);
                        }
                        if (vendido.equals("0"))//Dependiendo de si está vendida o no se añade una fila con un icono distinto
                        {
                           //Se crea la fila (TableRow) y la primera celda (TextView) donde introducimos la imagen (disponible) y damos formato .

                            int indiceC=0;
                            TableRow filaTabla=new TableRow(this);
                            TextView txtCelda=new TextView(this);
                            filaTabla.setBackgroundColor(Color.GREEN);
                            txtCelda.setCompoundDrawablesWithIntrinsicBounds( R.drawable.disponible, 0, 0, 0);
                            txtCelda.setGravity(Gravity.CENTER);
                            txtCelda.setBackgroundColor(Color.WHITE);
                            filaTabla.addView(txtCelda,newTableRowsParams());

                            //Se crean el resto de celdas (desde la segunda a la septima) y se introduce la información del array en la celdas. Se da formato.
                            while (indiceC<filas.length){
                                txtCelda=new TextView(this);
                                txtCelda.setGravity(Gravity.CENTER);
                                txtCelda.setTextSize(14);
                                txtCelda.setTextColor(Color.BLACK);
                                txtCelda.setBackgroundColor(Color.WHITE);
                                txtCelda.setText((filas[indiceC++]));
                                filaTabla.addView(txtCelda,newTableRowsParams());
                            }
                            //Se introuduce la fila (TableRow) en la tabla (TableLayout)
                            tableLayout.addView(filaTabla);

                            //Con setOnClickListener, si pulsamos cualquier fila, pasamos a la actividad de modificación de ficha de la moto.
                            filaTabla.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent a = new Intent (ListadoMotocicletas.this, FichaMoto.class);
                                    //Buscamos el hijo de la tabla seleccionada donde se encuentra el id del registro (que lo identifica)
                                    TextView firstTextView = (TextView) filaTabla.getChildAt(1);
                                    //Lo sacamos del TextView y lo ingresamos en una variable string que enviamos a la siguiente actividad.
                                    id= firstTextView.getText().toString();
                                    a.putExtra("variable_id", id);
                                    startActivity(a);
                                    finish();
                                }
                            });

                        }
                        else
                            {
                                int indiceC=0;
                                TableRow filaTabla=new TableRow(this);
                                filaTabla.setBackgroundColor(Color.RED);
                                TextView txtCelda=new TextView(this);
                                txtCelda.setCompoundDrawablesWithIntrinsicBounds( R.drawable.no_disponible, 0, 0, 0);
                                txtCelda.setGravity(Gravity.CENTER);
                                txtCelda.setBackgroundColor(Color.WHITE);
                                filaTabla.addView(txtCelda,newTableRowsParams());


                                while (indiceC<filas.length){
                                    txtCelda=new TextView(this);
                                    txtCelda.setGravity(Gravity.CENTER);
                                    txtCelda.setTextSize(14);
                                    txtCelda.setTextColor(Color.BLACK);
                                    txtCelda.setBackgroundColor(Color.WHITE);
                                    txtCelda.setText((filas[indiceC++]));
                                    filaTabla.addView(txtCelda,newTableRowsParams());
                                }

                                tableLayout.addView(filaTabla);
                                filaTabla.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent a = new Intent (ListadoMotocicletas.this, FichaMoto.class);
                                        TextView firstTextView = (TextView) filaTabla.getChildAt(1);
                                        id= firstTextView.getText().toString();
                                        a.putExtra("variable_id", id);
                                        startActivity(a);
                                        finish();
                                    }
                                });
                            }


                    } while(cursor.moveToNext());
            }

        }
        else
            {
                Cursor cursor = base_Datos_motos.rawQuery("SELECT * FROM motos WHERE vendido=1", null);
            if (cursor.moveToFirst())
            {
                do
                    {
                        for (int i=0;i< filas.length;i++)
                        {
                            filas[i]=cursor.getString(i);
                            vendido=cursor.getString(8);

                        }

                        int indiceC=0;
                        TableRow filaTabla=new TableRow(this);
                        filaTabla.setBackgroundColor(Color.RED);
                        TextView txtCelda=new TextView(this);
                        txtCelda.setCompoundDrawablesWithIntrinsicBounds( R.drawable.no_disponible, 0, 0, 0);
                        txtCelda.setGravity(Gravity.CENTER);
                        txtCelda.setTextSize(14);
                        txtCelda.setBackgroundColor(Color.WHITE);
                        filaTabla.addView(txtCelda,newTableRowsParams());


                        while (indiceC<filas.length){
                            txtCelda=new TextView(this);
                            txtCelda.setGravity(Gravity.CENTER);
                            txtCelda.setTextSize(14);
                            txtCelda.setTextColor(Color.BLACK);
                            txtCelda.setBackgroundColor(Color.WHITE);
                            txtCelda.setText((filas[indiceC++]));
                            filaTabla.addView(txtCelda,newTableRowsParams());
                        }

                        tableLayout.addView(filaTabla);
                        filaTabla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent a = new Intent (ListadoMotocicletas.this, FichaMoto.class);
                                TextView firstTextView = (TextView) filaTabla.getChildAt(1);
                                id= firstTextView.getText().toString();
                                a.putExtra("variable_id", id);
                                startActivity(a);
                                finish();
                            }
                        });
                    }
                while(cursor.moveToNext());
            }
            };
        base_Datos_motos.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Se añade (menu_listado.xml) el menú listado.
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//Según la opción del menú, vamos a una
        // actividad determinada. Según la opción seleccionada, informamos con toast.
        int id = item.getItemId();

        if (id == R.id.listado) {
            Toast.makeText(this, "Preferencias", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(ListadoMotocicletas.this, Preferencias.class);
            startActivity(a);
            finish();

            return true;
        } else if (id == R.id.insert_moto) {
            Toast.makeText(this, "Insertar motocicleta", Toast.LENGTH_SHORT).show();

            //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new AjustesFragment()).commit();
            Intent a = new Intent(ListadoMotocicletas.this, InsertarMotocicleta.class);
            startActivity(a);
            finish();
            return true;
        } else if (id == R.id.elimi_moto) {
            Toast.makeText(this, "Eliminar motocicleta", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(ListadoMotocicletas.this, EliminarMotocicleta.class);
            startActivity(a);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);

    }
    private TableRow.LayoutParams newTableRowsParams(){
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(1,1,1,1);
        parametros.weight=1;


        return parametros;


    }
}