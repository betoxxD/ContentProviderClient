package com.example.clientecontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAdd;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnOpenAddActivity);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });

        // Inicializar reciclerView
        Vector<Usuario> usuarios = new Vector<>();
        recyclerView = findViewById(R.id.rvMain);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        Cursor cursor = getContentResolver().query(MiProveedorContenidoContract.Usuarios.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            Usuario usuario = new Usuario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            usuarios.add(usuario);
            // Log.d("MICP",cursor.getInt(0) +"-"+ cursor.getString(2));
        }
        ElementAdapter elementAdapter = new ElementAdapter(this, usuarios);
        elementAdapter.setOnclickListener(
                v1 -> {
                    Toast.makeText(this, "Elemento seleccionado: " + recyclerView.getChildAdapterPosition(v1) , Toast.LENGTH_LONG).show();
                }
        );
        recyclerView.setAdapter(elementAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {


        return super.onCreateView(name, context, attrs);
    }
}