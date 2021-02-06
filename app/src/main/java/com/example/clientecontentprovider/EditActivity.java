package com.example.clientecontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnAgregar;
    Button btnEliminar;
    EditText txtName;
    EditText txtPass;
    EditText txtEmail;
    EditText txtTel;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnAgregar = findViewById(R.id.btnAdd);
        btnEliminar = findViewById(R.id.btnEliminar);
        txtName = findViewById(R.id.txtName);
        txtPass = findViewById(R.id.txtPass);
        txtEmail = findViewById(R.id.txtEmail);
        txtTel = findViewById(R.id.txtTel);

        Intent intent = getIntent();
        id = intent.getLongExtra("id",-1);

        if(id != -1) {
            btnAgregar.setText("Modificar");
            btnEliminar.setVisibility(View.VISIBLE);
            Usuario usuario = null;
            Cursor cursor = getContentResolver().query(MiProveedorContenidoContract.Usuarios.CONTENT_URI.buildUpon().appendPath(id+"").build(),null,null,null,null);
            while (cursor.moveToNext()){
                usuario = new Usuario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            }
            txtName.setText(usuario.getName());
            txtPass.setText(usuario.getPass());
            txtEmail.setText(usuario.getEmail());
            txtTel.setText(usuario.getTel());
        }
        ContentResolver cr = getContentResolver();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(MiProveedorContenidoContract.Usuarios.NOMBRE,txtName.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.PASS,txtPass.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.EMAIL,txtEmail.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.TELEFONO,txtTel.getText().toString());
                if(id == -1){
                    Uri uriResultado = cr.insert(MiProveedorContenidoContract.Usuarios.CONTENT_URI,cv);
                    Log.d("MICP",uriResultado.toString());
                    finish();
                }else {
                    String args[] = {id+""};
                    int uriResultado = cr.update(MiProveedorContenidoContract.Usuarios.CONTENT_URI.buildUpon().appendPath(id+"").build(),cv,null,null);
                    Log.d("MICP",uriResultado+"");
                    finish();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uriResultado = cr.delete(MiProveedorContenidoContract.Usuarios.CONTENT_URI.buildUpon().appendPath(id+"").build(),null,null);
                if(uriResultado == 1) {
                    Toast.makeText(EditActivity.this, "Eliminado correctamente.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(EditActivity.this, "No se pudo eliminar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}