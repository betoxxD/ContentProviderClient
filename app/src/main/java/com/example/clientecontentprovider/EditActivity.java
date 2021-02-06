package com.example.clientecontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Button btnAgregar;
    EditText txtName;
    EditText txtPass;
    EditText txtEmail;
    EditText txtTel;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = -1;
        btnAgregar = findViewById(R.id.btnAdd);
        txtName = findViewById(R.id.txtName);
        txtPass = findViewById(R.id.txtPass);
        txtEmail = findViewById(R.id.txtEmail);
        txtTel = findViewById(R.id.txtTel);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(MiProveedorContenidoContract.Usuarios.NOMBRE,txtName.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.PASS,txtPass.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.EMAIL,txtEmail.getText().toString());
                cv.put(MiProveedorContenidoContract.Usuarios.TELEFONO,txtTel.getText().toString());
                ContentResolver cr = getContentResolver();
                if(id == -1){
                    Uri uriResultado = cr.insert(MiProveedorContenidoContract.Usuarios.CONTENT_URI,cv);
                    Log.d("MICP",uriResultado.toString());
                }else {
                    String args[] = {id+""};
                    int uriResultado = cr.update(MiProveedorContenidoContract.Usuarios.CONTENT_URI,cv,MiProveedorContenidoContract.Usuarios._ID + "LIKE ?", args);
                    Log.d("MICP",uriResultado+"");
                }
            }
        });
    }
}