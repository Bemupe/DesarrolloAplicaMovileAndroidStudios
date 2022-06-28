package com.example.muleropedrosa.mulero_pedrosa_benjamin_pmdm_tarea02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
    }
    public void volver (View view) {

        onBackPressed();
    }
}