package com.example.muleropedrosa.tarea04pmdmmuleropedrosabenjamin04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Pintando extends AppCompatActivity {

    Lienzo lienzo;
    ImageButton botonAmarillo, botonRojo, botonAzul, botonCara, botonEstrella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        lienzo=(Lienzo)findViewById(R.id.id_lienzo);
        botonAmarillo=(ImageButton) findViewById(R.id.id_boton_amarillo);
        botonRojo=(ImageButton) findViewById(R.id.id_boton_rojo);
        botonAzul=(ImageButton) findViewById(R.id.id_boton_azul);
        botonCara=(ImageButton) findViewById(R.id.id_boton_cara);
        botonEstrella=(ImageButton) findViewById(R.id.id_boton_estrella);

    }


    //Metodo para el botón que dibuja en amarillo
   public void dibujaAmarillo(View view) {
        //Variable int cargada con el número correspondiente del color amarillo obtenida de los recursos.
       int colorAmarillo = getResources().getColor(R.color.amarillo);

       //Ponemos el pincel (paint) con el color amarillo
       lienzo.comoPintarDraw.setColor(colorAmarillo);

       //Establezco el color del circulo que se crea al primer click.
       lienzo.circulo.setColor(colorAmarillo);

       //Reseteamos el fondo de color (blanco) de los botones para dar sensación de nueva selección.
       resetBoton();

       //Ponemos BackgroundTint en color gris para dar sensación de seleccionar el botón que dibuja en amarillo
       botonAmarillo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gris)));

    }
    //Metodo para el botón que dibuja en azul.
    public void dibujaAzul(View view) {
        int colorAzul = getResources().getColor(R.color.azul);
        lienzo.comoPintarDraw.setColor(colorAzul);
        lienzo.circulo.setColor(colorAzul);
        resetBoton();
        botonAzul.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gris)));

    }

    //Metodo para el botón que dibuja en rojo.
    public void dibujaRojo (View view) {
        int colorRojo = getResources().getColor(R.color.rojo_dibuja);
        lienzo.comoPintarDraw.setColor(colorRojo);
        lienzo.circulo.setColor(colorRojo);
        resetBoton();
        botonRojo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gris)));

    }

    //Metodo para el botón que dibuja estrellas.
    public void pintaEstrella (View view) {
        int colorBlanco = getResources().getColor(R.color.white);
        lienzo.setPincelMapaBit(R.drawable.estrella_negra);
        lienzo.comoPintarDraw.setColor(colorBlanco);
        resetBoton();
        botonEstrella.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gris)));
    }

    //Metodo para el botón que dibuja cara.
    public void pintaMiCara (View view) {
        int colorBlanco = getResources().getColor(R.color.white);
        lienzo.setPincelMapaBit(R.drawable.benja_cara);
        lienzo.comoPintarDraw.setColor(colorBlanco);
        resetBoton();
        botonCara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gris)));

    }

    //Metodo para introducir en botón para volver hacia atrás.
    public void volverAtras (View view) {
        onBackPressed();
    }

    //Metodo para introducir en botón para limpiar el canvas.
    public void borrar(View view){
        int colorBlanco = getResources().getColor(R.color.white);
        lienzo.setBorrar(colorBlanco);
    }

    //Método para poner todos los botones con "setBackgroundTint" en blanco y dar sensación de selección-deselección.
    public void resetBoton (){
        botonAmarillo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        botonAzul.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        botonRojo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        botonEstrella.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        botonCara.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

    }

}