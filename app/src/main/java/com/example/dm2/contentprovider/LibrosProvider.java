package com.example.dm2.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by dm2 on 26/01/2018.
 */



public class LibrosProvider extends ContentProvider{

    private static final String uri ="content://com.example.dm2.contentprovider/libros";
    public static final Uri CONTENT_URI= Uri.parse(uri);

    private LibrosSQLiteHelper librosDBH;
    private static final String BD_NOMBRE = "DBLibros";
    private static final int BD_VERSION = 1;
    private static final String TABLA_LIBROS = "Libros";

    //Necesario para UriMatcher
    private static final int LIBROS = 1;
    private static final int LIBROS_ID = 2;
    private static final UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.dm2.contentprovider","libros", LIBROS);
        uriMatcher.addURI("com.example.dm2.contentprovider","libros/#", LIBROS_ID);
    }


    //titulo,autor,genero

    public static final class Libros implements BaseColumns {
        private Libros () {}
        //Nombres de columnas
        public static final String _ID = "idLibro";
        public static final String COL_TITULO = "titulo";
        public static final String COL_AUTOR = "autor";
        public static final String COL_GENERO = "genero";
    }

    @Override
    public boolean onCreate() {
        //inicializar la base de datos a traves de su nombre y versión

        librosDBH = new LibrosSQLiteHelper(getContext(), BD_NOMBRE,null, BD_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri)== LIBROS_ID) {
            where ="_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = librosDBH.getWritableDatabase();
        Cursor c = db.query(TABLA_LIBROS, projection, where, selectionArgs, null, null,sortOrder);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case LIBROS:
                return "vnd.android.cursor.dir/vnd.tabla.libros";
            case LIBROS_ID:
                return "vnd.android.cursor.item/vnd.tabla.libros";
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long regId = 1;
        SQLiteDatabase db = librosDBH.getWritableDatabase();
        regId = db.insert(TABLA_LIBROS, null, values);
        Uri newUri = ContentUris.withAppendedId (CONTENT_URI, regId);
        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == LIBROS_ID) {
            where = "_id="+ uri.getLastPathSegment();
        }
        SQLiteDatabase db =librosDBH.getWritableDatabase();
        cont = db.delete(TABLA_LIBROS, where,selectionArgs);
        return cont;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int cont;
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri)== LIBROS_ID) {
            where = "_id="+ uri.getLastPathSegment();
        }
        SQLiteDatabase db = librosDBH.getWritableDatabase();
        cont = db.update(TABLA_LIBROS, values, where, selectionArgs);
        return cont;
    }


}
