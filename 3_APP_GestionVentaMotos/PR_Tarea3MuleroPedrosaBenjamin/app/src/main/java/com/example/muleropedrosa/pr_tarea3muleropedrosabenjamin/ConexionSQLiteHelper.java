package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

//Esta clase corresponde a la creación de la base de datos con Sqlite.

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    final String CREAR_TABLA_MOTO="CREATE TABLE motos (id INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT, modelo TEXT, km TEXT, anio TEXT, precio TEXT, cv TEXT, cc TEXT, vendido TEXT)";
    //final String CREAR_TABLA_MOTO="CREATE TABLE motos (marca TEXT, modelo TEXT, km TEXT, anio TEXT, cc TEXT, cv TEXT, precio TEXT, vendido TEXT)";
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos) {
        baseDatos.execSQL(CREAR_TABLA_MOTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDatos, int versionAntigua, int versionNueva) {
        baseDatos.execSQL("DROP TABLE IF EXISTS motos");
        onCreate(baseDatos);
    }
}
