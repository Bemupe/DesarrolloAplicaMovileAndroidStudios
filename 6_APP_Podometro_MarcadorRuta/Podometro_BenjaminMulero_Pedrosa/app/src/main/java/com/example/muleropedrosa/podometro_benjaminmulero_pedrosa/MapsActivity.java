package com.example.muleropedrosa.podometro_benjaminmulero_pedrosa;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.muleropedrosa.podometro_benjaminmulero_pedrosa.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SensorEventListener {

    //MAPA
    private GoogleMap mMap;//Objeto de mapa de GoogleMap
    private ActivityMapsBinding binding;
    private double latitud=0.0;//Variable double para almacenar la latitud
    private double longitud=0.0;//Variable double para almacenar la longitud
    private LatLng miUbicacion=null;//Objeto  con la longitud y la latitud donde se encuentra el usuario.
    private LatLng antiguaUbicacion=null;//Objeto con la longitud y la latitud donde se encontraba el usuario.

    //PODOMETRO
    private boolean sensorPresente;//Variable boolean el sensor está presente.
    private boolean running;//Variable boolean el sensor está presente.
    private TextView txtpasos;//Objeto TextView para ingresar los pasos del podómetro.
    private Integer contadorPasos = 0;//Variable que cuenta los pasos
    private SensorManager sensorManager;//Objeto de gestión de sensores
    private Sensor sensorCuentaPasos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MAPA
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocalizacion();

        //PODOMETRO
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//Mantenemos activo el dispositivo

        txtpasos=findViewById(R.id.pasos);//Unimos la funcionalidad con el cuadro de  texto para señalar los pasos

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);//Iniciamos el servicio de sensor

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null&&running==true ){//Si no se encuentra el sensor de contar pasos y running no esta en true el contador no se inicia. Si se encuentra el contador de pasos se inicia la cuenta.

            sensorCuentaPasos=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

            sensorPresente=true;
        }else {
           txtpasos.setText("Contador de pasos no presente");

           sensorPresente=false;
        }


    }

    //MAPA
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                if (latitud==0.0&&longitud==0.0){

                    latitud=location.getLatitude();

                    longitud=location.getLongitude();

                    antiguaUbicacion = new LatLng(latitud, longitud);

                } else {

                    antiguaUbicacion=miUbicacion;

                }

                miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));

                CameraPosition cameraPosition = new CameraPosition.Builder()

                        .target(miUbicacion)

                        .zoom(18)

                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                //Añadimos la línea de la ruta.
                mMap.addPolyline((new PolylineOptions()).add(antiguaUbicacion,miUbicacion).
                        // Grosor de la línea
                                width(5)
                        // Color de la línea
                        .color(Color.RED)
                        .geodesic(true));

            }


        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    //PODOMETRO
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor==sensorCuentaPasos){
           contadorPasos=(int) sensorEvent.values[0];
            txtpasos.setText(String.valueOf(contadorPasos));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected  void onResume(){
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this,sensorCuentaPasos,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected  void onPause(){
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,sensorCuentaPasos);
    }

    public void boton (View view){
        running=true;
    }

}

