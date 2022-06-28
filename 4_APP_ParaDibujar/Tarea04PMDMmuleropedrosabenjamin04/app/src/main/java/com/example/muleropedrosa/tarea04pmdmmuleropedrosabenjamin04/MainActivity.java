package com.example.muleropedrosa.tarea04pmdmmuleropedrosabenjamin04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer cancion;//Variable media player


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Funcionamiento del MediaPlayer. Creamos un objeto mediaplayer en el onCreate de la pantalla pincipal, para que
        //reproduzca el archivo de sonido en la carpeta "raw" en bucle y desde el inicio de la aplicación.
        cancion = MediaPlayer.create(this, R.raw.chopin_prelude);
        cancion.setLooping(true);//Bucle musical.
        cancion.start();//Inicio de la canción.
    }

    //Funcionalidad boton pintar, que abre la nueva actividad.
    public void dibujar(View view) {
        Intent a = new Intent (this, Pintando.class);
        startActivity(a);
    }

    //Si se pulsa el boton de salir del movil, se cierra el activity y la música.
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (cancion != null && cancion.isPlaying()) {
            cancion.stop();
            cancion.release();
        }


    }
}