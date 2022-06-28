package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class zumos extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zumos);
        tv1=(TextView)findViewById(R.id.resultado_nar);
        tv2=(TextView)findViewById(R.id.resultado_man);
        tv3=(TextView)findViewById(R.id.resultado_mel);
        tv4=(TextView)findViewById(R.id.resultado_pi);
        tv5=(TextView)findViewById(R.id.resultado_san);
        tv6=(TextView)findViewById(R.id.resultado_uv);
    }
    public void volver (View v) {

        onBackPressed();
    }

    public void seguir (View view) {

        Intent a = new Intent (this, picoteo.class);

        //Reenvío variables registro
        a.putExtra("variable_nom", getIntent().getStringExtra("variable_nom"));
        a.putExtra("variable_ape", getIntent().getStringExtra("variable_ape"));
        a.putExtra("variable_tel", getIntent().getStringExtra("variable_tel"));
        a.putExtra("variable_spi", getIntent().getStringExtra("variable_spi"));
        a.putExtra("variable_dir", getIntent().getStringExtra("variable_dir"));

        //Envío varibles zumos
        a.putExtra("variable_nar", tv1.getText().toString());
        a.putExtra("variable_man", tv2.getText().toString());
        a.putExtra("variable_mel", tv3.getText().toString());
        a.putExtra("variable_pi", tv4.getText().toString());
        a.putExtra("variable_san", tv5.getText().toString());
        a.putExtra("variable_uv", tv6.getText().toString());

        //Advertencia de no seleccionar ningún zumo.
        if (tv1.getText().toString().equals("0")
                && tv2.getText().toString().equals("0")
                && tv3.getText().toString().equals("0")
                && tv4.getText().toString().equals("0")
                && tv5.getText().toString().equals("0")
                && tv6.getText().toString().equals("0")) {
            Toast.makeText(this, "No has elegido ningún zumo", Toast.LENGTH_LONG).show();
            startActivity(a);
        }

        startActivity(a);
    }
    public void limpiar (View view) {

        tv1.setText("0");
        tv2.setText("0");
        tv3.setText("0");
        tv4.setText("0");
        tv5.setText("0");
        tv6.setText("0");

    }

    public void calcu_nar (View view) {
        String valor_string=tv1.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
       // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_nar = String.valueOf(resultado);
        tv1.setText(resul_final_nar);
    }
    public void calcu_man (View view) {
        String valor_string=tv2.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_man = String.valueOf(resultado);
        tv2.setText(resul_final_man);
    }
    public void calcu_mel (View view) {
        String valor_string=tv3.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_mel = String.valueOf(resultado);
        tv3.setText(resul_final_mel);
    }
    public void calcu_pi (View view) {
        String valor_string=tv4.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_pi = String.valueOf(resultado);
        tv4.setText(resul_final_pi);
    }
    public void calcu_san (View view) {
        String valor_string=tv5.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_san = String.valueOf(resultado);
        tv5.setText(resul_final_san);
    }
    public void calcu_uv (View view) {
        String valor_string=tv6.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final_uv = String.valueOf(resultado);
        tv6.setText(resul_final_uv);
    }
}