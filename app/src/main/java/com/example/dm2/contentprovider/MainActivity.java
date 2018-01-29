package com.example.dm2.contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btnAct1);
        btn2=(Button)findViewById(R.id.btnAct2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this,Actividad1.class);
                startActivity(intento);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this,Actividad2.class);
                startActivity(intento);
            }
        });

        /*String[] TIPO_LLAMADA = {"", "entrante", "saliente", "perdida"};
        TextView salida = (TextView) findViewById(R.id.salida);
        Uri navegador = Uri.parse("content://browser/bookmarks");
        Cursor c = getContentResolver().query(navegador, null, null, null, null);
        while (c.moveToNext()) {
            salida.append("\n"
                            + DateFormat.format("dd/MM/yy k:mm (",
                    c.getLong(c.getColumnIndex(CallLog.Calls.DATE)))
                            + c.getString(c.getColumnIndex(CallLog.Calls.DURATION)) + ") "
                            + c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)) + ", "
                            + TIPO_LLAMADA[Integer.parseInt(
                            c.getString(c.getColumnIndex(CallLog.Calls.TYPE)))]);
        }*/


    }
}
