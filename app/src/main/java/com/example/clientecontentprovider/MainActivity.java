package com.example.clientecontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentValues cv = new ContentValues();
        cv.put(MiProveedorContenidoContract.Usuarios.NOMBRE,"Jhony");
        cv.put(MiProveedorContenidoContract.Usuarios.PASS,"123");
        cv.put(MiProveedorContenidoContract.Usuarios.EMAIL,"ElJhon@gmail.com");
        cv.put(MiProveedorContenidoContract.Usuarios.TELEFONO,"445");
        ContentResolver cr = getContentResolver();
        Uri uriResultado = cr.insert(MiProveedorContenidoContract.Usuarios.CONTENT_URI,cv);

        Log.d("MICP",uriResultado.toString());

        Cursor cursor = getContentResolver().query(MiProveedorContenidoContract.Usuarios.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            Log.d("MICP",cursor.getInt(0) +"-"+ cursor.getString(2));
        }
    }
}