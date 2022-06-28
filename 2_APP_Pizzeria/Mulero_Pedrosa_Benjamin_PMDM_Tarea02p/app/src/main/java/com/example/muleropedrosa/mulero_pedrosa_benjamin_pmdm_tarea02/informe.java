package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class informe extends AppCompatActivity {
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv16;
    private TextView tv17;
    private TextView tv18;
    private TextView tv19;
    private TextView tv20;
    private TextView tv21;
    private TextView tv22;
    private TextView tv23;
    private TextView tv24;
    private TextView tv25;
    private TextView tv26;
    private TextView tv27;
    private TextView tv28;
    private TextView tv29;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        tv1 = (TextView) findViewById(R.id.fecha);
        tv1.setText(new Date().toString());

        //Establecemos la variables a usar para realizar el calculo de los totales del informe

        double pre_nar, pre_man, pre_mel, pre_pi, pre_san, pre_uv, pre_cac, pre_ace, pre_pat;
        double dcant_nar, dcant_man, dcant_mel, dcant_pi, dant_san, dcant_uv, dcant_cac, dcant_ace, dcant_pat;
        double rpre_nar, rpre_man, rpre_mel, rpre_pi, rpre_san, rpre_uv, rpre_cac, rpre_ace, rpre_pat;
        int cant_nar, cant_man, cant_mel, cant_pi, cant_san, cant_uv, cant_cac, cant_ace, cant_pat;

        //METEMOS DATOS DE REGISTRO EN EL INFORME
        String variable_nom = getIntent().getStringExtra("variable_nom");
        String variable_ape = getIntent().getStringExtra("variable_ape");
        String variable_tel = getIntent().getStringExtra("variable_tel");
        String variable_spi = getIntent().getStringExtra("variable_spi");
        String variable_dir = getIntent().getStringExtra("variable_dir");

        tv0 = (TextView) findViewById(R.id.recogida);
        tv2 = (TextView) findViewById(R.id.nombre);
        tv3 = (TextView) findViewById(R.id.apellido);
        tv4 = (TextView) findViewById(R.id.telefono);

        tv5 = (TextView) findViewById(R.id.direccion);
        tv0.setText(variable_spi);
        tv2.setText(variable_nom);
        tv3.setText(variable_ape);
        tv4.setText(variable_tel);

        //Ponemos la condición de si se elige en el spinner "A domicilio" aparecerá la dirección
        if (variable_spi.equals("A domicilio")) {
            tv5.setText(variable_dir);
        }

        //RECUPERAMOS LAS VARIABLES ARRASTRASDAS DE LAS CANTIDADES POR CADA PRODUCTO Y LAS OPCIONES CELIACO Y INTORLERANTE A LA LACTOSA
        //Datos productos
        //Zumos
        String variable_nar = getIntent().getStringExtra("variable_nar");
        String variable_man = getIntent().getStringExtra("variable_man");
        String variable_mel = getIntent().getStringExtra("variable_mel");
        String variable_pi = getIntent().getStringExtra("variable_pi");
        String variable_san = getIntent().getStringExtra("variable_san");
        String variable_uv = getIntent().getStringExtra("variable_uv");

        //Picoteo
        String variable_cac = getIntent().getStringExtra("variable_cac");
        String variable_pat = getIntent().getStringExtra("variable_pat");
        String variable_ace = getIntent().getStringExtra("variable_ace");


        Boolean variable_celiac = getIntent().getExtras().getBoolean("variable_celiac");
        Boolean variable_lacto = getIntent().getExtras().getBoolean("variable_lacto");

        tv6 = (TextView) findViewById(R.id.naranja);
        tv7 = (TextView) findViewById(R.id.manzana);
        tv8 = (TextView) findViewById(R.id.melocoton);
        tv9 = (TextView) findViewById(R.id.pina);
        tv10 = (TextView) findViewById(R.id.sandia);
        tv11 = (TextView) findViewById(R.id.uva);
        tv12 = (TextView) findViewById(R.id.cacahuetes);
        tv13 = (TextView) findViewById(R.id.patatas);
        tv14 = (TextView) findViewById(R.id.aceitunas);

        //Establecer condiciones según la elección de celiaco o sin lactosa y se introducen cantidades en los TextView (contenedores)

        //PREPARACIÓN DE VARIABLES PARA EL CÁLCULO TOTAL
        //Establecimiento de variables para el cálculo (cantidadxproducto).
        // Paso de string a integer y de integer a double de la cantidades.
        //Establecimiento de precio unitario.
        cant_nar=Integer.parseInt(variable_nar);
        cant_man=Integer.parseInt(variable_man);
        cant_mel=Integer.parseInt(variable_mel);
        cant_pi=Integer.parseInt(variable_pi);
        cant_san=Integer.parseInt(variable_san);
        cant_uv=Integer.parseInt(variable_uv);
        cant_cac=Integer.parseInt(variable_cac);
        cant_ace=Integer.parseInt(variable_ace);
        cant_pat=Integer.parseInt(variable_pat);

        dcant_nar=Double.valueOf(cant_nar);
        dcant_man=Double.valueOf(cant_man);
        dcant_mel=Double.valueOf(cant_mel);
        dcant_pi=Double.valueOf(cant_pi);
        dant_san=Double.valueOf(cant_san);
        dcant_uv=Double.valueOf(cant_uv);
        dcant_cac=Double.valueOf(cant_cac);
        dcant_ace=Double.valueOf(cant_ace);
        dcant_pat=Double.valueOf(cant_pat);

        pre_nar=0.50;
        pre_man=0.50;
        pre_mel=0.50;
        pre_pi=0.50;
        pre_san=0.50;
        pre_uv=0.50;
        pre_cac=0.10;
        pre_ace=0.10;
        pre_pat=0.10;

        //Calculo de cantidades (cantidadxproducto) por producto.
        rpre_nar=Math.round((dcant_nar*pre_nar)*100.0)/100.0;
        rpre_man=Math.round((dcant_man*pre_man)*100.0)/100.0;
        rpre_mel=Math.round((dcant_mel*pre_mel)*100.0)/100.0;
        rpre_pi=Math.round((dcant_pi*pre_pi)*100.0)/100.0;
        rpre_san=Math.round((dant_san*pre_san)*100.0)/100.0;
        rpre_uv=Math.round((dcant_uv*pre_uv)*100.0)/100.0;
        rpre_cac=Math.round((dcant_cac*pre_cac)*100.0)/100.0;
        rpre_ace=Math.round((dcant_ace*pre_ace)*100.0)/100.0;
        rpre_pat=Math.round((dcant_pat*pre_pat)*100.0)/100.0;




        //ESTABLECIMIENTO DE CONDICIONALES SEGÚN LA ELECCIÓN DE INTOLERANTE A LA LACTOSA O CELIACO.
        //Por cada condición cumplida, paso a String e introducción en TexView (etiquetas) de cantidad por precio y cálculos totales.
        if (variable_lacto.toString() == "false"&&variable_celiac.toString() == "false"){
            //Resultados si celiavo "false" y "lactosa" false.
            tv6.setText(variable_nar);
            tv7.setText(variable_man);
            tv8.setText(variable_mel);
            tv9.setText(variable_pi);
            tv10.setText(variable_san);
            tv11.setText(variable_uv);
            tv12.setText(variable_cac);
            tv14.setText(variable_ace);
            tv13.setText(variable_pat);

            String resul_pre_nar= String.valueOf(rpre_nar);
            String resul_pre_man=String.valueOf(rpre_man);;
            String resul_pre_mel=String.valueOf(rpre_mel);;
            String resul_pre_pi=String.valueOf(rpre_pi);;
            String resul_pre_san=String.valueOf(rpre_san);;
            String resul_pre_uv=String.valueOf(rpre_uv);;
            String resul_pre_cac=String.valueOf(rpre_cac);;
            String resul_pre_ace=String.valueOf(rpre_ace);;
            String resul_pre_pat=String.valueOf(rpre_pat);;

            tv15 = (TextView) findViewById(R.id.pre_naranja);
            tv16 = (TextView) findViewById(R.id.pre_manzana);
            tv17 = (TextView) findViewById(R.id.pre_melocoton);
            tv18 = (TextView) findViewById(R.id.pre_piña);
            tv19 = (TextView) findViewById(R.id.pre_sandia);
            tv20 = (TextView) findViewById(R.id.pre_uva);
            tv21 = (TextView) findViewById(R.id.pre_cacahuete);
            tv22 = (TextView) findViewById(R.id.pre_patatas);
            tv23 = (TextView) findViewById(R.id.pre_aceitunas);

            tv15.setText(resul_pre_nar);
            tv16.setText(resul_pre_man);
            tv17.setText(resul_pre_mel);
            tv18.setText(resul_pre_pi);
            tv19.setText(resul_pre_san);
            tv20.setText(resul_pre_uv);
            tv21.setText(resul_pre_cac);
            tv22.setText(resul_pre_pat);
            tv23.setText(resul_pre_ace);


            //Cálculo de los totales según condicionales a domicilio o establecimiento

            if (variable_spi.equals("A domicilio"))  {
                double coste_domicilio=2.30;
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel+rpre_pi+rpre_san+rpre_uv)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace+rpre_pat)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico+coste_domicilio)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);
                String domicilio=String.valueOf(coste_domicilio);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);
                tv29 = (TextView) findViewById(R.id.coste_domicilio);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);
                tv29.setText(domicilio); }

            if (variable_spi.equals("Establecimiento"))  {
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel+rpre_pi+rpre_san+rpre_uv)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace+rpre_pat)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);}
        }


        if (variable_lacto.toString() == "true"&&variable_celiac.toString() == "true") {
            tv6.setText(variable_nar);
            tv7.setText(variable_man);
            tv8.setText(variable_mel);
            tv12.setText(variable_cac);
            tv14.setText(variable_ace);

            String resul_pre_nar= String.valueOf(rpre_nar);
            String resul_pre_man=String.valueOf(rpre_man);;
            String resul_pre_mel=String.valueOf(rpre_mel);;
            String resul_pre_cac=String.valueOf(rpre_cac);;
            String resul_pre_ace=String.valueOf(rpre_ace);;

            tv15 = (TextView) findViewById(R.id.pre_naranja);
            tv16 = (TextView) findViewById(R.id.pre_manzana);
            tv17 = (TextView) findViewById(R.id.pre_melocoton);
            tv21 = (TextView) findViewById(R.id.pre_cacahuete);
            tv23 = (TextView) findViewById(R.id.pre_aceitunas);

            tv15.setText(resul_pre_nar);
            tv16.setText(resul_pre_man);
            tv17.setText(resul_pre_mel);
            tv21.setText(resul_pre_cac);
            tv23.setText(resul_pre_ace);

            //Cálculo de los totales según condicionales a domicilio o establecimiento

            if (variable_spi.equals("A domicilio"))  {
                double coste_domicilio=2.30;
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico+coste_domicilio)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);
                String domicilio=String.valueOf(coste_domicilio);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);
                tv29 = (TextView) findViewById(R.id.coste_domicilio);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);
                tv29.setText(domicilio); }

            if (variable_spi.equals("Establecimiento"))  {
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);}

        }

        if (variable_lacto.toString() == "true"&&variable_celiac.toString() == "false"){
            tv6.setText(variable_nar);
            tv7.setText(variable_man);
            tv8.setText(variable_mel);
            tv12.setText(variable_cac);
            tv14.setText(variable_ace);
            String resul_pre_nar= String.valueOf(rpre_nar);
            String resul_pre_man=String.valueOf(rpre_man);;
            String resul_pre_mel=String.valueOf(rpre_mel);;
            String resul_pre_cac=String.valueOf(rpre_cac);;
            String resul_pre_ace=String.valueOf(rpre_ace);;

            tv15 = (TextView) findViewById(R.id.pre_naranja);
            tv16 = (TextView) findViewById(R.id.pre_manzana);
            tv17 = (TextView) findViewById(R.id.pre_melocoton);
            tv21 = (TextView) findViewById(R.id.pre_cacahuete);
            tv23 = (TextView) findViewById(R.id.pre_aceitunas);

            tv15.setText(resul_pre_nar);
            tv16.setText(resul_pre_man);
            tv17.setText(resul_pre_mel);
            tv21.setText(resul_pre_cac);
            tv23.setText(resul_pre_ace);

            //Cálculo de los totales según condicionales a domicilio o establecimiento

            if (variable_spi.equals("A domicilio"))  {
                double coste_domicilio=2.30;
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico+coste_domicilio)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);
                String domicilio=String.valueOf(coste_domicilio);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);
                tv29 = (TextView) findViewById(R.id.coste_domicilio);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);
                tv29.setText(domicilio); }

            if (variable_spi.equals("Establecimiento"))  {
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);}

        }

        if (variable_lacto.toString() == "false"&&variable_celiac.toString() == "true"){
              tv6.setText(variable_nar);
              tv7.setText(variable_man);
              tv8.setText(variable_mel);
              tv9.setText(variable_pi);
              tv10.setText(variable_san);
              tv11.setText(variable_uv);
              tv12.setText(variable_cac);
              tv14.setText(variable_ace);

            String resul_pre_nar= String.valueOf(rpre_nar);
            String resul_pre_man=String.valueOf(rpre_man);;
            String resul_pre_mel=String.valueOf(rpre_mel);;
            String resul_pre_pi=String.valueOf(rpre_pi);;
            String resul_pre_san=String.valueOf(rpre_san);;
            String resul_pre_uv=String.valueOf(rpre_uv);;
            String resul_pre_cac=String.valueOf(rpre_cac);;
            String resul_pre_ace=String.valueOf(rpre_ace);;


            tv15 = (TextView) findViewById(R.id.pre_naranja);
            tv16 = (TextView) findViewById(R.id.pre_manzana);
            tv17 = (TextView) findViewById(R.id.pre_melocoton);
            tv18 = (TextView) findViewById(R.id.pre_piña);
            tv19 = (TextView) findViewById(R.id.pre_sandia);
            tv20 = (TextView) findViewById(R.id.pre_uva);
            tv21 = (TextView) findViewById(R.id.pre_cacahuete);
            tv23 = (TextView) findViewById(R.id.pre_aceitunas);

            tv15.setText(resul_pre_nar);
            tv16.setText(resul_pre_man);
            tv17.setText(resul_pre_mel);
            tv18.setText(resul_pre_pi);
            tv19.setText(resul_pre_san);
            tv20.setText(resul_pre_uv);
            tv21.setText(resul_pre_cac);
            tv23.setText(resul_pre_ace);


            //Cálculo de los totales según condicionales a domicilio o establecimiento

            if (variable_spi.equals("A domicilio"))  {
                double coste_domicilio=2.30;
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel+rpre_pi+rpre_san+rpre_uv)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico+coste_domicilio)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);
                String domicilio=String.valueOf(coste_domicilio);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);
                tv29 = (TextView) findViewById(R.id.coste_domicilio);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);
                tv29.setText(domicilio); }

            if (variable_spi.equals("Establecimiento"))  {
                double total_zum=Math.round((rpre_nar+rpre_man+rpre_mel+rpre_pi+rpre_san+rpre_uv)*100.0)/100.0;
                double total_pico=Math.round((rpre_cac+rpre_ace)*100.0)/100.0;
                double subtotal=Math.round((total_zum+total_pico)*100.0)/100.0;
                double iva_7=Math.round((subtotal*0.07)*100.0)/100.0;
                double iva_total=Math.round((subtotal*1.07)*100.0)/100.0;

                String total_1 = String.valueOf(total_zum);
                String total_2 = String.valueOf(total_pico);
                String total_3 = String.valueOf(subtotal);
                String total_4 = String.valueOf(iva_7);
                String pagar = String.valueOf(iva_total);

                tv24 = (TextView) findViewById(R.id.total_zumos);
                tv25 = (TextView) findViewById(R.id.total_picoteo);
                tv26 = (TextView) findViewById(R.id.subtotal);
                tv27 = (TextView) findViewById(R.id.iva);
                tv28 = (TextView) findViewById(R.id.total);

                tv24.setText(total_1);
                tv25.setText(total_2);
                tv26.setText(total_3);
                tv27.setText(total_4);
                tv28.setText(pagar);}

        }

    }

    public void reempezar (View v) {
        Intent h = new Intent (this, MainActivity.class);
        startActivity(h);
    }
}