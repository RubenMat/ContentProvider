package com.example.dm2.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad2 extends AppCompatActivity {

    Button btnInsert,btnUpd,btnDel,btnList;
    TextView txtList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        txtList=(TextView)findViewById(R.id.txtListado);

        btnList=(Button)findViewById(R.id.btnListar);
        btnInsert=(Button)findViewById(R.id.btnInsertar);
        btnDel=(Button)findViewById(R.id.btnBorrar);
        btnUpd=(Button)findViewById(R.id.btnActualizar);

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues valores = new ContentValues();
                valores.put(LibrosProvider.Libros.COL_TITULO, "A Medias");
                getContentResolver().update(LibrosProvider.CONTENT_URI, valores,"autor='Lizar'", null);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(LibrosProvider.CONTENT_URI, "autor='Lizar'", null);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues valores = new ContentValues();
                valores.put(LibrosProvider.Libros.COL_TITULO,"Cosas a medias");
                valores.put(LibrosProvider.Libros.COL_AUTOR,"Lizar");
                valores.put(LibrosProvider.Libros.COL_GENERO,"Comedia");
                Uri nuevoElemento = getContentResolver().insert(LibrosProvider.CONTENT_URI, valores);

                Toast.makeText(getApplicationContext(),"Datos insertados" + nuevoElemento,Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Definimos los nombres de las columnas de la tabla a recuperar
                String [] projection = new String[] {
                        LibrosProvider.Libros._ID,
                        LibrosProvider.Libros.COL_TITULO,
                        LibrosProvider.Libros.COL_AUTOR,
                        LibrosProvider.Libros.COL_GENERO
                };

                Uri librosUri = LibrosProvider.CONTENT_URI;
                ContentResolver cr = getContentResolver();

                //Hacemos la consulta
                Cursor c = cr.query(librosUri,
                        projection, //Columnas a devolver
                        null, //Condición de la query
                        null, //Argumentos variables de la query
                        null //Orden de los resultados
                );

                if (c.moveToFirst()){
                    String titulo;
                    String autor;
                    String genero;
                    int colTitulo = c.getColumnIndex(LibrosProvider.Libros.COL_TITULO);
                    int colAutor = c.getColumnIndex(LibrosProvider.Libros.COL_AUTOR);
                    int colGenero = c. getColumnIndex(LibrosProvider.Libros.COL_GENERO);
                    txtList.setText("");
                    do {
                        titulo = c.getString(colTitulo);
                        autor = c.getString(colAutor);
                        genero = c.getString(colGenero);
                        txtList.append(titulo + " - "+ autor +" - "+genero+ "\n" );
                    } while (c.moveToNext());
                }
            }
        });

    }
}
