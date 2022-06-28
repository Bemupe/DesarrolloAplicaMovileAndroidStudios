package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class picoteo extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picoteo);
        tv1=(TextView)findViewById(R.id.resultado_cac);
        tv2=(TextView)findViewById(R.id.resultado_pat);
        tv3=(TextView)findViewById(R.id.resultado_ace);

    }

    public void volver (View v) {

        onBackPressed();
    }

    public void seguir(View view) {
        Intent b = new Intent (this, informe.class);


        //Reenvío variables registro
        b.putExtra("variable_nom", getIntent().getStringExtra("variable_nom"));
        b.putExtra("variable_ape", getIntent().getStringExtra("variable_ape"));
        b.putExtra("variable_tel", getIntent().getStringExtra("variable_tel"));
        b.putExtra("variable_spi", getIntent().getStringExtra("variable_spi"));
        b.putExtra("variable_dir", getIntent().getStringExtra("variable_dir"));

        //Reenvío variables zumos
        b.putExtra("variable_nar", getIntent().getStringExtra("variable_nar"));
        b.putExtra("variable_man", getIntent().getStringExtra("variable_man"));
        b.putExtra("variable_mel", getIntent().getStringExtra("variable_mel"));
        b.putExtra("variable_pi", getIntent().getStringExtra("variable_pi"));
        b.putExtra("variable_san", getIntent().getStringExtra("variable_san"));
        b.putExtra("variable_uv", getIntent().getStringExtra("variable_uv"));


        //Envío variables picoteo
        CheckBox celiaco = findViewById(R.id.checkBox_celiaco);
        CheckBox lactosa = findViewById(R.id.checkBox_lactosa);
        //boolean celi = celiaco.isChecked();
        b.putExtra("variable_cac", tv1.getText().toString());
        b.putExtra("variable_pat", tv2.getText().toString());
        b.putExtra("variable_ace", tv3.getText().toString());
        b.putExtra("variable_celiac", celiaco.isChecked());
        b.putExtra("variable_lacto", lactosa.isChecked());

        startActivity(b);
    }
    public void limpiar (View view) {

        tv1.setText("0");
        tv2.setText("0");
        tv3.setText("0");

    }

    public void calcu_ac (View view) {
        String valor_string=tv3.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final = String.valueOf(resultado);
        tv3.setText(resul_final);
    }

    public void calcu_pat (View view) {
        String valor_string=tv2.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final = String.valueOf(resultado);
        tv2.setText(resul_final);
    }

    public void calcu_cac (View view) {
        String valor_string=tv1.getText().toString();
        int valor_int=Integer.parseInt(valor_string);
        int var1=1;
        // int var2=2;
        int resultado= var1+valor_int;
        String resul_final = String.valueOf(resultado);
        tv1.setText(resul_final);
    }
}