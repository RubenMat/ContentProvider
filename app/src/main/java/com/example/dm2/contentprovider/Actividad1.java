package com.example.dm2.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URI;

public class Actividad1 extends AppCompatActivity {

    TextView txtSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        txtSalida=(TextView)findViewById(R.id.salida);

        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        Cursor c = getContentResolver().query(uri,null,null,null,null);
        while(c.moveToNext()){
            txtSalida.append("\n Nombre: " + c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                                    + " Nombre fonético: " + c.getString(c.getColumnIndex(ContactsContract.Contacts.PHONETIC_NAME))
                                    + " Alias: " + c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE)));
        }
    }
}
