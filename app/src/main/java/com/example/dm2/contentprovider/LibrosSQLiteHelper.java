package com.example.dm2.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dm2 on 12/01/2018.
 */

public class LibrosSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate =
            "CREATE TABLE Libros (idLibro INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT, genero TEXT)";
    public LibrosSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL("Insert into Libros (titulo,autor,genero) VALUES ('Cosasnasis','Unnasi','Politica')");
        db.execSQL("Insert into Libros (titulo,autor,genero) VALUES ('Cosasnonasis','Unnonasi','Fantasia')");
        db.execSQL("Insert into Libros (titulo,autor,genero) VALUES ('La vida de un gato','Un gato','Miau')");
        db.execSQL("Insert into Libros (titulo,autor,genero) VALUES ('Eragon','Naniiii','Unpocomalo')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL(sqlCreate);
    }
}