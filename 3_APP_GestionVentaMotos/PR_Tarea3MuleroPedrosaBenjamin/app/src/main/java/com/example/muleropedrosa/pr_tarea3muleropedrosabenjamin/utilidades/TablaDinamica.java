package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.utilidades;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.R;
import java.util.ArrayList;

//Esta clase corresponde a una serie de contructores y métodos para la realización de una tabla dinámica y con la cual inicié el ejercicio,
//pero ante no poder encontrar la solución a iniciar una actividad en un constructor, la he dejado de forma informativa y para el
//uso de la creación de la cabecera de las tablas.

public class TablaDinamica {
    private TableLayout tableLayout;
    private Context context;
    private String[] contenido;
    private ArrayList<String[]>datos;
    private TableRow filaTabla;
    private TextView txtCelda;
    private int indiceC;



    public TablaDinamica (TableLayout tableLayout, Context context){
        this.tableLayout=tableLayout;
        this.context=context;
    }

    public void añadirCabecera (String[]cabeza){
        this.contenido=cabeza;
        crearCabeza();
    }

    public void añadirFilaImagenVendido (String[]lista){
        this.contenido=lista;
        crearFilaImagenVendido();
    }

    private void crearCabeza(){
        indiceC=0;
        nuevaFila();

        while (indiceC<contenido.length){
            CeldaCabeza();
            txtCelda.setText((contenido[indiceC++]));
            filaTabla.addView(txtCelda, newTableRowsParams());
        }
        tableLayout.addView(filaTabla);
    }

    private void crearFilaImagenVendido(){
        indiceC=0;
        nuevaFila();
        nuevaCeldaImagenVendido();
        filaTabla.addView(txtCelda,newTableRowsParams());
        while (indiceC<contenido.length){
            nuevaCelda();
            txtCelda.setText((contenido[indiceC++]));
            filaTabla.addView(txtCelda, newTableRowsParams());
        }
        tableLayout.addView(filaTabla);

    }

    private void crearFilaImagenDisponible(){
        indiceC=0;
        nuevaFila();
        nuevaCeldaImagenDisponible();
        filaTabla.addView(txtCelda,newTableRowsParams());

        while (indiceC<contenido.length){
            nuevaCelda();
            txtCelda.setText((contenido[indiceC++]));
            filaTabla.addView(txtCelda, newTableRowsParams());
        }
        tableLayout.addView(filaTabla);
    }

    public void nuevaFila(){
        filaTabla=new TableRow(context);
        filaTabla.setBackgroundColor(Color.WHITE);

    }

    public void CeldaCabeza(){
        txtCelda=new TextView(context);
        txtCelda.setGravity(Gravity.CENTER);
        txtCelda.setTextSize(14);
        txtCelda.setTextColor(Color.WHITE);
        txtCelda.setBackgroundColor(Color.BLACK);

    }
    public void nuevaCelda(){
        txtCelda=new TextView(context);
        txtCelda.setGravity(Gravity.CENTER);
        txtCelda.setTextSize(14);
        txtCelda.setTextColor(Color.BLACK);
        txtCelda.setBackgroundColor(Color.WHITE);

    }
    public void nuevaCeldaImagenVendido(){
        txtCelda=new TextView(context);
        txtCelda.setCompoundDrawablesWithIntrinsicBounds( R.drawable.no_disponible, 0, 0, 0);
        txtCelda.setGravity(Gravity.CENTER);
        txtCelda.setTextSize(14);
        txtCelda.setBackgroundColor(Color.WHITE);
    }

    public void nuevaCeldaImagenDisponible(){
        txtCelda=new TextView(context);
        txtCelda.setCompoundDrawablesWithIntrinsicBounds( R.drawable.disponible, 0, 0, 0);
        txtCelda.setGravity(Gravity.CENTER);
        txtCelda.setTextSize(14);
        txtCelda.setBackgroundColor(Color.WHITE);
    }

    private TableRow.LayoutParams newTableRowsParams(){
        TableRow.LayoutParams parametros= new TableRow.LayoutParams();
        parametros.setMargins(1,1,1,1);
        parametros.weight=1;


        return parametros;


    }




}
